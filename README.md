# Return CompletableFuture from Controller

The original idea is from  
https://medium.com/@basecs101/spring-boot-async-annotation-make-api-calls-asynchronous-2024-latest-dcce878d0fe2

# Return Callable or DeferredResult from Controller

The original idea is from
https://dzone.com/articles/spring-and-servlet-30-asynchronous-processing

# Async Task Uncaught Exception Handler

The original idea is from  
https://blog.stackademic.com/handling-background-processing-in-spring-boot-ae94aa03b869

# Graceful Shutdown

The original idea is from  
https://medium.com/@office.yeon/graceful-shutdown-in-spring-boot-with-sync-and-async-tasks-a8f8d89ee252

```
server.shutdown=graceful
```
Causes Tomcat to stop accepting new requests, waits for active requests to complete, and handles any interruptions or abort signals.

```
spring.lifecycle.timeout-per-shutdown-phase=90s
```
The timeout-per-shutdown-phase property sets the maximum amount of time that Spring will wait for beans in each phase to shut down. If the beans in a particular phase do not stop within the specified time, Spring will proceed to the next phase. By default, this timeout is set to 30 seconds.

```
@Bean
 public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
  ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

  threadPoolTaskExecutor.setTaskDecorator(new MDCTaskDecorator());
  threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
  threadPoolTaskExecutor.setAwaitTerminationSeconds(AWAIT_TERMINATION_SECONDS);
  threadPoolTaskExecutor.initialize();

  return threadPoolTaskExecutor;
 }

 @Bean
 public Executor taskExecutor(final ThreadPoolTaskExecutor threadPoolTaskExecutor) {
  return new DelegatingSecurityContextExecutor(threadPoolTaskExecutor);
 }
```
This causes orderly shutdown of ThreadPoolTaskExecutor

# Shutdown Actuator

Run

```
curl -X POST localhost:8080/actuator/shutdown
```
