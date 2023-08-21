package com.example.slackclown.converter;

import com.example.slackclown.dto.UserDto;
import com.example.slackclown.entity.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    @Autowired
    ModelMapper modelMapper;


    public AppUser dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, AppUser.class);
    }

    public UserDto entityToDto(AppUser user) {
        return modelMapper.map(user, UserDto.class);
    }

}
