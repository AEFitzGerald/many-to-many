package com.fitzgerald.categproduct.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fitzgerald.categproduct.models.Category;
import com.fitzgerald.categproduct.models.Product;
import com.fitzgerald.categproduct.services.AppService;



@Controller
public class HomeController {
	
	@Autowired
	private AppService appService;
	
	//	Autowired does this: it tells the controller about the services and constructs the class with them
	//	private final AppService appService;
	
	//  construct the controller class with the services
	//	public HomeController(AppService appService) {
	//		this.appService = appService;
	//	}
	
	@GetMapping("/home")
	public String home() {
		return "hello.jsp";
	}
	

	
	//---------------------------------------- Create ONE category home page
	@GetMapping("/")
	public String home(@ModelAttribute("category") Category category) {
		return "create-category.jsp"; 
	}
	
	@PostMapping("/category/create")
	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			return "create-category.jsp";
		}
		this.appService.createCategory(category);
		
		
		return "redirect:/";	
	}
	
	
	//--------------------------------------- Show ONE category page / Post new products to this category
	@GetMapping("/category/{id}")
	public String showCategory(@PathVariable("id") Long id, Model model) {
		
		Category category = this.appService.getOneCategory(id);
		model.addAttribute("category", category);
		
		List<Product> allProducts = this.appService.getAllProducts();
		model.addAttribute("allProducts", allProducts);
		
		
		return "show-category.jsp";
}
	
	
	@PostMapping("/category/{id}/add")
	public String addProductToCategory(@PathVariable("id")Long categoryId,  @RequestParam("productId") Long productId) {
		
		System.out.println("Category ID is this -->" + categoryId);
		System.out.println("Product ID is this -->" + productId);
		
		//retrieve a category object with the category id
		Category category = this.appService.getOneCategory(categoryId);
		
		//retrieve a product object with the product id
		Product product = this.appService.getOneProduct(productId);
		
		//what are the products associated with this category
		List<Product> currentProducts = category.getProducts();
		System.out.println(currentProducts);
		
		//add selected product to this category
		currentProducts.add(product);
		
		//set the product to this category's product list
		category.setProducts(currentProducts);
		
		//communicate with appService and update the category 
		this.appService.updateCategory(category);
		
		return "redirect:/category/"+ categoryId;
		
	}
	
	
	//---------------------------------------- Create ONE product page
		@GetMapping("/product")
		public String home(@ModelAttribute("product") Product product) {
			return "create-product.jsp"; 
		}
		
		
		
		@PostMapping("/product/create")
		public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
			if(result.hasErrors()) {
				return "create-product.jsp";
			}
			this.appService.createProduct(product);
			
			
			return "redirect:/product";	
		}
		
	
	//--------------------------------------- Show ONE product page / Post new categories to this product
	@GetMapping("/product/{id}")
	public String showProduct(@PathVariable("id") Long id, Model model) {
		
		Product product = this.appService.getOneProduct(id);
		model.addAttribute("product", product);
		
		List<Category> allCategories = this.appService.getAllCategories();
		model.addAttribute("allCategories", allCategories);
		
		
		return "show-product.jsp";
}
	
	
	@PostMapping("/product/{id}/add")
	public String addCategoryToProduct(@PathVariable("id")Long productId,  @RequestParam("categoryId") Long categoryId) {
		
		System.out.println("Product ID is this -->" + productId);
		System.out.println("Category ID is this -->" + categoryId);
		
		//retrieve a category object with the category id
		Product product = this.appService.getOneProduct(productId);
		
		//retrieve a product object with the product id
		Category category = this.appService.getOneCategory(categoryId);
		
		//what are the products associated with this category
		List<Category> currentCategories = product.getCategories();
		System.out.println(currentCategories);
		
		//add selected product to this category
		currentCategories.add(category);
		
		//set the product to this category's product list
		product.setCategories(currentCategories);
		
		//communicate with appService and update the category 
		this.appService.updateProduct(product);
		
		return "redirect:/product/"+ productId;
		
	}

//this.category.getProducts().add(p);	
//	this.category.getProducts().add(thisProduct);
	
}