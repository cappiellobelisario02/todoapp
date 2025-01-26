package controller;

import model.Todo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoService implements IntfTodoService{

    private final Map<Integer, Todo> todos = new HashMap<>();
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);


    @Override
    public void addTodo(Todo todo) {
        todos.put(ID_GENERATOR.incrementAndGet(), todo);
    }

    @Override
    public void deleteTodo(int id) {
        todos.remove(id);
    }

    @Override
    public Map<Integer, Todo> getAllTodos() {
        return todos;
    }

    @Override
    public Todo getTodo(int id) {
        return null;
    }
}
