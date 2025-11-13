package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, Integer> {
    Optional<Broker> findByEmail(String email);

    Boolean existsByEmail(String email);
}
