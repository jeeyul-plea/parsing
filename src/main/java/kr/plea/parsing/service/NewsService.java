package kr.plea.parsing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.plea.parsing.data.NewsData;
import kr.plea.parsing.data.dto.NewsContentFindDto;
import kr.plea.parsing.mapper.NewsDataMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NewsService {
	private static final Logger log = LoggerFactory.getLogger(NewsService.class);
	private final NewsDataMapper newsDataMapper;

	@Transactional
	public void saveData(NewsData data, String contentID) {
		List<NewsData.AppendData> appendDataList = data.getNewsContent().getAppendDataList();
		if (!checkExist(contentID)) {
			newsDataMapper.insertContent(data);
			newsDataMapper.insertContentHistory((data));

			for (NewsData.AppendData appendData : appendDataList) {
				newsDataMapper.insertAppendData(appendData, contentID);
				newsDataMapper.insertAppendDataHistory(appendData, contentID);
			}
		} else {
			newsDataMapper.updateOldContent(contentID);
			newsDataMapper.insertContentHistory(data);
			newsDataMapper.updateOldAppendData(contentID);

			for (NewsData.AppendData appendData : appendDataList) {
				newsDataMapper.insertAppendDataHistory(appendData, contentID);
			}
		}
	}

	// @Transactional
	// public void updateData(NewsData data, String contentID) {
	// 	List<NewsData.AppendData> appendDataList = data.getNewsContent().getAppendDataList();
	// 	newsDataMapper.updateOldContent(contentID);
	// 	newsDataMapper.insertContentHistory(data);
	// 	newsDataMapper.updateOldAppendData(contentID);
	//
	// 	for (NewsData.AppendData appendData : appendDataList) {
	// 		newsDataMapper.insertAppendDataHistory(appendData, contentID);
	// 	}
	// }

	@Transactional
	public void deleteData(String contentID) {
		if (checkExist(contentID)) {
			newsDataMapper.setContentDeleteState(contentID);
			newsDataMapper.setAppendDateDeleteState(contentID);
		} else {
			log.info("존재하지 않는 뉴스 데이터 입니다.");
		}
	}

	// public NewsContentFindDto getActiveData(String contentID) {
	// 	if(!checkExist(contentID)) {
	// 		log.info("존재하지 않는 뉴스 데이터 입니다.");
	// 	}
	// 	return newsDataMapper.findContent(contentID);
	// }

	@Cacheable(key = "#contentID", value = "newsContent", cacheManager = "redisCacheManager")
	public NewsContentFindDto findNews(String contentID) {
		if(checkExist(contentID)) {
			log.info("db를 통한 데이터 find");
			NewsContentFindDto jsonData = newsDataMapper.findContent(contentID);
			log.info("Fetched data: {}" , jsonData);
			return jsonData;
		}
		else return null;
	}

	private boolean checkExist(String contentID) {
		if (newsDataMapper.findContent(contentID) != null)
			return true;
		return false;
	}
}
