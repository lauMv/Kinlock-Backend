package com.app.kinlock.presentation.dto;

import lombok.Data;

@Data
public class SendEmailDto {

    private String base64Pdf;
    private String email;
}
