package com.transaction.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.product.entities.DeletedProduct;


public interface ProductDeletedJpaRepo extends JpaRepository<DeletedProduct, Integer>{
}
