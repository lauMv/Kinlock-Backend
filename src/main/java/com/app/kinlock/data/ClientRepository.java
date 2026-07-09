package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends GenericRepository<Client, Integer>{
}
