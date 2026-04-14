package com.gl.app.controller;

import com.gl.app.dto.NotificationDto;
import com.gl.app.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @PostMapping("/send")
    public ResponseEntity<NotificationDto> sendNotification(@RequestBody NotificationDto dto) {
        return ResponseEntity.ok(service.sendNotification(dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationDto>> getUserNotifications(@PathVariable String userId) {
        return ResponseEntity.ok(service.getUserNotifications(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(Long id) {
        service.deleteNotification(id);
        return ResponseEntity.ok("Notification deleted");
    }

//    @PostMapping("/send")
//    public ResponseEntity<Void> send(@RequestBody NotificationDto dto) {
//        service.sendNotification(dto);
//        return ResponseEntity.ok().build();
//    }
}