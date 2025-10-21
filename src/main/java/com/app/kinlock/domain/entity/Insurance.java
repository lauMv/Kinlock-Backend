package com.app.kinlock.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Insurance extends Base{

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
