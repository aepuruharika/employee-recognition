package com.gl.app.controller;

import com.gl.app.dto.LeaderboardDto;
import com.gl.app.dto.RecognitionDto;
import com.gl.app.entity.Recognition;
import com.gl.app.exception.BandLevelNotFoundException;
import com.gl.app.exception.RecognitionServiceException;
import com.gl.app.service.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recognition")
public class RecognitionController {

    @Autowired
    private RecognitionService service;

    @PostMapping("/give")
    public ResponseEntity<Recognition> giveRecognition(@RequestBody RecognitionDto rec) throws RecognitionServiceException {
        return ResponseEntity.ok(service.giveRecognition(rec));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<RecognitionDto>> getRecognitionsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(service.getRecognitionsByUser(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecognitionDto>> getAllRecognitions() {
        return ResponseEntity.ok(service.getAllRecognitions());
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<LeaderboardDto>> getLeaderboard() {
        return ResponseEntity.ok(service.getLeaderboard());
    }

    @GetMapping("/leaderboard/{band}")
    public ResponseEntity<List<LeaderboardDto>> getLeaderboardByBand(@PathVariable String band) throws BandLevelNotFoundException {
        return ResponseEntity.ok(service.getLeaderboardByBandLevel(band));
    }


    @DeleteMapping("/{id}")
    public void deleteRecognition(@PathVariable Long id) {
        service.deleteRecognition(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RecognitionDto> updateRecognition(Long id, @RequestBody RecognitionDto rec) throws RecognitionServiceException {
        return ResponseEntity.ok(service.updateRecognition(id,rec));
    }
}
