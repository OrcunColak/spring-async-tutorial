package com.colak.springasynctutorial.controller;

import com.colak.springasynctutorial.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AsyncServiceController {

    private final AsyncService asyncService;

    // http://localhost:8080/async-task
    @GetMapping("/async-task")
    public CompletableFuture<String> triggerAsyncTask() {
        // nio-8080-exec-1 : triggerAsyncTask Start
        // MyAsyncThread-1 : Async task completed!
        log.info("triggerAsyncTask Start ");
        return asyncService.performAsyncTask();
    }
}
