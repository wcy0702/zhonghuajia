package com.wcy.serv.model;

import lombok.Data;

/**
 * 类别 Created by Silence on 2017/3/12.
 */
@Data
public class Category {

	private Long id;

	private Long userId;

	/* 类别名称 */
	private String name;

	/* LOGO */
	private String logo;

	/* 视频数量 */
	private Integer amount;

	/* 人气 */
	private Long popularity;

	/* md5 用于分享链接 */
	private String md5;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getPopularity() {
		return popularity;
	}

	public void setPopularity(Long popularity) {
		this.popularity = popularity;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}
