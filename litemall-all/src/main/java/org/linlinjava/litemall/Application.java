package org.linlinjava.litemall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"org.linlinjava.litemall"})
@MapperScan("org.linlinjava.litemall.db.dao")
@EnableTransactionManagement
@EnableScheduling
public class Application {
    /**
     * 也就是说两个后台服务和静态文件都部署在一个Spring Boot可执行jar包中。
     *
     *
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}