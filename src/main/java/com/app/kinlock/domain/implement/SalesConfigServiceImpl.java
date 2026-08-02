package com.app.kinlock.domain.implement;

import com.app.kinlock.data.SalesConfigRepository;
import com.app.kinlock.domain.entity.SalesConfig;
import com.app.kinlock.domain.service.SalesConfigService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalesConfigServiceImpl implements SalesConfigService {

    private static final Integer SINGLETON_ID = 1;

    private final SalesConfigRepository salesConfigRepository;

    @Override
    public SalesConfig getConfig() {
        return salesConfigRepository.findById(SINGLETON_ID)
                .orElseGet(() -> salesConfigRepository.save(new SalesConfig(SINGLETON_ID, true)));
    }

    @Override
    public SalesConfig setEnabled(Boolean enabled) {
        SalesConfig config = getConfig();
        config.setEnabled(enabled);
        return salesConfigRepository.save(config);
    }
}
