package com.app.kinlock.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private Long phone;
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientPlan> details = new ArrayList<>();

    private String gender;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private String marriedName;
    private String documentType;
    private Long ci;
    private String countryOfBirth;
    private LocalDate birthdate;
    private Long cellphone;
    private String maritalStatus;
    private String countryOfResidence;
    private String area;
    private String profession;
    private String employmentSituation;
    private String occupation;
    private String workPlace;
    private String salary;

}
