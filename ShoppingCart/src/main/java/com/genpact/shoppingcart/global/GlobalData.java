package com.genpact.shoppingcart.global;

import java.util.ArrayList;
import java.util.List;

import com.genpact.shoppingcart.model.Product;

public class GlobalData {
	
	public static List<Product> cart;
	static {
		cart = new ArrayList<Product>();
	}
	
	
}
