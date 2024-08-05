package kr.plea.parsing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.plea.parsing.service.NewsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class newsController {
	private final NewsService newsService;

	@DeleteMapping("/{contentID}")
	public ResponseEntity<Void> deleteNews(@PathVariable("contentID") String contentID) {
		newsService.deleteData(contentID);
		return ResponseEntity.ok().build();
	}
}
