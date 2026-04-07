package com.gl.userservice.controller;

import com.gl.userservice.dto.UserRequestDto;
import com.gl.userservice.dto.UserResponseDto;
import com.gl.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto dto) {
        UserResponseDto response = userService.registerUser(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String empId) {
        UserResponseDto response = userService.getUserById(empId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String empId,
                                                      @RequestBody UserRequestDto dto) {
        UserResponseDto response = userService.updateUser(empId, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/band/{bandLevel}")
    public ResponseEntity<List<UserResponseDto>> getUsersByBand(@PathVariable String bandLevel) {
        List<UserResponseDto> users = userService.getUsersByBand(bandLevel);
        return ResponseEntity.ok(users);
    }
}