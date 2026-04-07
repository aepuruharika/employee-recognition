package com.gl.userservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String empId;
    private String name;
    private String email;
    private String password;
    private String role;
    private String bandLevel;
    private String managerId;
}