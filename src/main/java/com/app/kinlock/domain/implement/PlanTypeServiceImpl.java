package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.PlanTypeRepository;
import com.app.kinlock.domain.entity.PlanType;
import com.app.kinlock.domain.service.PlanTypeService;
import com.app.kinlock.exceptions.DuplicatedException;
import com.app.kinlock.presentation.dto.PlanTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlanTypeServiceImpl extends CRUDServiceImpl<PlanType, Integer> implements PlanTypeService {

    private final PlanTypeRepository repository;

    @Override
    protected GenericRepository<PlanType, Integer> getRepository() {
        return repository;
    }

    @Override
    public PlanType create(PlanTypeDto dto) {
        if (repository.existsByName(dto.getName())) {
            throw new DuplicatedException("El tipo de plan " + dto.getName() + " ya existe");
        }
        PlanType planType = new PlanType();
        planType.setName(dto.getName());
        return this.create(planType);
    }

    @Override
    public PlanType update(Integer id, PlanTypeDto dto) {
        PlanType planType = this.getById(id);
        if (repository.existsByNameAndIdNot(dto.getName(), id)) {
            throw new DuplicatedException("El tipo de plan " + dto.getName() + " ya existe");
        }
        planType.setName(dto.getName());
        return repository.save(planType);
    }

    @Override
    public PlanType getByName(String name) {
        return repository.findByName(name);
    }
}