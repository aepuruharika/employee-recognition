package com.gl.userservice.service;

import com.gl.userservice.dto.UserRequestDto;
import com.gl.userservice.dto.UserResponseDto;
import com.gl.userservice.dto.UserMapper;
import com.gl.userservice.entity.User;
import com.gl.userservice.exception.EmailAlreadyExistsException;
import com.gl.userservice.exception.UserNotFoundException;
import com.gl.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponseDto registerUser(UserRequestDto dto) {
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> { throw new EmailAlreadyExistsException("Email already exists: " + dto.getEmail()); });

        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        return UserMapper.toDTO(saved);
    }

    public UserResponseDto getUserById(String empId) {
        User user = userRepository.findById(empId)
                .orElseThrow(() -> new UserNotFoundException("User not found with empId: " + empId));
        return UserMapper.toDTO(user);
    }

    public UserResponseDto updateUser(String empId, UserRequestDto dto) {
        User user = userRepository.findById(empId)
                .orElseThrow(() -> new UserNotFoundException("User not found with empId: " + empId));

        if(dto.getBandLevel() != null) user.setBandLevel(dto.getBandLevel());
        if(dto.getManagerId() != null) user.setManagerId(dto.getManagerId());

        User updated = userRepository.save(user);
        return UserMapper.toDTO(updated);
    }

    public List<UserResponseDto> getUsersByBand(String bandLevel) {
        List<User> users = userRepository.findByBandLevel(bandLevel);
        return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }
}