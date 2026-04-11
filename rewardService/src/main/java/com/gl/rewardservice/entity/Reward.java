package com.gl.rewardservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rewards", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String badgeName;

    @Column(nullable = false)
    private int milestonePoints;

    private LocalDateTime awardedDate;
}