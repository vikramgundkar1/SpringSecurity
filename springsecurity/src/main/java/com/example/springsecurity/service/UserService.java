package com.example.springsecurity.service;

import com.example.springsecurity.entity.UserModel;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {


    public List<UserModel> getAllUsers();

    public ResponseEntity<Object> createUser(UserModel userModel);

}
