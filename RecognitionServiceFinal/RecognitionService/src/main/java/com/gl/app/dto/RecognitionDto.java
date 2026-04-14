package com.gl.app.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RecognitionDto {
    private String  senderId;
    private String receiverId;
    private int points;
    private String message;
    //private LocalDateTime createdAt;
}