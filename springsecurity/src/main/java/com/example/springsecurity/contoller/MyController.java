package com.example.springsecurity.contoller;

import com.example.springsecurity.entity.UserModel;
import com.example.springsecurity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class MyController {

    Logger logger= LoggerFactory.getLogger(MyController.class);



    @Autowired
    UserService userService;


    @GetMapping("/users")
    public List<UserModel> getAllUsers()
    {
        return userService.getAllUsers();

    }

    @GetMapping("/test")
    public String test() {
        logger.info("This is workig message");
        return "Testing message";
    }

}
