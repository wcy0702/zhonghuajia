package com.wcy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wcy.config.security.AnyUser;
import com.wcy.serv.model.Category;
import com.wcy.serv.model.User;
import com.wcy.serv.service.CategoryService;
import com.wcy.serv.service.UserService;

import lombok.AllArgsConstructor;

/**
 * Created by Silence on 2017/3/10.
 */
@Controller
@AllArgsConstructor
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private CategoryService categoryService;

	/**
	 * 校验邮箱验证
	 */
	@RequestMapping(value = "/validate/{token}", method = RequestMethod.GET)
	public String emailConfirm(@PathVariable("token") String token, Model model) {
		User user = userService.completeSignUp(token);
		if (user != null) {
			model.addAttribute("result", "注册成功，赶紧登陆体验吧！");
		} else {
			model.addAttribute("result", "链接已失效，请重新注册！");
		}
		return "login";
	}

	/**
	 * 用户页
	 */
	@GetMapping("/user")
	public String user(@AuthenticationPrincipal AnyUser user, Model model) {
		if (user != null) {
			String nickName = userService.getNicknameById(user.getId());
			user.setNickname(nickName);
		}
		model.addAttribute("user", user);
		List<Category> categories = categoryService.getByUserId(user.getId());
		model.addAttribute("categories", categories);
		return "user";
	}

}
