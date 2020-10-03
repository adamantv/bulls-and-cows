package com.mygame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Класс для конфигурации и запуска приложения
 */

@SpringBootApplication
public class MyGameApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyGameApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyGameApplication.class);
	}
}
