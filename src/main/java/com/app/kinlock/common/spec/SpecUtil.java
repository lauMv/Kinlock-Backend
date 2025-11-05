package com.app.kinlock.common.spec;

import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class SpecUtil<T> {

    private SpecUtil() {}

    public static <T> Specification<T> fieldLike(Path<String> path, String value) {
        return fieldLike(path, value, true);
    }

    public static <T> Specification<T> fieldLike(Path<String> path, String value, boolean wrap) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            String pattern = wrap ? "%" + value.toLowerCase() + "%" : value.toLowerCase();
            return cb.like(cb.lower(path), pattern);
        };
    }

    public static <T> Specification<T> fieldEquals(Path<?> path, Object value) {
        return (root, query, cb) ->
                value == null ? cb.conjunction() : cb.equal(path, value);
    }

    public static <T> Specification<T> fieldLessThanEqual(Path<? extends Number> path, Number value) {
        return (root, query, cb) ->
                value == null ? cb.conjunction() : cb.le(path, value);
    }

    @SafeVarargs
    public static <T> Specification<T> compose(Specification<T>... specs) {
        List<Specification<T>> list = new ArrayList<>();
        for (Specification<T> s : specs) if (s != null) list.add(s);
        return list.stream()
                .reduce(Specification::and)
                .orElse((root, q, cb) -> cb.conjunction());
    }
}