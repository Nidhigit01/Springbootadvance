package com.example.ims.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.example.ims.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository 
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}