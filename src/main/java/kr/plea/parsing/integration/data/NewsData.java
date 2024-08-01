package kr.plea.parsing.integration.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsData {
	@JsonProperty("Header")
	private Header header;

	@JsonProperty("Metadata")
	private Metadata metadata;

	@JsonProperty("NewsContent")
	private NewsContent newsContent;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Header {
		@JsonProperty("Action")
		private String action;

		@JsonProperty("ServiceType")
		private String serviceType;

		@JsonProperty("ContentID")
		private String contentID;

		@JsonProperty("SendDate")
		private String sendDate;

		@JsonProperty("SendTime")
		private String sendTime;


	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Metadata {
		@JsonProperty("Category")
		private Category category;

		@JsonProperty("Source")
		private String source;

		@JsonProperty("Writer")
		private String writer;

		@JsonProperty("Desk")
		private Desk desk;

		@Data
		public static class Category {
			@JacksonXmlProperty(isAttribute = true, localName = "code")
			private String code;
		}

		@Data
		public static class Desk {
			@JacksonXmlProperty(isAttribute = true, localName = "code")
			private String code;

			@JacksonXmlText
			private String value;
		}
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class NewsContent {
		@JsonProperty("Title")
		private String title;

		@JsonProperty("SubTitle")
		private String subTitle;

		@JsonProperty("Comment")
		private String comment;

		@JsonProperty("Body")
		private String body;

		@JacksonXmlElementWrapper(useWrapping = false)
		@JacksonXmlProperty(localName = "AppendData")
		private List<AppendData> appendDataList;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class AppendData {
		@JacksonXmlProperty(isAttribute = true, localName = "mimeType")
		private String mimeType;

		@JacksonXmlProperty(localName = "Title")
		private String title;

		@JacksonXmlProperty(localName = "Caption")
		private String caption;

		@JacksonXmlProperty(localName = "FileName")
		private String fileName;
	}
}
