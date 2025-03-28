package com.app.todomanagement.service;

import java.util.List;

import com.app.todomanagement.dto.TodoDto;

public interface TodoService {
	
	TodoDto addTodo(TodoDto todoDto);
	
	TodoDto getTodoById(Long TodoId);
	
	List<TodoDto> getAllTodo();
	
	TodoDto updateTodo(TodoDto todoDto,Long todoId);
	
	void deleteTodo(Long todoId);
	
	TodoDto completeTodo(Long todoId);
	
	TodoDto inCompleteTodo(Long todoId);

}
