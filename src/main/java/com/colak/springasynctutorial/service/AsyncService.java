package com.colak.springasynctutorial.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AsyncService {

    private SecureRandom random = new SecureRandom();
    @Async
    public CompletableFuture<String> performAsyncTask() {
        // Simulate a time-consuming task
        try {
            TimeUnit.SECONDS.sleep(5); // Sleep for 5 seconds
            log.info("Async task completed!");
            return CompletableFuture.completedFuture("Async task completed!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture("Async task failed to complete!");
    }

    @Async
    public void performMayThrowExceptionAsyncTask() {
        // Generate a random integer between 0 and 999 (inclusive)
        int randomNumber = random.nextInt(1000);
        if (randomNumber < 100) {
            // Simulate a time-consuming task
            try {
                TimeUnit.SECONDS.sleep(5); // Sleep for 5 seconds
                log.info("Async task completed!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            throw new RuntimeException("Async task failed to complete!");
        }
    }
}
