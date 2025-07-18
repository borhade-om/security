package com.batch.BatchProcessing.controller;

import com.batch.BatchProcessing.config.JWTService;
import com.batch.BatchProcessing.dto.UserDto;
import com.batch.BatchProcessing.entity.User;
import com.batch.BatchProcessing.repository.UserRepository;
import com.batch.BatchProcessing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JWTService jwtService;


    @PostMapping("/signup")
    public String userSignUp(@RequestBody User user ){
        return userService.saveUser(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> userSignIn(@RequestBody UserDto userDto){
       try {
           Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
       }
       catch (BadCredentialsException e) {
           return ResponseEntity.status(401).body("Authentication fail");
       }
       UserDetails userDetails = userService.loadUserByUsername(userDto.getUserName());
       return new ResponseEntity<String>(jwtService.generateToken(userDetails), HttpStatus.FOUND);
    }

    @PostMapping("/simple/signin")
    public String verify(@RequestBody UserDto userDto){
        try {
            return userService.verify(userDto);
        }
        catch (BadCredentialsException ex){
            return "bad credentials";
        }

    }
}
