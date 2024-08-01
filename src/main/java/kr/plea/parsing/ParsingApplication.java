package kr.plea.parsing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("kr.plea.parsing.integration.mapper")
public class ParsingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParsingApplication.class, args);
	}

}
