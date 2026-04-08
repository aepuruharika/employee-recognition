package com.gl.app.service;

import com.gl.app.dto.LeaderboardDto;
import com.gl.app.dto.RecognitionDto;
import com.gl.app.entity.Recognition;
import com.gl.app.exception.RecognitionServiceException;
import com.gl.app.repository.RecognitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecognitionService {

    @Autowired
    private RecognitionRepository repo;

    public Recognition giveRecognition(RecognitionDto rec) throws RecognitionServiceException {
        Recognition recognition =  new Recognition();
        recognition.setSenderId(rec.getSenderId());
        recognition.setReceiverId(rec.getReceiverId());
        recognition.setPoints(rec.getPoints());
        recognition.setMessage(rec.getMessage());
        if (rec.getSenderId().equals(rec.getReceiverId())) {
            throw new RecognitionServiceException("User cannot recognize themselves");
        }

        if (rec.getPoints() <= 0) {
            throw new RecognitionServiceException("Points must be greater than zero");
        }

        recognition.setCreatedAt(LocalDateTime.now());

        return repo.save(recognition);
    }

    public List<RecognitionDto> getRecognitionsByUser(Long userId) {
        List<Recognition> recognitions = repo.findByReceiverId(userId);

        return recognitions.stream()
                .map(rec -> RecognitionDto.builder()
                        .senderId(rec.getSenderId())
                        .receiverId(rec.getReceiverId())
                        .points(rec.getPoints())
                        .message(rec.getMessage())
                        //.createdAt(rec.getCreatedAt())
                        .build())
                .toList();
    }

    public List<RecognitionDto> getAllRecognitions() {
        List<Recognition> recognitions = repo.findAll();

        return recognitions.stream()
                .map(rec -> RecognitionDto.builder()
                        .senderId(rec.getSenderId())
                        .receiverId(rec.getReceiverId())
                        .points(rec.getPoints())
                        .message(rec.getMessage())
                        //.createdAt(rec.getCreatedAt())
                        .build())
                .toList();
    }

    public List<LeaderboardDto> getLeaderboard() {
        return repo.getLeaderboard();
    }

    public void deleteRecognition(Long id) {
        repo.deleteById(id);
    }

    public RecognitionDto updateRecognition(Long id, RecognitionDto dto) throws RecognitionServiceException {

        Recognition rec = repo.findById(id)
                .orElseThrow(() -> new RecognitionServiceException("Recognition not found"));

        rec.setPoints(dto.getPoints());
        rec.setMessage(dto.getMessage());

        Recognition updated = repo.save(rec);

        return RecognitionDto.builder()
                //.id(updated.getId())
                .senderId(updated.getSenderId())
                .receiverId(updated.getReceiverId())
                .points(updated.getPoints())
                .message(updated.getMessage())
                //.createdAt(updated.getCreatedAt())
                .build();
    }
}
