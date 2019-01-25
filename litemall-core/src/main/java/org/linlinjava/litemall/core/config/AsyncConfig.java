package org.linlinjava.litemall.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
//为了让@Async注解能够生效，还需要在Spring Boot的主程序中配置@EnableAsync
@EnableAsync
public class AsyncConfig {
}
