package com.gl.app.repository;

import com.gl.app.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByEmpIdOrderByCreatedAtDesc(String empId);

    List<Notification> findByEmpIdAndIsReadFalse(String empId);
}
