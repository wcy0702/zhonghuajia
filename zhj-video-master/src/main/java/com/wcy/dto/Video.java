package com.wcy.dto;

import lombok.Data;

/**
 * 视频 Created by Silence on 2016/12/2.
 */
@Data
public class Video {

	/* 视频名称 */
	private String title;

	/* 视频图片 */
	private String image;

	/* 视频播放地址 */
	private String playUrl;

	/* 播放类型 */
	private String type;

	/* [版权] 视频源地址 */
	private String value;

	/* [版权] 视频提供方 */
	private String provider;

	/* [版权] 视频解析方名称 */
	private String parserName;

	/* [版权] 视频解析方官网 */
	private String parser;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPlayUrl() {
		return playUrl;
	}

	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getParserName() {
		return parserName;
	}

	public void setParserName(String parserName) {
		this.parserName = parserName;
	}

	public String getParser() {
		return parser;
	}

	public void setParser(String parser) {
		this.parser = parser;
	}

}
