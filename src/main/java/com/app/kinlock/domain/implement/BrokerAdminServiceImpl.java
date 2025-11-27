package com.app.kinlock.domain.implement;

import com.app.kinlock.data.BrokerRepository;
import com.app.kinlock.domain.entity.Broker;
import com.app.kinlock.domain.service.BrokerAdminService;
import com.app.kinlock.exceptions.DuplicatedException;
import com.app.kinlock.presentation.dto.BrokerDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrokerAdminServiceImpl implements BrokerAdminService {

    private final BrokerRepository brokerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Broker createBroker(BrokerDto dto) {
        if (brokerRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicatedException("El correo ya esta en uso");
        }

        Broker b = new Broker();
        b.setName(dto.getName());
        b.setCi(dto.getCi());
        b.setEmail(dto.getEmail());
        b.setPassword(passwordEncoder.encode(dto.getPassword()));
        return brokerRepository.save(b);
    }
}
