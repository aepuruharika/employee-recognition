package com.gl.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


    @Entity
    @Table(name = "users")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class User {

        @Id
        @Column(name = "emp_id", nullable = false, updatable = false)
        private String empId;

        @NotBlank(message = "Name is required")
        @Column(nullable = false)
        private String name;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Column(unique = true, nullable = false)
        private String email;

        @NotBlank(message = "Password is required")
        @Column(nullable = false)
        private String password;

        @NotBlank(message = "Role is required")
        @Column(nullable = false)
        private String role;

        @NotBlank(message = "Band level is required")
        @Column(nullable = false)
        private String bandLevel;

        @Column(name = "manager_id")
        private String managerId;
    }

