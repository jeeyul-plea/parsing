package kr.plea.parsing.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import kr.plea.parsing.data.NewsData;
import kr.plea.parsing.parser.XmlParser;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalWatcherService implements NewsFileService {
	private static final Logger log = LoggerFactory.getLogger(LocalWatcherService.class);
	private WatchService watchService;
	private final XmlParser xmlParser;
	private final NewsService newsService;
	private final Path folderPath = Paths.get("C:\\newsData\\in");

	@PostConstruct
	public void startWatching() throws IOException {
		watchService = FileSystems.getDefault().newWatchService();
		folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
			StandardWatchEventKinds.ENTRY_MODIFY);

		Thread watchThread = new Thread(this::processEvents);
		watchThread.start();
	}

	@PreDestroy
	public void stopWatching() throws IOException {
		watchService.close();
	}

	private void processEvents() {
		while (true) {
			WatchKey key;
			try {
				key = watchService.take();
			} catch (InterruptedException | ClosedWatchServiceException e) {
				return;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				if (kind == StandardWatchEventKinds.OVERFLOW) {
					continue;
				}

				WatchEvent<Path> ev = (WatchEvent<Path>)event;
				Path filename = ev.context();
				File file = new File(folderPath.toFile(), filename.toString());
				String fileNameStr = filename.toString().toLowerCase();

				if (fileNameStr.endsWith(".xml")) {
					if (StandardWatchEventKinds.ENTRY_CREATE.equals(kind)
						|| StandardWatchEventKinds.ENTRY_MODIFY.equals(kind)) {
						createProcess(file);
					}
				}

			}

			boolean valid = key.reset();
			if (!valid) {
				break;
			}

		}
	}

	@Override
	public void createProcess(File xmlFile) {
		try {
			log.info("------------------ watcher operated ------------------");
			NewsData data = xmlParser.parseXml(xmlFile);
			String contentID = data.getHeader().getContentID();
			Path dirPath = Paths.get("C:\\newsData\\out", contentID);
			// ContentId로 폴더 생성
			if (!Files.exists(dirPath)) {
				Files.createDirectories(dirPath);
			}

			//데이터 저장
			newsService.saveData(data, contentID);

			//첨부파일 처리
			fileProcess(data, dirPath);

		} catch (IOException e) {
			log.info(e.getMessage());
		}
	}

	private void fileProcess(NewsData data, Path dirPath) throws IOException {
		for (NewsData.AppendData appendData : data.getNewsContent().getAppendDataList()) {
			String fileName = appendData.getFileName();
			Path filePath = Paths.get("C:\\newsData\\in", fileName);

			// 파일 저장
			Path destPath = dirPath.resolve(fileName);
			if (!Files.exists(destPath)) {
				Files.write(destPath, Files.readAllBytes(filePath));
			}
		}
	}

}
