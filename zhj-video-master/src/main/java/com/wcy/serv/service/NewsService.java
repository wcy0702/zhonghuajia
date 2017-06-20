package com.wcy.serv.service;

import java.util.List;

import com.wcy.serv.model.Category;
import com.wcy.serv.model.CategoryItem;

public interface NewsService {

    List<CategoryItem> getNewItems(Long userId);

    List<Category> getAllCategories(Long userId);

}
