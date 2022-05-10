package com.genpact.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.shoppingcart.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
