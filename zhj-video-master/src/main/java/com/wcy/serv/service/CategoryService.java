package com.wcy.serv.service;

import org.springframework.stereotype.Service;

import com.wcy.serv.model.Category;

import java.util.List;

/**
 * Category Service
 * Created by Silence on 2017/3/13.
 */
public interface CategoryService {

  /* 查询用户所有的分类信息 */
  List<Category> getByUserId(Long userId);

  /* 根据MD5查询分类信息 */
  Category getByMd5(String md5);

  /* 根据ID查询分类信息 */
  Category getById(Long id, Long userId);

  /* 根据ID删除分类信息 */
  boolean deleteByUserIdAndId(Long userId, Long id);

  /* 插入一条记录 */
  boolean insert(Long userId, String name);

}
