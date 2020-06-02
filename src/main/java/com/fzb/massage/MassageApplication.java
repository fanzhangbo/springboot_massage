package com.fzb.massage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fzb.massage.mapper")
public class MassageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MassageApplication.class, args);

	}

}
