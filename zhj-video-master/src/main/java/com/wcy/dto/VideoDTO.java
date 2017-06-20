package com.wcy.dto;

import lombok.Data;

/**
 * VideoDTO Created by Silence on 2017/2/13.
 */
@Data
public class VideoDTO {

	// 视频是否有效
	private Boolean available;
	// 视频标题
	private String title;
	// 视频背景图片
	private String image;
	// 视频地址
	private String value;

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
