package com.gl.app.client;

import com.gl.app.dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @PostMapping("/api/notifications/recognition")
    void sendNotification(@RequestBody NotificationDto dto);
}