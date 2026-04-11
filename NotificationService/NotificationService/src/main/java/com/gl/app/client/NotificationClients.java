package com.gl.app.client;

import com.gl.app.dto.NotificationDto;
import com.gl.app.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationClients {

    @PostMapping("/api/notifications/recognition")
    void sendNotification(@RequestBody NotificationDto dto);
}