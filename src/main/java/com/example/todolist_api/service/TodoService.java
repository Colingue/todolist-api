package com.example.todolist_api.service;

import com.example.todolist_api.dto.TodoDto;
import com.example.todolist_api.model.Todo;
import com.example.todolist_api.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    private TodoDto convertToDto(Todo todo) {
        return new TodoDto(todo.getId(), todo.getTitle(), todo.getDescription(), todo.isCompleted(), todo.getDeadline());
    }

    private Todo convertToEntity(TodoDto todoDto) {
        return new Todo(todoDto.getId(), todoDto.getTitle(), todoDto.getDescription(), todoDto.isCompleted(), todoDto.getDeadline());
    }

    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<TodoDto> getTodoById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.map(this::convertToDto);
    }

    public TodoDto createTodo(TodoDto todoCreateDto) {
        Todo todo = convertToEntity(todoCreateDto);
        Todo savedTodo = todoRepository.save(todo);
        return convertToDto(savedTodo);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    public TodoDto updateTodo(Long id, TodoDto todoDetails) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow();
        existingTodo.setTitle(todoDetails.getTitle());
        existingTodo.setDescription(todoDetails.getDescription());
        existingTodo.setCompleted(todoDetails.isCompleted());
        existingTodo.setDeadline(todoDetails.getDeadline());
        Todo updatedTodo = todoRepository.save(existingTodo);
        return convertToDto(updatedTodo);
    }
}
