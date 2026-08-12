package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.Client;

public interface ClientService extends CRUDService<Client, Integer> {

    Client update(Client client, Integer id);
}
