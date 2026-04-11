package com.gl.app.service;


import com.gl.app.dto.NotificationDto;
import com.gl.app.entity.Notification;
import com.gl.app.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repo;

    // 🔥 Save Notification
    public Notification sendNotification(NotificationDto dto) {

        Notification notification = Notification.builder()
                .empId(dto.getEmpId())
                .message(dto.getMessage())
                .type(dto.getType())
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .build();

        return repo.save(notification);
    }

    // 📥 Get all notifications
    public List<Notification> getUserNotifications(String empId) {
        return repo.findByEmpIdOrderByCreatedAtDesc(empId);
    }

    // 📥 Get unread notifications
    public List<Notification> getUnreadNotifications(String empId) {
        return repo.findByEmpIdAndIsReadFalse(empId);
    }

    // ✅ Mark as read
    public void markAsRead(Long id) {
        Notification notification = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        repo.save(notification);
    }
}