package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.Broker;
import com.app.kinlock.presentation.dto.BrokerDto;

public interface BrokerAdminService {

    Broker createBroker(BrokerDto dto);

}
