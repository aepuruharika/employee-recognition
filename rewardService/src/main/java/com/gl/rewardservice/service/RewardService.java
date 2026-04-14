package com.gl.rewardservice.service;

import com.gl.rewardservice.Dto.NotificationDto;
import com.gl.rewardservice.Dto.RewardRequestDto;
import com.gl.rewardservice.client.NotificationClient;
import com.gl.rewardservice.entity.Reward;
import com.gl.rewardservice.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RewardService {

    @Autowired
    private RewardRepository repo;

    @Autowired
    private NotificationClient notificationClient;

    private String getBadge(int points) {

        if (points >= 1500) return "GOLD";
        if (points >= 1000) return "SILVER";
        if (points >= 500) return "BRONZE";

        return null;
    }

    public RewardRequestDto assignReward(String userId, int points) {

        String badge = getBadge(points);

        if (badge == null) {
            return null;
        }

        Reward reward = repo.findByUserId(userId)
                .orElse(null);


        String oldBadge = (reward != null) ? reward.getBadgeName() : null;


        if (reward == null) {
            reward = new Reward();
            reward.setUserId(userId);
        }


        reward.setBadgeName(badge);
        reward.setMilestonePoints(points);
        reward.setAwardedDate(LocalDateTime.now());

        Reward saved = repo.save(reward);


        boolean isNewBadge = (oldBadge == null) || !oldBadge.equals(badge);


        if (isNewBadge) {

            NotificationDto notification = NotificationDto.builder()
                    .empId(userId)
                    .type("BADGE")
                    .message("🎉 Congrats! You earned " + badge + " badge")
                    .build();

            notificationClient.sendNotification(notification);
        }

        return RewardRequestDto.builder()
                .userId(saved.getUserId())
                .milestonePoints(saved.getMilestonePoints())
                .build();
    }


    public RewardRequestDto getByUser(String userId) {

        return repo.findByUserId(userId)
                .map(r -> RewardRequestDto.builder()
                        .userId(r.getUserId())
                        .milestonePoints(r.getMilestonePoints())
                        .badgeName(r.getBadgeName())
                        .build())
                .orElse(null);
    }


    public List<RewardRequestDto> getAll() {

        return repo.findAll()
                .stream()
                .map(r -> RewardRequestDto.builder()
                        .userId(r.getUserId())
                        .milestonePoints(r.getMilestonePoints())
                        .badgeName(r.getBadgeName())
                        .build())
                .toList();
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deleteByUserId(String userId) {
        repo.deleteByUserId(userId);
    }
}