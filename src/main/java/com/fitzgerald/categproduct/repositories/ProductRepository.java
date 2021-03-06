package com.fitzgerald.categproduct.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fitzgerald.categproduct.models.Category;
import com.fitzgerald.categproduct.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	List <Product> findAll();
	
	//Return list of products unassigned to category requested
	List<Product> findByCategoriesNotContaining(Category c);
}
