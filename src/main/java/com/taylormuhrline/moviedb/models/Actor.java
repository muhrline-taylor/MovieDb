package com.taylormuhrline.moviedb.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="actors")
public class Actor {
	
	
	// FIELDS ------------------------------------------ //
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String fname;
	
	@NotNull
	@Size(min=1)
	private String lname;
	
	@ManyToMany(
				cascade=CascadeType.PERSIST,
				fetch=FetchType.EAGER
			)
	@JoinTable(
				name="movies_actors",
				joinColumns= {@JoinColumn(name="actor_id")},
				inverseJoinColumns= {@JoinColumn(name="movie_id")}
			)
	private Set<Movie> movies = new HashSet<Movie>();
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
 
    
    // CONSTRUCTORS ------------------------------------------ // 
    
    public Actor(@NotNull String lname) {
		super();
		this.lname = lname;
	}
	public Actor(String fname, @NotNull String lname) {
		super();
		this.fname = fname;
		this.lname = lname;
	}
	public Actor() {
		super();
	}
	
	
	
	
	// GETTERS AND SETTERS ----------------------------------------------- //
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Set<Movie> getMovies() {
		return movies;
	}
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
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
