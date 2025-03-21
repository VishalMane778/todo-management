package com.app.todomanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.todomanagement.dto.TodoDto;
import com.app.todomanagement.entity.Todo;
import com.app.todomanagement.exception.ResourceNotFoundException;
import com.app.todomanagement.repository.TodoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		// convert TodoDto into Todo Jpa Entity
//		Todo todo = new Todo();
//		todo.setTitle(todoDto.getTitle());
//		todo.setDescription(todoDto.getDescription());
//		todo.setCompleted(todoDto.isCompleted());
		
		Todo todo=modelMapper.map(todoDto, Todo.class);

		// Todo Jpa entity
		Todo savedTodo = todoRepository.save(todo);

		// convert saved Todo Jpa entity into ToDto object

//		TodoDto savedTodoDto = new TodoDto();
//		savedTodoDto.setId(savedTodo.getId());
//		savedTodoDto.setTitle(savedTodo.getTitle());
//		savedTodoDto.setDescription(savedTodo.getDescription());
//		savedTodoDto.setCompleted(savedTodo.isCompleted());
		
		TodoDto savedTodoDto=modelMapper.map(savedTodo, TodoDto.class);
		return savedTodoDto;
	}

	@Override
	public TodoDto getTodoById(Long TodoId) {
		Todo todo=todoRepository.findById(TodoId)
				                .orElseThrow(()->new ResourceNotFoundException("Todo not found with given id : "+TodoId));
		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTodo() {
		List<Todo> todos=todoRepository.findAll();
		return todos.stream()
				    .map((todo)->modelMapper.map(todo,TodoDto.class))
				    .collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long todoId) {
		//if the jpa entity object contains primary key then this save() performs the update operation
		//and if the jpa entity object does not contains primary key then this save() perform insert operation
		Todo todo=todoRepository.findById(todoId)
		              .orElseThrow(()->new ResourceNotFoundException("Todo not found with given id : "+todoId));
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		//this save() perform both insert and update operation
		Todo updatedTodo=todoRepository.save(todo);
		
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public void deleteTodo(Long todoId) {
		Todo todo=todoRepository.findById(todoId)
		              .orElseThrow(()->new ResourceNotFoundException("Todo not found with given id : "+todoId));
		
		todoRepository.deleteById(todoId);
	}

	@Override
	public TodoDto completeTodo(Long todoId) {
		Todo todo=todoRepository.findById(todoId)
		              .orElseThrow(()->new ResourceNotFoundException("Todo not found with given id : "+todoId));
		todo.setCompleted(Boolean.TRUE);
		Todo updatedTodo=todoRepository.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long todoId) {
		Todo todo=todoRepository.findById(todoId)
		              .orElseThrow(()->new ResourceNotFoundException("Todo not found with given id : "+todoId));
		todo.setCompleted(Boolean.FALSE);
		Todo updatedTodo=todoRepository.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

}
