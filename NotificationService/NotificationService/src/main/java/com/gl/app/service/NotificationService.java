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
    private NotificationRepository notificationRepository;

    public NotificationDto sendNotification(NotificationDto dto) {

        Notification notification = Notification.builder()
                .userId(dto.getUserId())
                .message(dto.getMessage())
                .type(dto.getType())
                .createdAt(LocalDateTime.now())
                .build();

        Notification saved = notificationRepository.save(notification);

        return NotificationDto.builder()
                .userId(saved.getUserId())
                .message(saved.getMessage())
                .type(saved.getType())
                //.createdAt(saved.getCreatedAt())
                .build();
    }

    public List<NotificationDto> getUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId)
                .stream()
                .map(n -> NotificationDto.builder()
                        .userId(n.getUserId())
                        .message(n.getMessage())
                        .type(n.getType())
                        //.createdAt(n.getCreatedAt())
                        .build())
                .toList();
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
