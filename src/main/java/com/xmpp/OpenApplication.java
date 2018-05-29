package com.xmpp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@MapperScan("com.xmpp.open.mapper")
@SpringBootApplication
public class OpenApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(OpenApplication.class, args);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(OpenApplication.class);
//	}
}
