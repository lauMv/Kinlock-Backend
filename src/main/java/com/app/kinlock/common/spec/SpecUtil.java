package com.app.kinlock.common.spec;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class SpecUtil {

    public static <T> Specification<T> fieldLike(String attribute, String value) {
        return fieldLike(attribute, value, true);
    }

    public static <T> Specification<T> fieldLike(String attribute, String value, boolean wrap) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction(); // 1=1
            String pattern = wrap ? "%" + value.toLowerCase() + "%" : value.toLowerCase();
            return cb.like(cb.lower(root.get(attribute)), pattern);
        };
    }

    public static <T> Specification<T> fieldEquals(String attribute, Object value) {
        return (root, query, cb) ->
                value == null ? cb.conjunction() : cb.equal(root.get(attribute), value);
    }

    public static <T> Specification<T> fieldGreaterThanEqual(String attribute, Number value) {
        return (root, query, cb) ->
                value == null ? cb.conjunction() : cb.ge(root.get(attribute), value);
    }

    public static <T> Specification<T> fieldLessThanEqual(String attribute, Number value) {
        return (root, query, cb) ->
                value == null ? cb.conjunction() : cb.le(root.get(attribute), value);
    }

    public static <T, R> Specification<T> joinLike(
            String joinAttribute, String targetAttribute, String value) {

        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            Join<T, R> join = root.join(joinAttribute, JoinType.INNER);
            String pattern = "%" + value.toLowerCase() + "%";
            return cb.like(cb.lower(join.get(targetAttribute)), pattern);
        };
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