package controller;

import model.Todo;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoService implements IntfTodoService{

    private final Map<Integer, Todo> todos = new HashMap<>();
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private static final String FILENAME = "todos.txt";

    public TodoService() {
        checkFileExist();
        loadTodosFromFile();
    }

    @Override
    public void addTodo(Todo todo) {
        todos.put(ID_GENERATOR.incrementAndGet(), todo);
        saveTodosToFile();
    }

    @Override
    public void deleteTodo(int id) {
        todos.remove(id);
        saveTodosToFile();
    }

    @Override
    public boolean updateTodo(Todo todo, String task) {
        String prev_task = todo.getTask();

        todo.setTask(task);

        if (!prev_task.equals(task)){
            saveTodosToFile();
            return true;
        }
        return false;
    }

    public boolean updateTodo(Todo todo, LocalDate dueDay) {
        LocalDate prev_dueDay = todo.getDueDay();
        todo.setDueDay(dueDay);

        if (!prev_dueDay.equals(dueDay)){
            saveTodosToFile();
            return true;
        }
        return false;
    }

    @Override
    public Map<Integer, Todo> getAllTodos() {
        return todos;
    }

    @Override
    public Todo getTodo(int id) {
        return todos.getOrDefault(id, null);
    }

    @Override
    public void saveTodosToFile() {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILENAME))) {
            for(Map.Entry<Integer, Todo> entry : todos.entrySet()) {
                bufferedWriter.write(entry.getKey() + "," + entry.getValue().getTask() + "," + entry.getValue().getDueDay().format(DateTimeFormatter.ISO_LOCAL_DATE));
                bufferedWriter.newLine();
            }
        }catch (IOException ioe){
            System.err.println("Error saving todos to file: " + ioe.getMessage());
        }
    }

    @Override
    public void loadTodosFromFile() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String task = parts[1];
                    LocalDate dueDay = LocalDate.parse(parts[2], DateTimeFormatter.ISO_LOCAL_DATE);
                    todos.put(id, new Todo(task, dueDay));
                    ID_GENERATOR.set(Math.max(id, ID_GENERATOR.get()));
                }
            }
        }catch(IOException ioe){
            System.err.println("Error loading todos from file: " + ioe.getMessage());
        }
    }

    private void checkFileExist() {
        File file = new File(FILENAME);
        if(!file.exists()){
            System.out.println("File does not exist...\nCreating a new file...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }
}
