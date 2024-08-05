// package kr.plea.parsing.integration.processor;
//
// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.List;
//
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;
//
// import kr.plea.parsing.integration.data.NewsData;
// import kr.plea.parsing.integration.mapper.NewsDataMapper;
// import kr.plea.parsing.integration.parser.XmlParser;
// import lombok.RequiredArgsConstructor;
//
// @Component
// @RequiredArgsConstructor
// public class pollingProcess implements  FileProcessor{
//
// 	private final XmlParser xmlParser;
// 	private final NewsDataMapper newsDataMapper;
//
// 	@Override
// 	public void processFile(File xmlFile) {
// 		try {
// 			NewsData data = xmlParser.parseXml(xmlFile);
//
// 			String contentID = data.getHeader().getContentID();
// 			Path dirPath = Paths.get("C:\\newsData\\out", contentID);
// 			if (Files.exists(dirPath)) {
// 				return;
// 			}
// 			// ContentId로 폴더 생성
// 			Files.createDirectories(dirPath);
//
// 			//데이터 저장
// 			saveData(data, contentID);
//
// 			//첨부파일 처리
// 			for (NewsData.AppendData appendData : data.getNewsContent().getAppendDataList()) {
//
// 				String fileName = appendData.getFileName();
// 				Path filePath = Paths.get("C:\\newsData\\in", fileName);
//
// 				// 파일 저장
// 				Path destPath = dirPath.resolve(fileName);
// 				Files.write(destPath, Files.readAllBytes(filePath));
// 			}
//
// 		} catch (IOException e) {
// 			e.printStackTrace();
// 		}
// 	}
//
// 	@Transactional
// 	public void saveData(NewsData data, String contentID) {
// 		newsDataMapper.insertHeader(data.getHeader());
// 		newsDataMapper.insertMetadata(data.getMetadata(), contentID);
// 		newsDataMapper.insertNewsContent(data.getNewsContent(), contentID);
// 		List<NewsData.AppendData> appendDataList = data.getNewsContent().getAppendDataList();
// 		for (NewsData.AppendData appendData : appendDataList) {
// 			newsDataMapper.insertAppendData(appendData, contentID);
// 		}
// 	}
// }
