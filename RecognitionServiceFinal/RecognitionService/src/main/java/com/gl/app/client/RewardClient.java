package com.gl.app.client;

import com.gl.app.dto.RewardRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "reward-service")
public interface RewardClient {

    @PostMapping("/api/rewards/assign")
    RewardRequestDto assignReward(@RequestBody RewardRequestDto dto);
}