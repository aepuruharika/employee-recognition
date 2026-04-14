package com.gl.app.client;


import com.gl.app.dto.NotificationDto;
import com.gl.app.dto.RewardRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @PostMapping("/api/notification/send")
    NotificationDto sendNotification(@RequestBody NotificationDto dto);

}
