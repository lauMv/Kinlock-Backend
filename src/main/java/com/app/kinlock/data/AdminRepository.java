package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends GenericRepository<Admin, Integer> {

    Optional<Admin> findByEmail(String email);

}
