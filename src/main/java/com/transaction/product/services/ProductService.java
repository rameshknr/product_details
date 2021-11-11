package com.transaction.product.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.transaction.product.data.Data;
import com.transaction.product.entities.DeletedProduct;
import com.transaction.product.entities.Product;
import com.transaction.product.repository.ProductDeletedJpaRepo;
import com.transaction.product.repository.ProductJpaRepo;

@Service
public class ProductService {
	@Autowired
	ProductJpaRepo productRepository;
		
	@Autowired
	ProductDeletedJpaRepo productDeletedRepository;
	
	List<Product> productList = Data.getListOfProduct();
	List<DeletedProduct> deletedProductList ;

	public List<Product> fetchAllProducts(String orderBy,String direction, int pageNumber,int pageSize) {
		
		Pageable pageable;
		if(orderBy.equalsIgnoreCase("price")) {
			pageable = direction.equalsIgnoreCase("desc")? PageRequest.of(pageNumber,pageSize,Sort.by("price").descending()) : PageRequest.of(pageNumber,pageSize,Sort.by("price")) ;
		}else if(orderBy.equalsIgnoreCase("name")) {
			pageable = direction.equalsIgnoreCase("desc")? PageRequest.of(pageNumber,pageSize,Sort.by("name").descending()) : PageRequest.of(pageNumber,pageSize,Sort.by("name")) ;
		}else {
			pageable = direction.equalsIgnoreCase("desc")? PageRequest.of(pageNumber,pageSize,Sort.by("id").descending()) : PageRequest.of(pageNumber,pageSize,Sort.by("id")) ;
		}
		
		Page<Product> pageProduct = productRepository.findAll(pageable);
		return pageProduct.getContent();
		//return productList;		
	}

	public void addProduct(Product input) {
		productRepository.save(input);
		//productList.add(input);
	}
	
	public boolean findByName(String name) {
		Product product = productRepository.findByName(name);
		//List<Product> list = productList.stream().filter(p -> p.name.equalsIgnoreCase(name)).collect(Collectors.toList());	
		//System.out.println(product);
		if(product == null){
			return false;
		}
		return true;
	}
	
	public Product findProductById(int id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent())
		{
			return product.get();
		}
		return null;
		
		/**List<Product> list = productList.stream().filter(p -> p.id == id).collect(Collectors.toList());	
		if(list.size() > 0 ) {
			return list.get(0);
		}
		
		return null;**/
	}

	public void deleteProduct(int id) {
		Optional<Product> product = productRepository.findById(id);
		DeletedProduct deletedProduct = new DeletedProduct(product.get().getId(),product.get().getName(),product.get().getPrice());
		productDeletedRepository.save(deletedProduct);
		productRepository.deleteById(id);
		/**List<Product> list = productList.stream().filter(p -> p.id == id).collect(Collectors.toList());	
		Product product = new Product();
		if(list.size() > 0 ) {
			product = list.get(0);
			DeletedProduct deletedProduct = new DeletedProduct(product.getId(),product.getName(),product.getPrice());
			deletedProductList.add(deletedProduct);
		}**/
	}
	
	public List<DeletedProduct> fetchAllDeletedProducts() {
		return productDeletedRepository.findAll();
		//return deletedProductList;
	}
}
