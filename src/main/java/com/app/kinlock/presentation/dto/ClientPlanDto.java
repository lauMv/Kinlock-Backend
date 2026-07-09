package com.app.kinlock.presentation.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientPlanDto {

    private Integer planId;
    private Double vehiclePrice;
    private String vehiclePlate;
    private String gender;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private String marriedName;
    private String documentType;
    private Long documentNumber;
    private String countryOfBirth;
    private LocalDate birthdate;
    private Long cellphone;
    private String email;
    private String maritalStatus;
    private String countryOfResidence;
    private String area;
    private String address;
    private String profession;
    private String employmentSituation;
    private String occupation;
    private String workPlace;
    private String salary;
}
