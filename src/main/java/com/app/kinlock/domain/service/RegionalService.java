package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.Regional;
import com.app.kinlock.presentation.dto.RegionalDto;

public interface RegionalService extends CRUDService<Regional, Integer> {

    Regional create(RegionalDto dto);

    Regional update(Integer id, RegionalDto dto);
}
