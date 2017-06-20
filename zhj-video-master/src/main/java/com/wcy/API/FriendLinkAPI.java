package com.wcy.API;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcy.dto.SimpleResponse;
import com.wcy.serv.model.FriendLink;
import com.wcy.serv.service.FriendLinkService;

import lombok.AllArgsConstructor;

/**
 * Created by Silence on 2017/3/9.
 */
@RestController
@RequestMapping("/friend")
@AllArgsConstructor
public class FriendLinkAPI {

	@Resource
	private FriendLinkService service;

	/**
	 * 申请友情链接，自动识别
	 */
	@GetMapping("/join")
	public SimpleResponse join(HttpServletRequest request) {
		SimpleResponse response = new SimpleResponse();
		String name = request.getParameter("name");
		String domain = request.getParameter("domain");
		boolean success = service.insert(name, domain);
		if (success) {
			response.setCode(100);
		} else {
			response.setCode(200);
			response.setMessage("申请失败，请按照说明操作哦！");
		}
		return response;
	}

	/**
	 * 首页-友情链接列表
	 */
	@GetMapping("/list-home")
	public List<FriendLink> homeFriendLink() {
		return service.listHome();
	}

	/**
	 * 友情链接页-列表
	 */
	@GetMapping("/list")
	public List<FriendLink> friendLinkList(HttpServletRequest request) {
		int index = Integer.valueOf(request.getParameter("index"));
		int size = Integer.valueOf(request.getParameter("size"));
		return service.listAll(index, size);
	}

}
