package com.example.springsecurity.service.serviceImpl;

import com.example.springsecurity.entity.UserModel;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



//    public UserServiceImpl() {
//
//        list.add(new UserModel(1,"Vikram", "vikram@gmail.com"));
//        list.add(new UserModel(2,"Vikas", "vikas@gmail.com"));
//        list.add(new UserModel(2,"Sataym", "sdfsd@gmail.com"));
//
//    }

    @Override
    public List<UserModel> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> createUser(UserModel userModel) {

        Optional<UserModel> isUserExist = userRepository.findByEmail(userModel.getEmail());

        if(isUserExist.isPresent())
        {
            return new ResponseEntity<Object>("The User is already exist", HttpStatus.CONFLICT);
        }


        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
         return  new ResponseEntity<>(userRepository.save(userModel), HttpStatus.OK);
    }


}
