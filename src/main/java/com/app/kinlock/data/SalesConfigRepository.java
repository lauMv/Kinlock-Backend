package com.app.kinlock.data;

import com.app.kinlock.domain.entity.SalesConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesConfigRepository extends JpaRepository<SalesConfig, Integer> {
}
