package com.app.kinlock.data;

import com.app.kinlock.domain.entity.OfferColumnConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferColumnConfigRepository extends GenericRepository<OfferColumnConfig, Integer> {

    boolean existsByColumnKey(String columnKey);
}