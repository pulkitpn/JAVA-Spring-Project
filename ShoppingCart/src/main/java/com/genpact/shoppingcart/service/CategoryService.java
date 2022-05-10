package com.genpact.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.shoppingcart.model.Category;
import com.genpact.shoppingcart.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

// Here all the services we are providing
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	// service to add category
	public void addCategory(Category category) {
		this.categoryRepository.save(category);
	}

	// service to fetch all the category
	public List<Category> getAllCategory() {
		return this.categoryRepository.findAll();
	}

	// service to delete category by id
	public void deleteCategoryById(int categoryId) {
		System.out.println("CAT ID IN SERVICE = "+categoryId);
		this.categoryRepository.deleteById(categoryId);
	}
	
	//service to get categories by id
	public Optional<Category> getCategoryById(int categoryId){
		return this.categoryRepository.findById(categoryId);
	}
}
