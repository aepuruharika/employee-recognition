package com.gl.rewardservice.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationDto {
    private String empId;

    @NotBlank(message = "Message must not be blank")
    private String message;

    @NotBlank(message = "Type must not be blank")
    private String type;
}
