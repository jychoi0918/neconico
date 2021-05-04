package com.neconico.neconico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class NeconicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeconicoApplication.class, args);
	}

}
