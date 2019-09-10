package com.housing.app.mapper;

import com.housing.app.dto.UserDto;
import com.housing.app.model.User;
import org.mapstruct.Mapper;


@Mapper
public interface UserMapper {

    UserDto toUserDto(User user);
}
