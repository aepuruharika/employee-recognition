package com.gl.app.repository;

import com.gl.app.entity.Recognition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecognitionRepository extends JpaRepository<Recognition, Long> {
    List<Recognition> findByReceiverId(String receiverId);

    @Query("SELECT r.receiverId, SUM(r.points) FROM Recognition r GROUP BY r.receiverId ORDER BY SUM(r.points) DESC")
    List<Object[]> getLeaderboard();
}