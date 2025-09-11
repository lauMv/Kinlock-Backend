package com.app.kinlock.domain.service;

import java.util.List;
import java.util.Map;

public interface CRUDService <T, ID> {

    T create(T t);

    T update(ID id, T t) throws Exception;

    List<T> getAll();

    T getById(ID id);

//    List<T> getByIds(List<ID> ids);

    void delete(ID id);

    void checkIfElementsExist(Map<String, Boolean> dependencyMap);
}