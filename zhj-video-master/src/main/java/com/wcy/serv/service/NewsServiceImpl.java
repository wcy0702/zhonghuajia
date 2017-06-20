package com.wcy.serv.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wcy.serv.mapper.CategoryItemMapper;
import com.wcy.serv.mapper.CategoryMapper;
import com.wcy.serv.model.Category;
import com.wcy.serv.model.CategoryItem;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {

	@Resource
	private CategoryMapper categoryMapper;

	@Resource
	private CategoryItemMapper categoryItemMapper;

	@Override
	public List<CategoryItem> getNewItems(Long userId) {
		return categoryItemMapper.selectNews(userId);
	}

	@Override
	public List<Category> getAllCategories(Long userId) {
		return categoryMapper.selectByUserId(userId);
	}

}
