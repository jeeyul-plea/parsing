package kr.plea.parsing.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Configuration
public class JacksonConfig {
	@Bean
	public XmlMapper xmlMapper() {
		return new XmlMapper();
	}
}
