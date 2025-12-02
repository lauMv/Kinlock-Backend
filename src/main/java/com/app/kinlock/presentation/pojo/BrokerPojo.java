package com.app.kinlock.presentation.pojo;

import lombok.Data;

@Data
public class BrokerPojo {

    private Integer id;
    private String name;
    private String email;

    public BrokerPojo(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
