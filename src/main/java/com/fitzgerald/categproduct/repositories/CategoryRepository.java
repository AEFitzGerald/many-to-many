package com.fitzgerald.categproduct.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fitzgerald.categproduct.models.Category;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	//SELECT * FROM categories JPL library
	List<Category> findAll();

}
