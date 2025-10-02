package com.app.kinlock.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
public class Insurance {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String type;

    @OneToMany(mappedBy = "insurance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Plan> plans = new ArrayList<>();

    public Insurance(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
