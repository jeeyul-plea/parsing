package kr.plea.parsing.parser;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.plea.parsing.data.NewsData;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class XmlParser {

	private final XmlMapper xmlMapper;

	public NewsData parseXml(File file) throws IOException {
		return xmlMapper.readValue(file, NewsData.class);
	}
}
