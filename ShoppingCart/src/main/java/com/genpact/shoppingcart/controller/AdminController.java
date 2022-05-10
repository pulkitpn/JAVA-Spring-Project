package com.genpact.shoppingcart.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.genpact.shoppingcart.dto.ProductDTO;
import com.genpact.shoppingcart.model.Category;
import com.genpact.shoppingcart.model.Product;
import com.genpact.shoppingcart.service.CategoryService;
import com.genpact.shoppingcart.service.ProductService;

 
@Controller
@RequestMapping("shopping-cart/admin/")
public class AdminController {

	public static String uploadDir = System.getProperty("user.dir") +"/src/main/resources/static/productimages";
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	// http://localhost:8080/shopping-cart/admin/admin-dashboard
	@GetMapping("/admin-dashboard")
	public String adminDashboard() {
		return "adminDashboard";
	}

	// handler to display category
	// http://localhost:8080/shopping-cart/admin/admin-dashboard/categories
	@GetMapping("/admin-dashboard/categories")
	public String getCategoriesPage(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "displayCategories";
	}

	// handler to open the add-category form
	// http://localhost:8080/shopping-cart/admin/admin-dashboard/categories/add-category
	@GetMapping("/admin-dashboard/categories/add-category")
	public String getCategoriesAdd(Model model) {
		model.addAttribute("category", new Category());
		return "addCategories";
	}

	// handler to add the category in DB
	@PostMapping("/admin-dashboard/categories/add-category")
	public String postCategoriesAdd(@ModelAttribute Category category) {
		System.out.println(category);
		this.categoryService.addCategory(category);
		return "redirect:/shopping-cart/admin/admin-dashboard/categories";
	}

	// handler to delete category by id
	@GetMapping("/admin-dashboard/categories/delete/{categoryId}")
	public String deleteCategories(@PathVariable int categoryId , Model model) {
		try {
			this.categoryService.deleteCategoryById(categoryId);
			return "redirect:/shopping-cart/admin/admin-dashboard/categories";
		} catch (Exception e) {
			 model.addAttribute("message","There are products present in this category");
			return "redirect:/shopping-cart/admin/admin-dashboard/categories";
		}
	}

	// handler to update category by id
	@GetMapping("/admin-dashboard/categories/update/{categoryId}")
	public String updateCategories(@PathVariable int categoryId, Model model) {
		
			Optional<Category> categoryById = this.categoryService.getCategoryById(categoryId);
			if (categoryById.isPresent()) {
				model.addAttribute("category", categoryById.get());
				return "addCategories";
			} else {
				return "displayCategories";
			}
		
		

	}

	// PRODUCT SECTION

	// handler to display product
	// http://localhost:8080/shopping-cart/admin/admin-dashboard/products
	@GetMapping("/admin-dashboard/products")
	public String getProductsPage(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		List<Product> allProduct = productService.getAllProduct();
		return "displayProduct";
	}

	// handler to open the add-product form
	// http://localhost:8080/shopping-cart/admin/admin-dashboard/products/add-product
	@GetMapping("/admin-dashboard/products/add-product")
	public String getProductsAdd(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", this.categoryService.getAllCategory());
		
		return "addProduct";
	}

	@PostMapping("/admin-dashboard/products/add-product")
	public String postProductAdd(@ModelAttribute ProductDTO productDTO ,
			@RequestParam("productImage") MultipartFile file,
			@RequestParam("imgName")String imgName) throws IOException {
		 
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
	    product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get()); 
	    product.setPrice(productDTO.getPrice());
	    product.setWeight(productDTO.getWeight());
	    product.setDescription(productDTO.getDescription());
	    
	    String imageUUID;
	    if(!file.isEmpty()) {
	    	// when file is not empty
	    	imageUUID = file.getOriginalFilename();
	    	Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
	    	Files.write(fileNameAndPath, file.getBytes());
	    }
	    else {
	    	imageUUID = imgName;
	    }
	    
	    product.setImageName(imageUUID);
	    productService.addProducts(product);
	    
		return "redirect:/shopping-cart/admin/admin-dashboard/products";
	}
	
	
	@GetMapping("/admin-dashboard/products/update/{id}")
	public String updateProduct(@PathVariable int id , Model model) {
		 Product product = this.productService.getProductById(id).get();
		 ProductDTO productDTO = new ProductDTO();
		 
		 productDTO.setId(product.getId());
		 productDTO.setName(product.getName());
		 productDTO.setCategoryId(product.getCategory().getId());
		 productDTO.setPrice(product.getPrice());
		 productDTO.setWeight(product.getWeight());
		 productDTO.setDescription(product.getDescription());
		 productDTO.setImageName(product.getImageName());
		 model.addAttribute("categories",categoryService.getAllCategory());
		 model.addAttribute("productDTO",productDTO);
		return "addProduct";
	}
	
	@GetMapping("/admin-dashboard/products/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		this.productService.removeProductsById(id);
		return "redirect:/shopping-cart/admin/admin-dashboard/products";
	}
	

}