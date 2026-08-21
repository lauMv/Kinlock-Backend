package com.app.kinlock.domain.implement;

import com.app.kinlock.data.ClientRepository;
import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.domain.entity.Client;
import com.app.kinlock.domain.mapper.ClientMapper;
import com.app.kinlock.domain.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientServiceImpl extends CRUDServiceImpl<Client, Integer> implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    protected GenericRepository<Client, Integer> getRepository() {
        return clientRepository;
    }

    @Override
    public Client update(Client client, Integer id) {
        Client clientExisting = this.getById(id);
        return clientRepository.save(clientMapper.update(client, clientExisting));
    }
}
