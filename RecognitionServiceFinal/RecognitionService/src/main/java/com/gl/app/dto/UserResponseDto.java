package com.gl.app.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDto {
    private String empId;
    private String name;
    private String email;
    private String role;
    private String bandLevel;
    private String managerId;
}
