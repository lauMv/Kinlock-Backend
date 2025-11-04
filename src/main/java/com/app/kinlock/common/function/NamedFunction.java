package com.app.kinlock.common.function;

import java.util.function.Function;

public record NamedFunction<T, R>(String name, Function<T, R> function) implements Function<T, R> {

    @Override
    public R apply(T t) {
        return function.apply(t);
    }
}
