package com.colak.springtutorial.controller;

import com.colak.springtutorial.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AsyncServiceController {

    private final AsyncService asyncService;

    // Return the result of async task
    // http://localhost:8080/async-task
    @GetMapping("/async-task")
    public CompletableFuture<String> triggerAsyncTask() {
        // nio-8080-exec-1 : triggerAsyncTask Start
        // MyAsyncThread-1 : Async task completed!
        log.info("triggerAsyncTask Start ");
        return asyncService.performAsyncTask();
    }

    // Start async task in the background and return a accepted response.
    // The async may throw an exception, and it will be detected by AsyncUncaughtExceptionHandler
    // http://localhost:8080/async-task1
    @GetMapping("/async-task1")
    public ResponseEntity<String> triggerAsyncTask1() {
        log.info("triggerAsyncTask1 Start ");
        asyncService.performMayThrowExceptionAsyncTask();
        return ResponseEntity.ok("request accepted");
    }
}
