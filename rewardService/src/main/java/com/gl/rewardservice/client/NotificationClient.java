package com.gl.rewardservice.client;

import com.gl.rewardservice.Dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @PostMapping("/api/notification/send")
    void sendNotification(@RequestBody NotificationDto dto);
}