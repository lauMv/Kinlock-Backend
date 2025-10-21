package com.app.kinlock.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsuranceDto {

    private String name;
    private String type;
    private String email;
    private String qrImage;
}
