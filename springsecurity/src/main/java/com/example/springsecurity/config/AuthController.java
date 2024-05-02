package com.example.springsecurity.config;

import com.example.springsecurity.entity.UserModel;
import com.example.springsecurity.model.JwtRequest;
import com.example.springsecurity.model.JwtResponse;
import com.example.springsecurity.security.JwtHelper;
import com.example.springsecurity.service.serviceImpl.UserServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/create-user")
    public ResponseEntity<Object> signIn(@RequestBody UserModel userModel)
    {
        logger.info("This is executing sign in method");
        return new ResponseEntity<>(userService.createUser(userModel), HttpStatus.OK);

    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        logger.info("Authentication initiated");
        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
//        JwtResponse response = JwtResponse.builder()
//                .jwtToken(token)
//                .username(userDetails.getUsername()).build();
        JwtResponse res = new JwtResponse();
        res.setJwtToken(token);
        res.setUsername(userDetails.getUsername());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);

        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password !!");
        }


    }
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
