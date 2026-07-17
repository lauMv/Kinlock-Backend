package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.Segment;
import com.app.kinlock.presentation.dto.SegmentDto;

public interface SegmentService extends CRUDService<Segment, Integer> {

    Segment create(SegmentDto dto);

    Segment update(Integer id, SegmentDto dto);

    Segment getByName(String name);

}