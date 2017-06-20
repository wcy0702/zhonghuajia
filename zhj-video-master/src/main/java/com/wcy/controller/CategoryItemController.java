package com.wcy.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wcy.config.security.AnyUser;
import com.wcy.serv.model.Category;
import com.wcy.serv.model.CategoryItem;
import com.wcy.serv.model.User;
import com.wcy.serv.service.CategoryItemService;
import com.wcy.serv.service.CategoryService;
import com.wcy.serv.service.UserService;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by Silence on 2017/3/14.
 */
@Controller
@AllArgsConstructor
public class CategoryItemController {

  @Resource
  private  UserService userService;
  
  @Resource
  private  CategoryItemService categoryItemService;

  @Resource
  private  CategoryService categoryService;

  /**
   * 查看分类的所有子项，仅作者本人可查看
   */
  @GetMapping("/user/item/{id}")
  public String list(@AuthenticationPrincipal AnyUser user, @PathVariable("id") Long id, Model model){
    List<CategoryItem> items = categoryItemService.list(id, user.getId());
    Category category = categoryService.getById(id, user.getId());
    model.addAttribute("user", user);
    model.addAttribute("category", category);
    model.addAttribute("items", items);
    return "items";
  }

  /**
   * 分类分享，用于暴露给所有用户
   */
  @GetMapping("/share/{md5}")
  public String share(@PathVariable("md5") String md5, Model model){
    Category category = categoryService.getByMd5(md5);
    if (category == null){
      return "error";
    }
    User user = userService.getById(category.getUserId());
    List<CategoryItem> items = categoryItemService.list(category.getId(), user.getId());
    model.addAttribute("user", user);
    model.addAttribute("category", category);
    model.addAttribute("items", items);
    return "share";
  }

}
