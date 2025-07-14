package com.batch.BatchProcessing.service;

import com.batch.BatchProcessing.dto.UserDto;
import com.batch.BatchProcessing.entity.User;
import com.batch.BatchProcessing.mapper.UserMapper;
import com.batch.BatchProcessing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    public String saveUser(UserDto userDto){

        User entity = userMapper.toEntity(userDto);
        entity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepository.save(entity);
        return "data Inserted";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUserName = userRepository.findByUserName(username);

        if(byUserName==null){
            throw new UsernameNotFoundException("user not present");
        }else {
            return new org.springframework.security.core.userdetails.User(byUserName.getUserName(), byUserName.getPassword(), new ArrayList<>());
        }
    }

}
