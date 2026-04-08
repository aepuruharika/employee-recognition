package com.gl.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EMPLOYEE-SERVICE")
public interface RecognitionFeignClients {

    @GetMapping("/employees/{id}")
    String getEmployeeById(@PathVariable("id") Long id);
}