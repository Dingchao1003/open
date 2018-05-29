package com.xmpp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@MapperScan("com.xmpp.open.mapper")
@SpringBootApplication
public class OpenApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenApplication.class, args);
	}
}
