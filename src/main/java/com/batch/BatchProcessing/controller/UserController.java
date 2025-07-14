package com.batch.BatchProcessing.controller;

import com.batch.BatchProcessing.dto.UserDto;
import com.batch.BatchProcessing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsService userDetailsService;


    @PostMapping("/signup")
    public String userSignUp(@RequestBody UserDto userDto ){
        return userService.saveUser(userDto);
    }

//    @PostMapping("/signin")
//    public
}
