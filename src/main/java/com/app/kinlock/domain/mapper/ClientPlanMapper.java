package com.app.kinlock.domain.mapper;

import com.app.kinlock.domain.entity.Client;
import com.app.kinlock.domain.entity.ClientPlan;
import com.app.kinlock.presentation.dto.ClientPlanDto;
import org.springframework.stereotype.Component;

@Component
public class ClientPlanMapper {

    public ClientPlan fromDto(ClientPlanDto dto, ClientPlan clientPlan, Client client) {
        clientPlan.setVehicleBrand(dto.getVehicleBrand());
        clientPlan.setVehicleModel(dto.getVehicleModel());
        clientPlan.setVehiclePrice(dto.getVehiclePrice());
        clientPlan.setVehiclePlate(dto.getVehiclePlate());
        clientPlan.setVehiclePicFront(dto.getVehiclePicFront());
        clientPlan.setVehiclePicBack(dto.getVehiclePicBack());
        clientPlan.setVehiclePicRight(dto.getVehiclePicRight());
        clientPlan.setVehiclePicLeft(dto.getVehiclePicLeft());
        clientPlan.setVehiclePicRuat(dto.getVehiclePicRuat());
        clientPlan.setVehiclePicChasis(dto.getVehiclePicChasis());
        clientPlan.setVehiclePicMileage(dto.getVehiclePicMileage());
        clientPlan.setClient(client);
        return clientPlan;
    }
}
