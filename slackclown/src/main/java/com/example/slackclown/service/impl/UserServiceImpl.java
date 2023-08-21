package com.example.slackclown.service.impl;

import com.example.slackclown.converter.UserDtoConverter;
import com.example.slackclown.dto.UserDto;
import com.example.slackclown.repository.UserRepository;
import com.example.slackclown.service.UserService;
import com.example.slackclown.util.ImageUploaderUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * this service class is for creating slack
 * users and register purpose and user operations
 */
@Service
public class UserServiceImpl implements UserService {


    private static final String USER = "user";

    @Autowired
    UserRepository userRepository;


    @Autowired
    UserDtoConverter userDtoConverter;

    @Autowired
    ImageUploaderUtill imageUploaderUtill;

    @Override
    public Object createUser(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail())
            .map(existingUser -> "User already exists")
            .orElseGet(() -> {
                imageUploaderUtill.imageUploader(userDto.getProfilePictureData(), USER);
                userRepository.save(userDtoConverter.dtoToEntity(userDto));
                return "user created sucessfully";
            });
    }

}
