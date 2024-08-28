package com.colak.springtutorial.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// See https://medium.com/@shishirkumar28/dynamic-thread-pool-management-with-spring-boot-57ac6c70225c
@RestController
@RequestMapping("/api/threadpool")
@RequiredArgsConstructor
public class DynamicThreadPoolController {

    private final ThreadPoolTaskExecutor taskExecutor;

    // http://localhost:8080/api/threadpool/corePoolSize
    // Endpoint to get current core pool size
    @GetMapping("/corePoolSize")
    public int getCorePoolSize() {
        return taskExecutor.getCorePoolSize();
    }

    // Endpoint to set core pool size
    @PostMapping("/corePoolSize")
    public String setCorePoolSize(@RequestParam int corePoolSize) {
        taskExecutor.setCorePoolSize(corePoolSize);
        return "Core pool size updated to " + corePoolSize;
    }
}

