package com.app.kinlock.common.function;

import com.app.kinlock.exceptions.FunctionNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class FunctionManager<T> {
    private List<NamedFunction<T, ?>> functions;

    public <R> void addFunction(NamedFunction<T, R> function) {
        if (!functions.contains(function)) {
            functions.add(function);
        }
    }

    public Object executeAndReturn(String functionName, T argument) {
        for (NamedFunction<T, ?> function : functions) {
            if (function.name().equals(functionName)) {
                return function.apply(argument);
            }
        }
        throw new FunctionNotFoundException(functionName);
    }
}
