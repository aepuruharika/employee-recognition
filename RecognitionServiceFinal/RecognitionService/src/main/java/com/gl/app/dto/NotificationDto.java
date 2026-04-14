package com.gl.app.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationDto {
    private String userId;
    private String message;
    private String type;
    //private LocalDateTime createdAt;
}
