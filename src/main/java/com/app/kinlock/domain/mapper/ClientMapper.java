package com.app.kinlock.domain.mapper;

import com.app.kinlock.domain.entity.Client;
import com.app.kinlock.presentation.dto.ClientPlanDto;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client fromClientPlanDto(ClientPlanDto dto, Client clientExisting){
        Client client;
        if (clientExisting == null) {
            client = new Client();
        } else{
            client = clientExisting;
        }
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

    public Client update(Client newClient, Client existingClient){
        existingClient.setEmail(newClient.getEmail());
        existingClient.setPhone(newClient.getPhone());
        existingClient.setAddress(newClient.getAddress());
        existingClient.setGender(newClient.getGender());
        existingClient.setName(newClient.getName());
        existingClient.setPaternalSurname(newClient.getPaternalSurname());
        existingClient.setMaternalSurname(newClient.getMaternalSurname());
        existingClient.setMarriedName(newClient.getMarriedName());
        existingClient.setDocumentType(newClient.getDocumentType());
        existingClient.setCi(newClient.getCi());
        existingClient.setCountryOfBirth(newClient.getCountryOfBirth());
        existingClient.setBirthdate(newClient.getBirthdate());
        existingClient.setCellphone(newClient.getCellphone());
        existingClient.setMaritalStatus(newClient.getMaritalStatus());
        existingClient.setCountryOfResidence(newClient.getCountryOfResidence());
        existingClient.setArea(newClient.getArea());
        existingClient.setProfession(newClient.getProfession());
        existingClient.setEmploymentSituation(newClient.getEmploymentSituation());
        existingClient.setOccupation(newClient.getOccupation());
        existingClient.setWorkPlace(newClient.getWorkPlace());
        existingClient.setSalary(newClient.getSalary());
        return existingClient;
    }
}
