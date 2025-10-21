package com.app.kinlock.domain.implement;

import com.app.kinlock.common.enums.BenefitsCoverageEnum;
import com.app.kinlock.data.BenefitRepository;
import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.domain.entity.Benefit;
import com.app.kinlock.domain.service.BenefitService;
import com.app.kinlock.exceptions.DuplicatedException;
import com.app.kinlock.presentation.dto.BenefitDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BenefitServiceImpl extends CRUDServiceImpl<Benefit, Integer> implements BenefitService {

    private final BenefitRepository repository;

    @Override
    protected GenericRepository<Benefit, Integer> getRepository() {
        return repository;
    }

    @Override
    public Benefit create(BenefitDto dto) {
        Benefit benefit = new Benefit(dto.getName(), dto.getDescription(), BenefitsCoverageEnum.fromString(dto.getCoverage()));
        return this.create(benefit);
    }

    @Override
    public Benefit update(Integer id, BenefitDto dto) {
        Benefit benefit = this.getById(id);
        if (repository.existsByNameAndCoverageAndIdNot(dto.getName(), BenefitsCoverageEnum.fromString(dto.getCoverage()), id)){
            throw new DuplicatedException("La covertura " + dto.getName() + "ya existe en la clasificacion "+ dto.getCoverage());
        }
        benefit.setName(dto.getName());
        benefit.setDescription(dto.getDescription());
        benefit.setCoverage(BenefitsCoverageEnum.fromString(dto.getCoverage()));
        return repository.save(benefit);
    }
}
