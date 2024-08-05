package kr.plea.parsing.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsContentFindDto {
	private String contentId;
	private String action;
	private String serviceType;
	private String sendData;
	private String sendTime;
	private String source;
	private String writer;
	private String categoryCode;
	private String deskCode;
	private String deskValue;
	private String title;
	private String subTitle;
	private String comment;
	private String body;
	private Boolean isUsed;
	private Boolean isDeleted;

	public NewsContentFindDto(NewsContentFindDto newsContent) {
		this.contentId = newsContent.getContentId();
		this.action = newsContent.getAction();
		this.serviceType = newsContent.getServiceType();
		this.sendData = newsContent.getSendData();
		this.sendTime = newsContent.getSendTime();
		this.source = newsContent.getSource();
		this.writer = newsContent.getWriter();
		this.categoryCode = newsContent.getCategoryCode();
		this.deskCode = newsContent.getDeskCode();
		this.deskValue = newsContent.getDeskValue();
		this.title = newsContent.getTitle();
		this.subTitle = newsContent.getSubTitle();
		this.comment = newsContent.getComment();
		this.body = newsContent.getBody();
		this.isUsed = newsContent.getIsUsed();
		this.isDeleted = newsContent.getIsDeleted();
	}
}
