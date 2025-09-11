package com.app.kinlock.domain.implement;

import com.app.kinlock.common.SpanishEntityNameProvider;
import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.domain.service.CRUDService;
import com.app.kinlock.exceptions.EntityNotFoundException;
import com.app.kinlock.exceptions.HasAssociatedEntityException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@Service
public abstract class CRUDServiceImpl<T, ID> implements CRUDService<T, ID> {

    protected abstract GenericRepository<T, ID> getRepository();

    @Override
    public T create(T t) {
        return getRepository().save(t);
    }

    @Override
    public T update(ID id, T t) throws Exception {
        Class<?> clazz = t.getClass();
        Method setIdMethod = clazz.getMethod("setId", id.getClass());
        setIdMethod.invoke(t, id);

        T entityGenericFound = getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        SpanishEntityNameProvider.getSpanishName(getEntityName()), (Integer) id));

        Class<?> genericClazz = entityGenericFound.getClass();
        Field field = genericClazz.getField("createdAt");
        field.setAccessible(true);
        Object createdAt = field.get(entityGenericFound);
        Method setCreatedAt = entityGenericFound.getClass()
                .getMethod("setCreatedAt", createdAt.getClass());
        setCreatedAt.invoke(t, createdAt);

        return getRepository().save(t);
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

    @Override
    public T getById(ID id) {
        return getRepository().findById(id).orElseThrow(
                () -> new EntityNotFoundException(SpanishEntityNameProvider.getSpanishName(getEntityName()),
                        (Integer) id));
    }

//    @Override
//    public List<T> getByIds(List<ID> ids) {
//        List<EntityErrorModel> errors = new ArrayList<>();
//        List<T> entities = new ArrayList<>();
//        for (ID id : ids) {
//            try {
//                entities.add(this.getById(id));
//            } catch (EntityNotFoundException e) {
//                errors.add(new EntityErrorModel(SpanishEntityNameProvider.getSpanishName(getEntityName()),
//                        e.getMessage(), "EntityNotFoundException"));
//            }
//        }
//        if (!errors.isEmpty()) {
//            throw new ErrorListException(errors);
//        }
//        return entities;
//    }

    @Override
    public void delete(ID id) {
        getRepository().findById(id).orElseThrow(
                () -> new EntityNotFoundException(SpanishEntityNameProvider.getSpanishName(getEntityName()),
                        (Integer) id));
        getRepository().deleteById(id);
    }

    @Override
    public void checkIfElementsExist(Map<String, Boolean> dependencyMap) {
        for (Map.Entry<String, Boolean> entry : dependencyMap.entrySet()) {
            if (entry.getValue()) {
                throw new HasAssociatedEntityException(
                        SpanishEntityNameProvider.getSpanishName(getEntityName()), entry.getKey());
            }
        }
    }

    private String getEntityName() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return ((Class<?>) parameterizedType.getActualTypeArguments()[0]).getSimpleName();
    }
}
