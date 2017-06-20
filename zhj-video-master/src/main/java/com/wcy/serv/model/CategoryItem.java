package com.wcy.serv.model;

import lombok.Data;

/**
 * 类别下的内容 Created by Silence on 2017/3/12.
 */
@Data
public class CategoryItem {

	private Long id;

	/* 用户 ID */
	private Long userId;

	/* 类别 ID */
	private Long categoryId;

	/* 详见 CategoryItemType */
	private String type;

	/* 标题 */
	private String name;

	/* 图片 */
	private String image;

	/* 地址 */
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
