package com.batch.BatchProcessing.mapper;

import com.batch.BatchProcessing.dto.UserDto;
import com.batch.BatchProcessing.entity.User;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-14T15:40:56+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        String userName = null;
        String password = null;

        userName = userDto.getUserName();
        password = userDto.getPassword();

        Long userId = null;
        LocalDateTime createdAt = null;

        User user = new User( userId, userName, password, createdAt );

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserName( user.getUserName() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }
}
