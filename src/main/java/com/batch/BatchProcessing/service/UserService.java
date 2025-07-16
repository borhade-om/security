package com.batch.BatchProcessing.service;

import com.batch.BatchProcessing.config.JWTService;
import com.batch.BatchProcessing.dto.UserDto;
import com.batch.BatchProcessing.entity.User;
import com.batch.BatchProcessing.mapper.UserMapper;
import com.batch.BatchProcessing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JWTService jwtService;

     @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UserService() {
    }

    public String saveUser(User user) {

//        User entity = userMapper.toEntity(userDto);
//        entity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "data Inserted";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUserName = userRepository.findByUserName(username);

        if (byUserName == null) {
            throw new UsernameNotFoundException("user not present");
        } else {
            return new org.springframework.security.core.userdetails.User(byUserName.getUserName(), byUserName.getPassword(), new ArrayList<>());
        }
    }

    public String verify(UserDto userDto) {
//        User byUserName = userRepository.findByUserName(userDto.getUserName());
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword())
        );
        UserDetails userDetails = loadUserByUsername(userDto.getUserName());
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(userDetails);
        } else {
            throw new UsernameNotFoundException("user not present");
        }
    }

}

