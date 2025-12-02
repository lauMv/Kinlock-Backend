package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.Broker;
import com.app.kinlock.presentation.dto.BrokerDto;
import com.app.kinlock.presentation.pojo.BrokerPojo;
import com.app.kinlock.presentation.pojo.PlanPojo;

import java.util.List;

public interface BrokerAdminService {

    void save(Broker broker);

    Broker createBroker(BrokerDto dto);

    Broker update(BrokerDto dto);

    List<BrokerPojo> getAllBrokersPojo();

    Broker findByEmail(String email);

    Broker getBrokerInfo();

    List<PlanPojo> getPlansByBroker();
}
