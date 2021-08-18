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
	
	//	Autowired does what below does: it tells the controller about the services and constructs the class with them
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
	public String categoryForm(@ModelAttribute("category") Category category) {
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
		
		Category c = this.appService.getOneCategory(id);
		model.addAttribute("category", c);
		
		//what are the products not associated with this category
		List<Product> toBeProducts = this.appService.findByCategoriesNotContains(c);
		model.addAttribute("toBeProducts", toBeProducts);
		
		
		return "show-category.jsp";
}
	
	
	@PostMapping("/category/{id}/add")
	public String addProductToCategory(@PathVariable("id")Long categoryId,  @RequestParam("productId") Long productId) {
		
		System.out.println("Category ID is this -->" + categoryId);
		System.out.println("Product ID is this -->" + productId);
		
		Category c = this.appService.getOneCategory(categoryId);
		Product p = this.appService.getOneProduct(productId);
		
		List<Product> currentProducts = c.getProducts();
		currentProducts.add(p);
		c.setProducts(currentProducts);
		
		//update the category 
		this.appService.updateCategory(c);
		
		return "redirect:/category/"+ categoryId;
		
	}
	
	
	//---------------------------------------- Create ONE product page
		@GetMapping("/product")
		public String productForm(@ModelAttribute("product") Product product) {
			
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
		
		Product p = this.appService.getOneProduct(id);
		model.addAttribute("product", p);
		
		//what are the categories not associated with this product
		List<Category> toBeCategories = this.appService.findByProductsNotContains(p);
		model.addAttribute("toBeCategories", toBeCategories);
		
		
		return "show-product.jsp";
}
	
	
	@PostMapping("/product/{id}/add")
	public String addCategoryToProduct(@PathVariable("id")Long productId,  @RequestParam("categoryId") Long categoryId) {
		
		System.out.println("Product ID is this -->" + productId);
		System.out.println("Category ID is this -->" + categoryId);
		
		Product p = this.appService.getOneProduct(productId);
		Category c = this.appService.getOneCategory(categoryId);
		
		
		List<Category> currentCategories = p.getCategories();
		currentCategories.add(c);
		p.setCategories(currentCategories);
		
		//update the product 
		this.appService.updateProduct(p);
		
		return "redirect:/product/"+ productId;
		
	}
	
}

