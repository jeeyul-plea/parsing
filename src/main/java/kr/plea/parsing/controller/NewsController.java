package kr.plea.parsing.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.plea.parsing.data.dto.NewsContentFindDto;
import kr.plea.parsing.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Slf4j
public class NewsController {
	private final NewsService newsService;

	@DeleteMapping("/{contentID}")
	public ResponseEntity<Void> deleteData(@PathVariable("contentID") String contentID) {
		newsService.deleteData(contentID);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{contentID}")
	public ResponseEntity<NewsContentFindDto> getNews(@PathVariable("contentID") String contentID){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		NewsContentFindDto findNews = newsService.findNews(contentID);
		stopWatch.stop();
		long totalTimeMillis = stopWatch.getTotalTimeMillis();
		log.info("totalTimeMillis = {}", totalTimeMillis);

		return new ResponseEntity(findNews, HttpStatus.OK);
	}

}
