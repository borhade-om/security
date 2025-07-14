package com.batch.BatchProcessing.mapper;

import com.batch.BatchProcessing.dto.UserDto;
import com.batch.BatchProcessing.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    User toEntity(UserDto userDto);
    UserDto toDto(User user);

}
