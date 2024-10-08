package kr.plea.parsing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("kr.plea.parsing.mapper")
public class ParsingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParsingApplication.class, args);
	}

}
