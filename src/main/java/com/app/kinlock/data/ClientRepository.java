package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends GenericRepository<Client, Integer>{
    Optional<Client> findClientByNameAndEmailAndCellphone(String name, String email, Long phone);
}
