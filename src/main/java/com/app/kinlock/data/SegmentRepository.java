package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Segment;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends GenericRepository<Segment, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

    Segment findByName(String name);
}