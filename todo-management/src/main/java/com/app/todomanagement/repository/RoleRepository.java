package com.app.todomanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.todomanagement.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name); 
}
