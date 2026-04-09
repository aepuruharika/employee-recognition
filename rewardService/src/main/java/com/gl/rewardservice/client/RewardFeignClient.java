package com.gl.rewardservice.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "REWARD-SERVICE")
public interface RewardFeignClient {

    @GetMapping("/employees/{id}")
    String getEmployeeById(@PathVariable("id") Long id);
}
