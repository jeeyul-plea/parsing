package kr.plea.parsing.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.plea.parsing.data.dto.NewsContentFindDto;
import kr.plea.parsing.data.NewsData;

@Mapper
public interface NewsDataMapper {
	void insertContent(NewsData news);

	void insertAppendData(NewsData.AppendData appendData, String contentID);

	void insertContentHistory(NewsData news);

	void insertAppendDataHistory(NewsData.AppendData appendData, String contentID);

	NewsContentFindDto findContent(String contentID);

	void updateOldContent(String contentID);

	void updateOldAppendData(String contentID);

	NewsContentFindDto findOne(String contentID);

	void setContentDeleteState(String contentID);

	void setAppendDateDeleteState(String contentID);
}
