package com.nusamandiri.monitoringgudang;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class MonitoringGudangApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringGudangApplication.class, args);
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

}
