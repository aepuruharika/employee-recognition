package com.gl.app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardDto {

    private String userId;
    private String name;
    private Long totalPoints;
    private int rank;
}