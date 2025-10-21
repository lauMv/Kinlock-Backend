package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.RegionalRepository;
import com.app.kinlock.domain.entity.Regional;
import com.app.kinlock.domain.service.RegionalService;
import com.app.kinlock.exceptions.DuplicatedException;
import com.app.kinlock.presentation.dto.RegionalDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegionalsServiceImpl extends CRUDServiceImpl<Regional, Integer> implements RegionalService {

    private final RegionalRepository regionalRepository;

    @Override
    protected GenericRepository<Regional, Integer> getRepository() {
        return regionalRepository;
    }

    @Override
    public Regional create(RegionalDto dto) {
        Regional regional = new Regional();
        regional.setName(dto.getName());
        regional.setCountry(dto.getCountry());
        return this.create(regional);
    }

    @Override
    public Regional update(Integer id, RegionalDto dto) {
        Regional regional = this.getById(id);
        if (regionalRepository.existsByNameAndCountryAndIdNot(dto.getName(), dto.getCountry(), id)) {
            throw new DuplicatedException("Regional " + dto.getName() + " ya existe");
        }
        regional.setName(dto.getName());
        regional.setCountry(dto.getCountry());
        return regionalRepository.save(regional);
    }
}
