package com.gl.rewardservice.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardRequestDto {
    private String userId;
    private int milestonePoints;
    private String badgeName;

}