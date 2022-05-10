package com.genpact.shoppingcart.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String categoryName;
	private int categoryId;
	private double price;
	private double weight;
	private String description;
	public String imageName;

	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(int id, String categoryName, int categoryId, double price, double weight, String description,
			String imageName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.price = price;
		this.weight = weight;
		this.description = description;
		this.imageName = imageName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return categoryName;
	}

	public void setName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", categoryName=" + categoryName + ", categoryId=" + categoryId + ", price="
				+ price + ", weight=" + weight + ", description=" + description + ", imageName=" + imageName + "]";
	}

}
