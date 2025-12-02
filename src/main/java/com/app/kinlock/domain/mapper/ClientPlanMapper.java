package com.app.kinlock.domain.mapper;

import com.app.kinlock.domain.entity.ClientPlan;
import com.app.kinlock.presentation.dto.ClientPlanDto;
import org.springframework.stereotype.Component;

@Component
public class ClientPlanMapper {

    public ClientPlan fromDto(ClientPlanDto dto, ClientPlan clientPlan) {
        clientPlan.setVehiclePrice(dto.getVehiclePrice());
        clientPlan.setVehiclePlate(dto.getVehiclePlate());
        clientPlan.setGender(dto.getGender());
        clientPlan.setName(dto.getName());
        clientPlan.setPaternalSurname(dto.getPaternalSurname());
        clientPlan.setMaternalSurname(dto.getMaternalSurname());
        clientPlan.setMarriedName(dto.getMarriedName());
        clientPlan.setDocumentType(dto.getDocumentType());
        clientPlan.setCountryOfBirth(dto.getCountryOfBirth());
        clientPlan.setBirthdate(dto.getBirthdate());
        clientPlan.setCellphone(dto.getCellphone());
        clientPlan.setEmail(dto.getEmail());
        clientPlan.setMaritalStatus(dto.getMaritalStatus());
        clientPlan.setCountryOfResidence(dto.getCountryOfResidence());
        clientPlan.setArea(dto.getArea());
        clientPlan.setAddress(dto.getAddress());
        clientPlan.setProfession(dto.getProfession());
        clientPlan.setEmploymentSituation(dto.getEmploymentSituation());
        clientPlan.setOccupation(dto.getOccupation());
        clientPlan.setWorkPlace(dto.getWorkPlace());
        clientPlan.setSalary(dto.getSalary());
        return clientPlan;
    }
}
