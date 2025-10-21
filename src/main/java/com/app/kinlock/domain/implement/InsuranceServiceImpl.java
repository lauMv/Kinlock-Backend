package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.InsuranceRepository;
import com.app.kinlock.domain.entity.Insurance;
import com.app.kinlock.domain.mapper.InsuranceMapper;
import com.app.kinlock.domain.service.InsuranceService;
import com.app.kinlock.presentation.dto.InsuranceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsuranceServiceImpl extends CRUDServiceImpl<Insurance, Integer> implements InsuranceService {

    private final InsuranceRepository repository;
    private final InsuranceMapper mapper;

    @Override
    protected GenericRepository<Insurance, Integer> getRepository() {
        return repository;
    }

    @Override
    public Insurance create(InsuranceDto dto) {
        Insurance insurance = mapper.fromDto(dto, new Insurance());
        return this.create(insurance);
    }

    @Override
    public Insurance update(Integer id, InsuranceDto dto) {
        Insurance ins = this.getById(id);
        mapper.fromDto(dto, ins);
        return repository.save(ins);
    }
}
