package com.genpact.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.shoppingcart.model.Category;
import com.genpact.shoppingcart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public List<Product> findAllByCategoryId(int id);
}
