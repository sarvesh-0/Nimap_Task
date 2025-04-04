package com.services;

import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dao.ProductRepository;
import com.model.Product;


@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	public Page<Product> getAllProducts(int page, int size){
		return productRepo.findAll(PageRequest.of(page, size));
	}
	
	public Product createProduct(Product product){
		return productRepo.save(product);
	}
	
	public Optional<Product> getProductById(int pId){
		return productRepo.findById(pId);
	}
	
	public Product updateProduct(int pId, Product productDetails) {
	     Product product = productRepo.findById(pId).orElseThrow(() -> new RuntimeException("Product not found"));
	     product.setProductName(productDetails.getProductName());
	     product.setPrice(productDetails.getPrice());
	     product.setCategory(productDetails.getCategory());
	     return productRepo.save(product);
	}
	
	public void deleteProduct(int pId){
		productRepo.deleteById(pId);
	}
}
