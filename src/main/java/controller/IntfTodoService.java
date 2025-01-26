package controller;

import model.Todo;

import java.util.Map;

public interface IntfTodoService {
    void addTodo(Todo todo);
    void deleteTodo(int id);
    Map<Integer, Todo> getAllTodos();
    Todo getTodo(int id);
}
