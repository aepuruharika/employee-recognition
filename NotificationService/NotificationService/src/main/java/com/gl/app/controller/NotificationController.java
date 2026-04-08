package com.gl.app.controller;

import com.gl.app.dto.NotificationDto;
import com.gl.app.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @PostMapping("/send")
    public ResponseEntity<NotificationDto> send(@RequestBody NotificationDto dto) {
        return ResponseEntity.ok(service.sendNotification(dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationDto>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getUserNotifications(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNotification(Long id) {
        service.deleteNotification(id);
        return ResponseEntity.ok().build();
    }
}