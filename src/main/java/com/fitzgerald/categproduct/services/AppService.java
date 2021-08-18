package com.fitzgerald.categproduct.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.fitzgerald.categproduct.models.Category;
import com.fitzgerald.categproduct.models.Product;
import com.fitzgerald.categproduct.repositories.CategoryRepository;
import com.fitzgerald.categproduct.repositories.ProductRepository;

@Service
public class AppService {

	//---------------- Initialize and tell service about the repositories

		private ProductRepository productRepo;
		private CategoryRepository categoryRepo;
	
			public AppService(ProductRepository productRepo, CategoryRepository categoryRepo) {
				this.productRepo = productRepo;
				this.categoryRepo = categoryRepo;
			}

	
	//---------------- Create a Product and save it, Create a Category and save it
	
	public Product createProduct(Product p) {
		return this.productRepo.save(p);
	}	
	
	public Category createCategory(Category c) {
		return this.categoryRepo.save(c);
	}
	
	
	//----------------- Retrieve ALL Products, Retrieve ALL Categories
	
	public List<Product> getAllProducts(){
		return this.productRepo.findAll();
	}
	
	public List<Category> getAllCategories(){
		return this.categoryRepo.findAll();
	}
	

	//------------------ Retrieve ONE Product, Retrieve ONE Category
	
	public Product getOneProduct(Long id) {
		return this.productRepo.findById( id ).orElse(null);
	}
		
	public Category getOneCategory(Long id) {
		return this.categoryRepo.findById(id).orElse(null);
	}
	

   
    //------------------- Retrieve LIST of categories that ONE product does NOT contain
    
	// Return the categories that are unassigned to this product to avoid repetition
    public List<Category> findByProductsNotContains(Product p){
    	return categoryRepo.findByProductsNotContaining(p);
    }
    	
    
    //------------------- Retrieve LIST of ANY categories that ONE product does NOT contain
    
    // Return the products that are unassigned to this category to avoid repetition
    public List<Product> findByCategoriesNotContains(Category c){
    	return productRepo.findByCategoriesNotContaining(c);
    }
    
    
    //------------------ Update product || Assign category to product
	
    public void updateProduct(Product p) {
	   this.productRepo.save(p);
    }
    

	//------------------ Update category || Assign product to category
	
    public void updateCategory(Category c) {
	   this.categoryRepo.save(c);
   }
    
    
   // ------------------- Delete product 
   
    public void deleteProduct(Long id) {
    	this.productRepo.deleteById(id);
    }
    
   // ------------------  Delete category
    
    public void deleteCategory(Long id) {
    	this.categoryRepo.deleteById(id);
    }
    

}
