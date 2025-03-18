package com.app.todomanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.todomanagement.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
