package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends GenericRepository<Admin, Integer> {

    Admin findByEmail(String email);

}
