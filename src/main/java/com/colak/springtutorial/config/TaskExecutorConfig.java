package com.colak.springtutorial.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@Slf4j
public class TaskExecutorConfig implements AsyncConfigurer {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // Core number of threads
        threadPoolTaskExecutor.setCorePoolSize(1);
        // Maximum number of threads
        threadPoolTaskExecutor.setMaxPoolSize(5);
        // Capacity for queued tasks
        threadPoolTaskExecutor.setQueueCapacity(500);
        threadPoolTaskExecutor.setThreadNamePrefix("MyAsyncThread-");
        threadPoolTaskExecutor.setRejectedExecutionHandler(this::logRejection);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    private void logRejection(Runnable runnable, ThreadPoolExecutor taskExecutor) {
        log.warn("Task rejected, thread pool is full and queue is also full");
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, obj) ->
            // Handle exception
            log.error("AsyncUncaughtExceptionHandler : ", throwable);
    }
}
