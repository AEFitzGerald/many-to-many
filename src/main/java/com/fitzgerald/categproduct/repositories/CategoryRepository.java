package com.fitzgerald.categproduct.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fitzgerald.categproduct.models.Category;
import com.fitzgerald.categproduct.models.Product;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	//SELECT * FROM categories 
	List<Category> findAll();
	
	//Return list of categories unassigned to product requested
	List<Category> findByProductsNotContaining(Product p);

}
