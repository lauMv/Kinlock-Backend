package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.SalesConfig;

public interface SalesConfigService {

    SalesConfig getConfig();

    SalesConfig setEnabled(Boolean enabled);

}
