package com.store.price_fetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.store.price_fetcher")
@EnableScheduling
public class PriceFetcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceFetcherApplication.class, args);
	}

}
