package com.app.kinlock.domain.mapper;

import com.app.kinlock.domain.entity.Insurance;
import com.app.kinlock.presentation.dto.InsuranceDto;
import org.springframework.stereotype.Component;

@Component
public class InsuranceMapper {

    public Insurance fromDto(InsuranceDto dto, Insurance insurance){
        insurance.setName(dto.getName());
        insurance.setType(dto.getType());
        insurance.setEmail(dto.getEmail());
        insurance.setQrImage(dto.getQrImage());
        return insurance;
    }
}
