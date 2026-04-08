package com.gl.app.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationDto {
    private Long userId;
    private String message;
    private String type;
    //private LocalDateTime createdAt;
}
