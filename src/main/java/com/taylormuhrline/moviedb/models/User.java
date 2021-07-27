package com.taylormuhrline.moviedb.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	
	private String username;
	
	private String password;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="seller")
	private List<Product> selling = new ArrayList<Product>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="buyer")
	private List<Product> buying = new ArrayList<Product>();
	
	
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    
    
    
    
    
    
    // CONSTRUCTORS =============================================== //
    
    
    public User(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}
	public User() {
		super();
	}
	
	
	
	
	// GETTERS AND SETTERS ========================================= //
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Product> getSelling() {
		return selling;
	}
	public void setSelling(List<Product> selling) {
		this.selling = selling;
	}
	public List<Product> getBuying() {
		return buying;
	}
	public void setBuying(List<Product> buying) {
		this.buying = buying;
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
