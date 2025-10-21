package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Regional;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionalRepository extends GenericRepository<Regional, Integer> {

    Boolean existsByNameAndCountryAndIdNot(String name, String country, Integer id);
}
