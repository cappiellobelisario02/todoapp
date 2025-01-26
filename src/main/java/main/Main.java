package main;

import controller.TodoController;
import controller.TodoService;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        TodoController todoController = new TodoController(new TodoService(), new ConsoleView());
        todoController.start();
    }
}
