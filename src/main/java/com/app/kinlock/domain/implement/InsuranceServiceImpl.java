package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.InsuranceRepository;
import com.app.kinlock.domain.entity.Insurance;
import com.app.kinlock.domain.service.InsuranceService;
import com.app.kinlock.presentation.dto.InsuranceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsuranceServiceImpl extends CRUDServiceImpl<Insurance, Integer> implements InsuranceService {

    private final InsuranceRepository repository;

    @Override
    protected GenericRepository<Insurance, Integer> getRepository() {
        return repository;
    }

    @Override
    public Insurance create(InsuranceDto dto) {
        return this.create(new Insurance(dto.getName(), dto.getType()));
    }

    @Override
    public Insurance update(Integer id, InsuranceDto dto) {
        Insurance ins = new Insurance(dto.getName(), dto.getType());
        ins.setName(dto.getName());
        ins.setType(dto.getType());
        return this.create(ins);
    }
}
