package kr.plea.parsing.integration.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.plea.parsing.integration.data.NewsData;

@Mapper
public interface NewsDataMapper {
	void insertHeader(NewsData.Header header);
	void insertMetadata(NewsData.Metadata metadata,String contentID);
	void insertNewsContent(NewsData.NewsContent newsContent, String contentID);
	void insertAppendData(NewsData.AppendData appendData, String contentID);
}
