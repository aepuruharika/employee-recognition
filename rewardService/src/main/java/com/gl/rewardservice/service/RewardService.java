package com.gl.rewardservice.service;

import com.gl.rewardservice.Dto.RewardDto;
import com.gl.rewardservice.entity.Reward;
import com.gl.rewardservice.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RewardService {

    @Autowired
    private RewardRepository repo;

    public Reward assignReward(RewardDto dto) {
        if (repo.existsByUserIdAndMilestonePoints(dto.getUserId(), dto.getMilestonePoints())) {
            throw new RuntimeException("Reward already assigned for this milestone");
        }

        Reward reward = Reward.builder()
                .userId(dto.getUserId())
                .badgeName(dto.getBadgeName())
                .milestonePoints(dto.getMilestonePoints())
                .awardedDate(LocalDateTime.now())
                .build();

        return repo.save(reward);
    }

    public List<RewardDto> getRewardsByUser(String userId) {
        return repo.findByUserId(userId).stream()
                .map(r -> RewardDto.builder()
                        .userId(r.getUserId())
                        .badgeName(r.getBadgeName())
                        .milestonePoints(r.getMilestonePoints())
                        .build())
                .toList();
    }

    public List<RewardDto> getAllRewards() {
        return repo.findAll().stream()
                .map(r -> RewardDto.builder()
                        .userId(r.getUserId())
                        .badgeName(r.getBadgeName())
                        .milestonePoints(r.getMilestonePoints())
                        .build())
                .toList();
    }

    public void deleteReward(Long id) {
        repo.deleteById(id);
    }
}