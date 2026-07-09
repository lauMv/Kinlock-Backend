package com.app.kinlock.domain.mapper;

import com.app.kinlock.domain.entity.Client;
import com.app.kinlock.presentation.dto.ClientPlanDto;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client fromClientPlanDto(ClientPlanDto dto){
        Client client = new Client();
        client.setGender(dto.getGender());
        client.setName(dto.getName());
        client.setPaternalSurname(dto.getPaternalSurname());
        client.setMaternalSurname(dto.getMaternalSurname());
        client.setMarriedName(dto.getMarriedName());
        client.setDocumentType(dto.getDocumentType());
        client.setCi(dto.getDocumentNumber());
        client.setCountryOfBirth(dto.getCountryOfBirth());
        client.setBirthdate(dto.getBirthdate());
        client.setCellphone(dto.getCellphone());
        client.setEmail(dto.getEmail());
        client.setMaritalStatus(dto.getMaritalStatus());
        client.setCountryOfResidence(dto.getCountryOfResidence());
        client.setArea(dto.getArea());
        client.setAddress(dto.getAddress());
        client.setProfession(dto.getProfession());
        client.setEmploymentSituation(dto.getEmploymentSituation());
        client.setOccupation(dto.getOccupation());
        client.setWorkPlace(dto.getWorkPlace());
        client.setSalary(dto.getSalary());
        return client;
    }
}
