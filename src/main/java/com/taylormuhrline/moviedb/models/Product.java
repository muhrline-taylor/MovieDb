package com.taylormuhrline.moviedb.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="buyer_id")
	private User buyer;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="seller_id")
	private User seller;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    
    
    
    
    
    
    // CONSTRUCTORS ===================================== //
    
    public Product(String name, BigDecimal price, User seller) {
		super();
		this.name = name;
		this.price = price;
		this.seller = seller;
	}
	public Product() {
		super();
	}
	public Product(String name, BigDecimal price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	
	
	// GETTERS AND SETTERS ============================================= //
    
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
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
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	
	
	
	

}
