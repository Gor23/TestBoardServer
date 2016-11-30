package com.riversoft.eventsion.display

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class DisplayControlApplication{

	public static void main(String[] args) {
		SpringApplication.run DisplayControlApplication, args
	}
}

