package com.colak.springasynctutorial.controller;

import com.colak.springasynctutorial.service.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class AsyncServiceController {

    private final AsyncService asyncService;

    // http://localhost:8080/async-task
    @GetMapping("/async-task")
    public CompletableFuture<String> triggerAsyncTask() {
        return asyncService.performAsyncTask();
    }
}
