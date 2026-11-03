package com.example.ims.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.example.ims.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository 
public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(String username);
}