package com.gl.app.controller;

import com.gl.app.dto.*;
import com.gl.app.entity.Recognition;
import com.gl.app.service.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recognitions")
@CrossOrigin
public class RecognitionController {

    @Autowired
    private RecognitionService service;

    @PostMapping
    public Recognition giveRecognition(@RequestBody RecognitionDto dto) throws Exception {
        return service.giveRecognition(dto);
    }

    @GetMapping("/{userId}")
    public List<RecognitionDto> getByUser(@PathVariable String userId) {
        return service.getRecognitionsByUser(userId);
    }

    @GetMapping
    public List<RecognitionDto> getAll() {
        return service.getAllRecognitions();
    }

    @GetMapping("/leaderboard")
    public List<LeaderboardDto> leaderboard() {
        return service.getLeaderboard();
    }

    @GetMapping("/leaderboard/{band}")
    public List<LeaderboardDto> leaderboardByBand(@PathVariable String band) throws Exception {
        return service.getLeaderboardByBandLevel(band);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteRecognition(id);
        return "Deleted successfully";
    }

    @PutMapping("/{id}")
    public RecognitionDto update(@PathVariable Long id, @RequestBody RecognitionDto dto) throws Exception {
        return service.updateRecognition(id, dto);
    }
}