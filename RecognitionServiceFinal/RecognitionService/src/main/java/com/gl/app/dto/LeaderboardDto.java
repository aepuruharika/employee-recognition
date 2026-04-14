package com.gl.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LeaderboardDto {

    private String userId;
    private Long totalPoints;
    private int rank;
    private String userName;
    public LeaderboardDto(int rank, String userName, Long totalPoints) {
        this.totalPoints = totalPoints;
        this.rank = rank;
        this.userName = userName;
    }
}