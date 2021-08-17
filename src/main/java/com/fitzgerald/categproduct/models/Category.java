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
@Table(name="categories")
public class Category {
	
	//--------------------  member variables
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
		@NotNull(message= "Please enter name...")
    @Size(min = 1, max = 200, message= "Name must be between 1-200 characters")
    private String name;
		
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
  
    
 //------------------- many to many relationship joined in new table
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "cat_prod", 
    		joinColumns = @JoinColumn(name = "category_id"),
    		inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
   
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    
    //------------------ empty constructor 
    
    public Category() {
        
    }
    
    //------------------ loaded constructor
    
	public Category(String name) {
		this.name = name;
	}

	// ------------------ getters and setters
	
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
	
	//-------------- join with products get products and set products 
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
    
}

