package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Insurance;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends GenericRepository<Insurance, Integer> {
}
