package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.Benefit;
import com.app.kinlock.presentation.dto.BenefitDto;

public interface BenefitService extends CRUDService<Benefit, Integer> {

    Benefit create(BenefitDto dto);

    Benefit update(Integer id, BenefitDto dto);

}
