package com.gl.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private String empId;
    private String name;
    private String email;
    private String role;
    private String bandLevel;
    private String managerId;
}