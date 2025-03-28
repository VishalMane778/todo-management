package com.app.todomanagement.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(nullable = false)
	private String name;
	@Column(nullable = false,unique = true)
	private String userName;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
	joinColumns =@JoinColumn(name="user_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
	private Set<Role> roles;
}
