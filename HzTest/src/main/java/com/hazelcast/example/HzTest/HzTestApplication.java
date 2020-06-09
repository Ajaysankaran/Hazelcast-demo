package com.hazelcast.example.HzTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;

@SpringBootApplication
@EnableCaching
public class HzTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HzTestApplication.class, args);
	}

}
