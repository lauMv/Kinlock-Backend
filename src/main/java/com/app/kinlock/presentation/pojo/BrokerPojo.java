package com.app.kinlock.presentation.pojo;

import lombok.Data;

@Data
public class BrokerPojo {

    private Integer id;
    private String name;
    private Long ci;
    private String email;

    public BrokerPojo(Integer id, String name, Long ci, String email) {
        this.id = id;
        this.name = name;
        this.ci = ci;
        this.email = email;
    }
}
