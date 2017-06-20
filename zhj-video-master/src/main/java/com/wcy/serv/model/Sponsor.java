package com.wcy.serv.model;

import java.util.Date;

import lombok.Data;

/**
 * 捐助表 Created by Silence on 2017/4/19.
 */
@Data
public class Sponsor {

	private int id;

	// 昵称
	private String nickname;

	// 捐助来源
	private String from;

	// 捐助金额
	private int money;

	// 捐助时间
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
