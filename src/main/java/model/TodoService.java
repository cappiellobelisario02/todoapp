package model;

import java.util.HashMap;
import java.util.Map;

public class TodoService implements IntfTodoService{

    private final Map<Integer, Todo> todos = new HashMap<>();
    private int todoCounter = 0;

    @Override
    public void addTodo(Todo todo) {
        todos.put(todoCounter++, todo);
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
        if (todos.containsKey(id)){
            return todos.get(id);
        }
        return null;
    }
}
