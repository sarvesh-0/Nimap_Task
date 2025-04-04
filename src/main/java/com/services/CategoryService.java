package com.services;

import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dao.CategoryRepository;
import com.model.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepo;
	
	public Page<Category> getAllCategories(int page, int size){
		return categoryRepo.findAll(PageRequest.of(page, size));
	}
	
	public Category createCategory(Category category){
		return categoryRepo.save(category);
	}
	
	public Optional<Category> getCategoryById(int cId){
		return categoryRepo.findById(cId);
	}
	
	public Category updateCategory(int cId, Category categoryDetails){
		Category category = categoryRepo.findById(cId).orElseThrow(() -> new RuntimeException("Category not found"));
		category.setCategoryName(categoryDetails.getCategoryName());
		return categoryRepo.save(category);
	}
	
	public void deleteCategory(int cId){
		categoryRepo.deleteById(cId);
	}
}
