package com.wcy.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wcy.serv.service.NewsService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class NewsController {

	/**
	 * 管理员ID，管理员的分类及收藏将被展示到 /news
	 */
	private static final Long USER_ID = 1000L;

	@Resource
	private NewsService newsService;

	@GetMapping("/news")
	public String news(Model model) {
		model.addAttribute("items", newsService.getNewItems(USER_ID));
		model.addAttribute("categories", newsService.getAllCategories(USER_ID));
		model.addAttribute("navIndex", 0);
		return "news";
	}

}
