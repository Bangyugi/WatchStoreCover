package com.group2.watchstorecover;

import com.group2.watchstorecover.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchStoreCoverApplication implements CommandLineRunner {
    @Autowired
    private RedisService redisService;

    public static void main(String[] args) {
        SpringApplication.run(WatchStoreCoverApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
