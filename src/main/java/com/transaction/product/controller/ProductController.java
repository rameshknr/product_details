package com.transaction.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.product.entities.DeletedProduct;
import com.transaction.product.entities.Product;
import com.transaction.product.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ProductController", description = "REST Apis related to product Entity!!!!")
@RestController
@CrossOrigin("*")
@RequestMapping("/v2/transactions")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@ApiOperation(value = "Test APi ", response = ResponseEntity.class, tags = "test")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<String> test(){
		return new ResponseEntity<>("Demo API", HttpStatus.OK);
	}

	@ApiOperation(value = "Get list of Products in the System  ", response = Product.class, tags = "product")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Product> fetchAllProducts(@RequestParam(value = "orderBy", required = false ,defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", required = false ,defaultValue = "asc") String direction, 
			@RequestParam(value = "pageNumber", required = false ,defaultValue = "0") int pageNumber, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize){
		return productService.fetchAllProducts(orderBy, direction, pageNumber, pageSize);
	}	
	
	@ApiOperation(value = "Adding Product in the System   ", response = ResponseEntity.class, tags = "product")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> addProduct(@RequestBody Product product){
		if(productService.findByName(product.name)){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		 productService.addProduct(product);
		 return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get Product based on ID in the System   ", response = ResponseEntity.class, tags = "product")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> fetchProduct(@PathVariable("id")int id){
		System.out.println("In Fetch Product: " + id);
		ResponseEntity<Product> response = null; 
		Product product = productService.findProductById(id);
		if(product==null){
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else{
			response = new ResponseEntity<>(product, HttpStatus.OK);
		}
		return response;
	}
	
	@ApiOperation(value = "Delete Product based on ID in the System  ", response = ResponseEntity.class, tags = "product")
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
		
		ResponseEntity<String> response = null;
		try{
			productService.deleteProduct(id);
			response = ResponseEntity.ok().body("Product has been deleted successfully.");
		}
		catch(EmptyResultDataAccessException e){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@ApiOperation(value = "Get list of Deleted Products in the System ", response = DeletedProduct.class, tags = "deleted-product")
	@RequestMapping(value = "/deleted-product", method = RequestMethod.GET)
	public List<DeletedProduct> fetchAllDeletedProducts(){
		return productService.fetchAllDeletedProducts();
	}

}
