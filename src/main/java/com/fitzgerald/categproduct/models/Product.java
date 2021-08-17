package com.fitzgerald.categproduct.models;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="products")
public class Product {
	//---------------------------------  member variables 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    	@NotNull(message= "Please enter name...")
    @Size(min = 1, max = 200, message= "Name must be between 1-200 characters")
    private String name;
    
    	@NotNull(message= "Please enter product description...")
    @Size(min = 10, max = 400, message= "Product Descriptiion must be between 10-400 characters")
    private String description;
    
    	@NotNull(message= "Please enter product price...")
    private double price;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    //--------------------------------- many to many relationship with new table join
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cat_prod", 
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List< Category > categories;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    //--------------------------------  empty constructor
    
    public Product() {
        
    }
    
    //-------------------------------- loaded constructor
    
	public Product(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	// ------------------------------- getters and setters 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	//-------------- join with categories get categories and set categories 
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
    
    
}
