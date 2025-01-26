package view;

import model.Todo;

import java.util.Map;

public interface View {

    void displayMenu();
    void displayTodos(Map<Integer, Todo> todos);
    void displayMessage(String message);
    String getInput(String prompt);

}
