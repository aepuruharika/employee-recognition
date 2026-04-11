package com.gl.rewardservice.controller;

import com.gl.rewardservice.Dto.RewardRequestDto;
import com.gl.rewardservice.entity.Reward;
import com.gl.rewardservice.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rewards")
@RequiredArgsConstructor
public class RewardController {

    @Autowired
    private RewardService service;

    @PostMapping("/assign")
    public ResponseEntity<RewardRequestDto> assign(@RequestBody RewardRequestDto dto) {

        RewardRequestDto response =
                service.assignReward(dto.getUserId(), dto.getMilestonePoints());

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<RewardRequestDto> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(service.getByUser(userId));
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<RewardRequestDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}