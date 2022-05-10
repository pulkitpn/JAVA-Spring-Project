package com.genpact.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.genpact.shoppingcart.global.GlobalData;
import com.genpact.shoppingcart.model.Category;
import com.genpact.shoppingcart.model.Product;
import com.genpact.shoppingcart.service.CategoryService;
import com.genpact.shoppingcart.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("cartCount",GlobalData.cart.size());
		List<Category> allCategory = this.categoryService.getAllCategory();
		List<Product> allProduct = this.productService.getAllProduct();

		System.out.println(allCategory);
		System.out.println(allProduct);
		return "shop";
	}

	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		model.addAttribute("cartCount",GlobalData.cart.size());
		
		List<Category> allCategory = this.categoryService.getAllCategory();
		List<Product> allProduct = this.productService.getAllProduct();

		System.out.println(allCategory);
		System.out.println(allProduct);
		return "shop";
	}

	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable int id) {
		model.addAttribute("product", productService.getProductById(id).get());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "viewproduct";
	}
	
	

}
