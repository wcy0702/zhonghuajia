package com.wcy.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 自定义的 User 对象 Created by Silence on 2017/4/15.
 */
public class AnyUser extends User {

	private Long id;

	private String nickname;

	AnyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "AnyUser [id=" + id + ", nickname=" + nickname + "]";
	}

}
