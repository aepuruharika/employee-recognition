package com.gl.rewardservice.controller;

import com.gl.rewardservice.Dto.RewardDto;
import com.gl.rewardservice.entity.Reward;
import com.gl.rewardservice.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RewardController {

    private final RewardService rewardService;

    @PostMapping("/assign")
    public ResponseEntity<RewardDto> assignReward(@RequestBody RewardDto dto) {
        Reward reward = rewardService.assignReward(dto);
        RewardDto response = RewardDto.builder()
                .userId(reward.getUserId())
                .badgeName(reward.getBadgeName())
                .milestonePoints(reward.getMilestonePoints())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RewardDto>> getRewardsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(rewardService.getRewardsByUser(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RewardDto>> getAllRewards() {
        return ResponseEntity.ok(rewardService.getAllRewards());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteReward(@PathVariable Long id) {
        rewardService.deleteReward(id);
        return ResponseEntity.ok(Map.of(
                "message", "Reward deleted successfully",
                "deletedId", id
        ));
    }
}