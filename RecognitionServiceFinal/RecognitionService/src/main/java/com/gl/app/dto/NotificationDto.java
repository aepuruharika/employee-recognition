package com.gl.app.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationDto {
    private String empId;
    private String message;
    private String type;
    //private LocalDateTime createdAt;
}
