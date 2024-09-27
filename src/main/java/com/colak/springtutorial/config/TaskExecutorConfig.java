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

    private static final int AWAIT_TERMINATION_SECONDS = 90;

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

        // Custom rejection handler
        // threadPoolTaskExecutor.setRejectedExecutionHandler((Runnable runnable, ThreadPoolExecutor taskExecutor) ->
        //         log.warn("Task rejected, thread pool is full and queue is also full")
        // );

        // There are usually four policies:
        // ThreadPoolExecutor.AbortPolicy: Discard the task and throw RejectedExecutionException.
        // ThreadPoolExecutor.DiscardPolicy: Also discard the task, but do not throw an exception.
        // ThreadPoolExecutor.DiscardOldestPolicy: Discard the task at the front of the queue and then try to execute the task again (repeat this process).
        // ThreadPoolExecutor.CallerRunsPolicy: Runs the rejected task directly in the calling thread,
        // unless the executor has been shut down, in which case the task is discarded.
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(AWAIT_TERMINATION_SECONDS);

        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, obj) ->
                // Handle exception
                log.error("AsyncUncaughtExceptionHandler : ", throwable);
    }
}
