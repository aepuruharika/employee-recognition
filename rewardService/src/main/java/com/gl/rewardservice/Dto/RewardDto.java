package com.gl.rewardservice.Dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardDto {
    private String userId;
    private String badgeName;
    private int milestonePoints;

}