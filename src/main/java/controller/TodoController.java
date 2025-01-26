package controller;

import model.Todo;
import view.ConsoleView;
import java.util.Timer;
import java.util.TimerTask;

public class TodoController {

    private TodoService todoService;
    private ConsoleView todoView;

    public TodoController(TodoService todoService, ConsoleView todoView) {
        this.todoService = todoService;
        this.todoView = todoView;
    }

    public void start(){
        while(true){
            todoView.displayWelcomeMessage();
            todoView.displayMenu();
            String choice = todoView.getInput("Per eseguire un comando ti basta digitare da tastiera uno dei numeri in elenco.\nInserisci un comando: ");

            switch(choice){
                case "1" -> addTodo();
                case "2" -> todoView.displayTodos(todoService.getAllTodos());
                case "3" -> deleteTodo();
                case "0" -> {
                    todoView.displayMessage("Arrivederci, alla prossima!");
                    return;
                }
                default -> todoView.displayMessage("Non e' possibile eseguire questo comando, riprova per favore...");
            }
            todoView.waitForEnter();
        }

    }


    public void addTodo() {
        String title = todoView.getInput("Dimmi per favore come si chiama l'impegno: ");
        String description = todoView.getInput("Descrivimi l'impegno per favore: ");

        todoView.displayMessage("Sto aggiungendo l'impegno alla tua lista, dammi solo un attimo per favore...");
        todoService.addTodo(new Todo(title, description));

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                todoView.displayMessage("Perfetto! Ho aggiunto l'impegno alla lista!");
            }
        }, 2000); // Delay in milliseconds (2000ms = 2 seconds)
    }


    public void deleteTodo() {
        int todoIdToRemove = Integer.parseInt(todoView.getInput("Dimmi per favore il numero dell'impegno che vuoi rimuovere: "));
        todoView.displayMessage("Procedo a cancellare l'impegno");
        todoService.deleteTodo(todoIdToRemove);

        new Timer().schedule(new TimerTask() {
             @Override
             public void run() {
                todoView.displayMessage("Perfetto! Ho cancellato l'impegno");
             }
        }, 2000); // Delay in milliseconds (2000ms = 2 seconds)

        /*else{
            consoleView.displayMessage("Accipicchia, l'impegno che vuoi eliminare non c'e' nella tua lista, forse lo hai gia' completato!");
        }*/
    }
}
