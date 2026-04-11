package com.gl.rewardservice.Dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationDto {
    private String empId;
    private String message;
    private String type;
}
