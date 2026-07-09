package com.app.kinlock.domain.mapper;

import com.app.kinlock.domain.entity.Client;
import com.app.kinlock.domain.entity.ClientPlan;
import com.app.kinlock.presentation.dto.ClientPlanDto;
import org.springframework.stereotype.Component;

@Component
public class ClientPlanMapper {

    public ClientPlan fromDto(ClientPlanDto dto, ClientPlan clientPlan, Client client) {
        clientPlan.setVehiclePrice(dto.getVehiclePrice());
        clientPlan.setVehiclePlate(dto.getVehiclePlate());
        clientPlan.setClient(client);
        return clientPlan;
    }
}
