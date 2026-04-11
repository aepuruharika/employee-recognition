package com.gl.app.controller;


import com.gl.app.dto.NotificationDto;
import com.gl.app.entity.Notification;
import com.gl.app.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController {

    @Autowired
    private NotificationService service;

    // 🔥 Called from Recognition Service (Feign)
    @PostMapping("/recognition")
    public Notification sendRecognition(@RequestBody NotificationDto dto) {
        return service.sendNotification(dto);
    }

    // 📥 Get all notifications
    @GetMapping("/{empId}")
    public List<Notification> getAll(@PathVariable String empId) {
        return service.getUserNotifications(empId);
    }

    // 📥 Get unread
    @GetMapping("/{empId}/unread")
    public List<Notification> getUnread(@PathVariable String empId) {
        return service.getUnreadNotifications(empId);
    }

    // ✅ Mark as read
    @PutMapping("/{id}/read")
    public String markAsRead(@PathVariable Long id) {
        service.markAsRead(id);
        return "Marked as read";
    }
}