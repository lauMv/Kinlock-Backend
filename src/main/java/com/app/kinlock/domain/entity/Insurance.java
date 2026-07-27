package com.app.kinlock.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Insurance extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String type;

    private String email;

    @Column(columnDefinition = "TEXT")
    private String logo;

    public Insurance(String name, String type, String email, String logo) {
        this.name = name;
        this.type = type;
        this.email = email;
        this.logo = logo;
    }
}
