package com.app.kinlock.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @GeneratedValue
    private Integer id;

    private String name;

    private String type;

    private String email;

    private String qrImage;

    public Insurance(String name, String type, String email, String qrImage) {
        this.name = name;
        this.type = type;
        this.email = email;
        this.qrImage = qrImage;
    }
}
