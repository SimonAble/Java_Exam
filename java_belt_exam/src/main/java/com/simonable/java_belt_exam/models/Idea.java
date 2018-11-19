package com.simonable.java_belt_exam.models;

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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.simonable.java_belt_exam.models.*;

//<<---------------Idea Model--------------->>
@Entity
@Table(name="ideas")
public class Idea {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=5, max=255, message="Please input an idea name of 5 characters or more.")
	private String name;
	
	@Size(min=5, max=255, message="Please input an idea description of 5 characters or more.")
	private String description;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;

//	<<---------------One To Many Relationship--------------->>
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	
//	<<---------------Many To Many Relationship--------------->>
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="users_ideas",
			joinColumns = @JoinColumn(name = "idea_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> users;
	
//	<<---------------Defining Constructor--------------->>	
	public Idea() {
		
	}

	public Idea(@Size(min = 5, max = 255, message = "Please input an idea name of 5 characters or more.") String name,
			@Size(min = 10, max = 255, message = "Please input an idea description of 10 characters or more.") String description,
			User user, List<User> users) {
		this.name = name;
		this.description = description;
		this.user = user;
		this.users = users;
	}

	//	<<---------------Getters and Setters--------------->>
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	//	<<---------------Creating/Updating--------------->>
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	
}
