package com.gl.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LeaderboardDto {

    private Long userId;
    private Long totalPoints;
}