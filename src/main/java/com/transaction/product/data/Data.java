package com.transaction.product.data;

import java.util.ArrayList;
import java.util.List;

import com.transaction.product.entities.Product;

public class Data {

	
	public static List<Product> getListOfProduct(){
		
		List<Product> productList = new ArrayList<>();
		Product product = new Product(1,"Ram",32100);
		productList.add(product);
		product = new Product(2,"Santhosh",15300.23);
		productList.add(product);
		product = new Product(3,"Monika",24100.56);
		productList.add(product);
		product = new Product(4,"Revathi",62100.24);
		productList.add(product);
		return productList;
		
	}
	
}
