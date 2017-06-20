package com.wcy.serv.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 友情链接 Created by Silence on 2017/3/8.
 */
@Data
@NoArgsConstructor
public class FriendLink {

	private Integer id;

	/* 网站名称 */
	private String name;

	/* 网站域名 */
	private String domain;

	/* 展示在首页 YES | NO */
	private String show;

	public FriendLink() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FriendLink(String name, String domain, String show) {
		this.name = name;
		this.domain = domain;
		this.show = show;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

}
