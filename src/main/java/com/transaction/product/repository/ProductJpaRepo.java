package com.transaction.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.product.entities.Product;

public interface ProductJpaRepo extends JpaRepository<Product, Integer>{
	
	public Product findByName(String name);
}
