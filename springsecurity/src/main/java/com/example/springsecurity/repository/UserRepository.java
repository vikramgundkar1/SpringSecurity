package com.example.springsecurity.repository;

import com.example.springsecurity.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    public Optional<UserModel> findByEmail(String email);
}
