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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="movies")
public class Movie {

	
	
	// FIELDS ------------------------------ //
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, max=255)
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="director_id")
	private Director director;
	
	@Min(1895)
	@Max(2030)
	private int year;
	
	private String poster;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinTable(
				name="movies_actors",
				joinColumns= {@JoinColumn(name="movie_id")},
				inverseJoinColumns= {@JoinColumn(name="actor_id")}
			)
	private Set<Actor> actors = new HashSet<Actor>();
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    
    
    
    
    // CONSTRUCTORS --------------------------------------------------- //
    
    public Movie(@Size(min = 1, max = 255) String name, Director director, @Min(1895) @Max(2030) int year) {
		super();
		this.name = name;
		this.director = director;
		this.year = year;
	}
	public Movie(@Size(min = 1, max = 255) String name, @Min(1895) @Max(2030) int year) {
		super();
		this.name = name;
		this.year = year;
	}
	public Movie(@Size(min = 1, max = 255) String name, @Min(1895) @Max(2030) int year, String poster) {
		super();
		this.name = name;
		this.year = year;
		this.poster = poster;
	}
	public Movie() {
		super();
	}
	
	
	
	// GETTERS AND SETTERS ------------------------------------------- //
    
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
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		this.director = director;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Set<Actor> getActors() {
		return actors;
	}
	public void setActors(Set<Actor> actors) {
		this.actors = actors;
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
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
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
