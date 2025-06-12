package com.br.ff.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class AsyncConfig {
	@Bean
	public AsyncSupportConfigurer configureAsync(WebMvcConfigurer configurer) {
	    AsyncSupportConfigurer asyncConfigurer = new AsyncSupportConfigurer();
	    asyncConfigurer.setDefaultTimeout(60000); // 1 minuto
	    asyncConfigurer.setTaskExecutor(mvcTaskExecutor());
	    return asyncConfigurer;
	}

	@Bean
	public AsyncTaskExecutor mvcTaskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(10);
	    executor.setMaxPoolSize(50);
	    executor.setQueueCapacity(100);
	    executor.setThreadNamePrefix("mvc-task-");
	    return executor;
	}
}
