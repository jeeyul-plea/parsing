// package kr.plea.parsing.integration.config;
//
// import java.io.File;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.integration.channel.DirectChannel;
// import org.springframework.integration.config.EnableIntegration;
// import org.springframework.integration.core.MessageSource;
// import org.springframework.integration.dsl.IntegrationFlow;
// import org.springframework.integration.dsl.Pollers;
// import org.springframework.integration.file.FileReadingMessageSource;
// import org.springframework.integration.file.filters.SimplePatternFileListFilter;
// import org.springframework.integration.scheduling.PollerMetadata;
// import org.springframework.messaging.MessageChannel;
//
// import kr.plea.parsing.integration.processor.pollingProcess;
//
// @Configuration
// @EnableIntegration
// public class FileIntegrationConfig {
// 	private static final String INPUT_DIR = "C:\\newsData\\in";
// 	private static final String FILE_PATTERN = "*.xml";
//
// 	//파일을 읽은 후 처리할 메시지 채널을 생성
// 	@Bean
// 	public MessageChannel fileInputChannel() {
// 		return new DirectChannel();
// 	}
//
// 	//파일을 읽기 위한 FileReadingMessageSource를 생성하고, 지정된 디렉토리에서 파일을 읽도록 설정, 파일패턴을 통해 원하는 파일만 읽음
// 	@Bean
// 	public MessageSource<File> fileReadingMessageSource() {
// 		FileReadingMessageSource source = new FileReadingMessageSource();
// 		source.setDirectory(new File(INPUT_DIR));
// 		source.setFilter(new SimplePatternFileListFilter(FILE_PATTERN));
// 		return source;
// 	}
//
// 	// 파일을 주기적으로 읽기 위함 (10초 마다 읽음)
// 	@Bean
// 	public PollerMetadata poller() {
// 		return Pollers.fixedDelay(10000).getObject();
// 	}
//
// 	// 파일을 읽은 후 처리할 통합 플로우 정의 fileReadingMessageSource 에서 읽은 파일을 fileProcessor의 processFile 메서드로 전달
// 	@Bean
// 	public IntegrationFlow fileIntegrationFlow(FileReadingMessageSource fileReadingMessageSource,
// 		pollingProcess pollingProcess) {
// 		return IntegrationFlow.from(fileReadingMessageSource, config -> config.poller(poller()))
// 			.handle(pollingProcess, "processFile")
// 			.get();
// 	}
// }
