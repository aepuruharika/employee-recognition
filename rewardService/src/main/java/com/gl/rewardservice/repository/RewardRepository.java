package com.gl.rewardservice.repository;

import com.gl.rewardservice.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    Optional<Reward> findByUserId(String userId);
    boolean existsByUserIdAndMilestonePoints(String userId, int milestonePoints);


}

