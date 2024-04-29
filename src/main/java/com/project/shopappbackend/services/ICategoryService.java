package com.project.shopappbackend.services;


import com.project.shopappbackend.dtos.CategoryDTO;
import com.project.shopappbackend.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO category);
    Category getCategoryById(long id);
    List<Category> getAllCategories();
    Category updateCategory(long categoryId, CategoryDTO category);
    void deleteCategory(long id);
}
