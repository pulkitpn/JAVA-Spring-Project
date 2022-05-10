package com.genpact.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.shoppingcart.model.Product;
import com.genpact.shoppingcart.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	// service to get all products
	public List<Product> getAllProduct() {
		return this.productRepository.findAll();
	}
	
	// service to add products
	public void addProducts(Product product) {
		this.productRepository.save(product);
	}
	
	// service to remove productsById
	public void removeProductsById(int productId) {
		 this.productRepository.deleteById(productId);
	}
	
	// service to get productsById
	public Optional<Product> getProductById(int productId){
		return this.productRepository.findById(productId); 
	}
	
	public List<Product> getAllProductsByCategoryId(int id){
		return this.productRepository.findAllByCategoryId(id);
	}
}
