package com.gl.userservice.controller;

import com.gl.userservice.dto.UserRequestDto;
import com.gl.userservice.dto.UserResponseDto;
import com.gl.userservice.dto.LoginRequestDto;
import com.gl.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto) {
        UserResponseDto response = userService.login(dto.getEmail(), dto.getPassword());
        // Generate a simple token (in production, use JWT)
        String token = "Bearer_" + System.currentTimeMillis();
        java.util.Map<String, Object> loginResponse = new java.util.HashMap<>();
        loginResponse.put("token", token);
        loginResponse.put("user", response);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}