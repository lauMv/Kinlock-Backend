package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.OfferColumnConfig;

import java.util.List;

public interface OfferColumnConfigService {

    List<OfferColumnConfig> getAll();

    OfferColumnConfig updateEnabled(Integer id, Boolean enabled);
}