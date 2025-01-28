package controller;

import model.Todo;

import java.util.Map;

public interface IntfTodoService {
    void addTodo(Todo todo);
    void deleteTodo(int id);
    boolean updateTodo(Todo todo, String task);
    Map<Integer, Todo> getAllTodos();
    Todo getTodo(int id);
    void saveTodosToFile();
    void loadTodosFromFile();
}
