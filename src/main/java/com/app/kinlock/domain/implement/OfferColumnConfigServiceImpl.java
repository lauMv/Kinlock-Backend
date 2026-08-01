package com.app.kinlock.domain.implement;

import com.app.kinlock.data.OfferColumnConfigRepository;
import com.app.kinlock.domain.entity.OfferColumnConfig;
import com.app.kinlock.domain.service.OfferColumnConfigService;
import com.app.kinlock.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OfferColumnConfigServiceImpl implements OfferColumnConfigService {

    private final OfferColumnConfigRepository repository;

    private static final Map<String, String> DEFAULT_COLUMNS = new LinkedHashMap<>();

    static {
        DEFAULT_COLUMNS.put("logo", "Logo");
        DEFAULT_COLUMNS.put("aseguradora", "Aseguradora");
        DEFAULT_COLUMNS.put("plan", "Plan");
        DEFAULT_COLUMNS.put("descuento", "Dcto.");
        DEFAULT_COLUMNS.put("primaAnual", "Prima Anual");
        DEFAULT_COLUMNS.put("franquicia", "Franquicia");
    }

    @Override
    public List<OfferColumnConfig> getAll() {
        for (Map.Entry<String, String> entry : DEFAULT_COLUMNS.entrySet()) {
            if (!repository.existsByColumnKey(entry.getKey())) {
                OfferColumnConfig config = new OfferColumnConfig();
                config.setColumnKey(entry.getKey());
                config.setLabel(entry.getValue());
                config.setEnabled(true);
                repository.save(config);
            }
        }
        return repository.findAll();
    }

    @Override
    public OfferColumnConfig updateEnabled(Integer id, Boolean enabled) {
        OfferColumnConfig config = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OfferColumnConfig", id));
        config.setEnabled(enabled);
        return repository.save(config);
    }
}