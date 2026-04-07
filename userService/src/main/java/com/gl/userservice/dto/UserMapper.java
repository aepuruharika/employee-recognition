package com.gl.userservice.dto;

import com.gl.userservice.entity.User;

public class UserMapper{

    public static User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setEmpId(dto.getEmpId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setBandLevel(dto.getBandLevel());
        user.setManagerId(dto.getManagerId());
        return user;
    }

    public static UserResponseDto toDTO(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setEmpId(user.getEmpId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setBandLevel(user.getBandLevel());
        dto.setManagerId(user.getManagerId());
        return dto;
    }
}