package com.wcy.dto;

import lombok.Data;

/**
 * 文章 Created by Silence on 2017/5/1.
 */
@Data
public class Article {

	/* 标题 */
	private String title;

	/* 图片 */
	private String image;

	/* 内容 */
	private String content;

	/* 作者 */
	private String author;

	/* 发布时间 */
	private String time;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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
