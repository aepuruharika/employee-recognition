package com.gl.rewardservice.exception;

public class RewardAlreadyAssignedException extends RuntimeException {
    public RewardAlreadyAssignedException(String message) {
        super(message);
    }
}