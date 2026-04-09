package com.CRM.repository;

import com.CRM.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

    //Optional because user may or may not exist in DB
    Optional<Users> findByEmail(String email);
}
