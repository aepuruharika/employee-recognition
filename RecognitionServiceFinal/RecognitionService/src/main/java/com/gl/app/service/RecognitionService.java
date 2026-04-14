package com.gl.app.service;

import com.gl.app.client.NotificationClient;
import com.gl.app.client.RewardClient;
import com.gl.app.client.UserClient;
import com.gl.app.dto.*;
import com.gl.app.entity.Recognition;
import com.gl.app.exception.BandLevelNotFoundException;
import com.gl.app.exception.RecognitionServiceException;
import com.gl.app.repository.RecognitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RecognitionService {

    @Autowired
    private RecognitionRepository repo;

    @Autowired
    private UserClient userClient;

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    private RewardClient rewardClient;

    public Recognition giveRecognition(RecognitionDto rec) throws RecognitionServiceException {

        if (rec.getSenderId().equals(rec.getReceiverId())) {
            throw new RecognitionServiceException("User cannot recognize themselves");
        }

        if (rec.getPoints() <= 0) {
            throw new RecognitionServiceException("Points must be greater than zero");
        }

        Recognition recognition = Recognition.builder()
                .senderId(rec.getSenderId())
                .receiverId(rec.getReceiverId())
                .points(rec.getPoints())
                .message(rec.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        Recognition saved = repo.save(recognition);

        Integer totalPoints = repo.getTotalPointsByReceiver(rec.getReceiverId());

        if (totalPoints == null) {
            totalPoints = 0;
        }

        // 🔥 STEP 2: call Reward Service
        RewardRequestDto rewardDto = RewardRequestDto.builder()
                .userId(rec.getReceiverId())
                .milestonePoints(totalPoints)
                .build();

        rewardClient.assignReward(rewardDto);


        // 🔥 Fetch sender name
        UserResponseDto sender = userClient.getUserById(rec.getSenderId());
        String senderName = (sender != null) ? sender.getName() : "Someone";

        // 🔥 Build notification
        String message = "🎉 You got " + rec.getPoints() + " points from " + senderName;

        NotificationDto notification = NotificationDto.builder()
                .userId(rec.getReceiverId())
                .message(message)
                .type("RECOGNITION")
                .build();

        // 🔥 Send notification
        notificationClient.sendNotification(notification);

        return saved;
    }

    // 📥 Get by user
    public List<RecognitionDto> getRecognitionsByUser(String userId) {
        return repo.findByReceiverId(userId).stream()
                .map(r -> RecognitionDto.builder()
                        .senderId(r.getSenderId())
                        .receiverId(r.getReceiverId())
                        .points(r.getPoints())
                        .message(r.getMessage())
                        .build())
                .toList();
    }

    // 📥 Get all
    public List<RecognitionDto> getAllRecognitions() {
        return repo.findAll().stream()
                .map(r -> RecognitionDto.builder()
                        .senderId(r.getSenderId())
                        .receiverId(r.getReceiverId())
                        .points(r.getPoints())
                        .message(r.getMessage())
                        .build())
                .toList();
    }

    // 🏆 Leaderboard
    public List<LeaderboardDto> getLeaderboard() {

        List<Object[]> results = repo.getLeaderboard();
        List<LeaderboardDto> list = new ArrayList<>();
        int rank = 1;

        for (Object[] row : results) {
            String userId = String.valueOf(row[0]);
            Long totalPoints = ((Number) row[1]).longValue();

            UserResponseDto user = userClient.getUserById(userId);

            LeaderboardDto dto = new LeaderboardDto(
                    userId,
                    totalPoints,
                    rank++,
                    user.getName()
            );

            list.add(dto);
        }

        return list;
    }


    public List<LeaderboardDto> getLeaderboardByBandLevel(String bandLevel)
            throws BandLevelNotFoundException {

        if (bandLevel == null ) {
            throw new BandLevelNotFoundException("Band level cannot be null or empty");
        }


        List<Object[]> results = repo.getLeaderboard();
        List<LeaderboardDto> list = new ArrayList<>();

        for (Object[] row : results) {

            String userId = String.valueOf(row[0]);
            Long points = ((Number) row[1]).longValue();

            UserResponseDto user = userClient.getUserById(userId);

            if (user == null || user.getBandLevel() == null) {
                continue;
            }

            String userBandLevel = user.getBandLevel();

            if (userBandLevel.equalsIgnoreCase(bandLevel)) {

                list.add(new LeaderboardDto(
                        userId,
                        points,
                        0,
                        user.getName()
                ));
            }
        }

        if (list.isEmpty()) {
            throw new BandLevelNotFoundException(
                    "No users found for band level: " + bandLevel
            );
        }

        // Sort by points descending
        list.sort((a, b) -> Long.compare(b.getTotalPoints(), a.getTotalPoints()));

        // Assign rank
        int rank = 1;
        for (LeaderboardDto dto : list) {
            dto.setRank(rank++);
        }

        return list;
    }

    // ❌ Delete
    public void deleteRecognition(Long id) {
        repo.deleteById(id);
    }

    // ✏️ Update
    public RecognitionDto updateRecognition(Long id, RecognitionDto dto)
            throws RecognitionServiceException {

        Recognition rec = repo.findById(id)
                .orElseThrow(() -> new RecognitionServiceException("Recognition not found"));

        rec.setPoints(dto.getPoints());
        rec.setMessage(dto.getMessage());

        Recognition updated = repo.save(rec);

        return RecognitionDto.builder()
                .senderId(updated.getSenderId())
                .receiverId(updated.getReceiverId())
                .points(updated.getPoints())
                .message(updated.getMessage())
                .build();
    }
}