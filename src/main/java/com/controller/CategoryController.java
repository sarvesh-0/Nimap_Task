package com.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Category;
import com.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService catService;
	
	@GetMapping
	public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
		return catService.getAllCategories(page, size);
	}
	
	@PostMapping
	public Category createCategory(@RequestBody Category category) {
		return catService.createCategory(category);
	}
	
	@GetMapping("/{cId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("cId") int cId){
		return catService.getCategoryById(cId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{cId}")
	public ResponseEntity<Category> updateCategory(@PathVariable("cId") int cId, @RequestBody Category category){
		return ResponseEntity.ok(catService.updateCategory(cId, category));
	}
	
	@DeleteMapping("/{cId}")
	public ResponseEntity<Void> deleteCategory(@PathVariable("cId") int cId){
		catService.deleteCategory(cId);
		return ResponseEntity.noContent().build();
	}
}
