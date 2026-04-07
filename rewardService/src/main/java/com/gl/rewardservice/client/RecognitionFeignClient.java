package com.gl.rewardservice.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "REWARD-SERVICE")
public interface RecognitionFeignClient{

    @GetMapping("/employees/{id}")
    String getEmployeeById(@PathVariable("id") Long id);
}
