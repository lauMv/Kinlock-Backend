package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Broker;
import com.app.kinlock.presentation.pojo.BrokerPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, Integer> {
    Broker findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query("SELECT new com.app.kinlock.presentation.pojo.BrokerPojo (b.id, b.name, b.email) " +
            "FROM Broker b ")
    List<BrokerPojo> getAllPojo();
}
