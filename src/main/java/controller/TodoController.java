package controller;

import model.Todo;
import view.ConsoleView;

import java.time.LocalDate;

public class TodoController {

    private final TodoService todoService;
    private final ConsoleView todoView;

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
                case "2" -> displayAllTodos();
                case "3" -> deleteTodo();
                case "4" -> updateTodo();
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
        String task = todoView.getInput("Scrivi per favore l'impegno: ");
        String dueDay = todoView.getInput("Scrivi per favore la data prevista di conclusione dell'impegno (aaaa-MM-gg): ");

        todoView.displayMessage("Sto aggiungendo l'impegno alla tua lista, dammi solo un attimo per favore...");
        LocalDate dueDayFormatted = TodoStringParser.formatDateFromString(dueDay);
        if(!dueDay.isEmpty() && dueDayFormatted != null){
            todoService.addTodo(new Todo(task, dueDayFormatted));
        }else{
            todoService.addTodo(new Todo(task));
        }

        todoView.displayMessage("Perfetto! Ho aggiunto l'impegno alla lista!");
    }

    public void deleteTodo() {
        showTodos();

        int todoIdToRemove = TodoStringParser.stringToInt(todoView.getInput("Dimmi per favore il numero dell'impegno che vuoi rimuovere: "));

        if(todoIdToRemove != -1 && todoService.getTodo(todoIdToRemove) != null){
            todoView.displayMessage("Procedo a cancellare l'impegno");
            todoService.deleteTodo(todoIdToRemove);
        }
        else{
            todoView.displayMessage("Accipicchia, l'impegno che vuoi eliminare non c'e' nella tua lista, forse lo hai gia' completato!");
        }
    }

    public void updateTodo() {
        showTodos();

        // Ottieni l'ID dell'impegno da modificare
        int todoIdToModify = getValidTodoId();
        if(todoIdToModify == -1){
            todoView.displayMessage("Annullamento modifica...");
            return;
        }

        todoView.displayMessage("""
                Cosa vorresti modificare dell'impegno?\s
                1. Descrizione \s
                2. Data di scadenza \s
                3. Entrambi i campi
                """);
        int updateChoice = Integer.parseInt(todoView.getInput("Inserisci il numero corrispondente al comando: "));

        //indica se l'impegno è stato modificato con successo o no
        // Aggiorna l'impegno
        boolean successTask = false;
        boolean successDueDay = false;

        switch (updateChoice){
            case 1 -> {
                String task = getValidTaskString();
                successTask = todoService.updateTodo(todoService.getTodo(todoIdToModify), task);
            }
            case 2 -> {
                LocalDate localDateTime = getValidDueDate();
                successDueDay = todoService.updateTodo(todoService.getTodo(todoIdToModify), localDateTime);
            }
            case 3 ->{
                String task = getValidTaskString();
                successTask = todoService.updateTodo(todoService.getTodo(todoIdToModify), task);
                LocalDate localDateTime = getValidDueDate();
                successDueDay = todoService.updateTodo(todoService.getTodo(todoIdToModify), localDateTime);
            }
            default -> todoView.displayMessage("Hai inserito un comando errato! Assicurati di inserirne uno corretto la prossima volta!");
        }

        if (successTask && !successDueDay) {
            todoView.displayMessage("Titolo dell'impegno aggiornato con successo!");
        } else if(!successTask && successDueDay) {
            todoView.displayMessage("Data dell'impegno aggiornata con successo!");
        } else{
            todoView.displayMessage("Mi dispiace, ma l'impegno non c'e' o non e' stato aggiornato...");
        }
    }

    private int getValidTodoId() {
        int todoId;
        while (true) {
            try {
                todoId = Integer.parseInt(todoView.getInput("Inserisci l'ID dell'impegno che vuoi modificare: "));
                if (todoService.getTodo(todoId) != null) {
                    break; // ID valido e impegno esiste
                } else {
                    todoView.displayMessage("Non ho trovato nessun impegno con questo ID.");
                    if(todoView.getInput("Vuoi continuare ad inserire un ID per modificare un impegno? S=si, N=no").trim().equalsIgnoreCase("N")){
                        return -1;
                    }
                }
            } catch (NumberFormatException e) {
                todoView.displayMessage("Errore: Inserisci un numero valido.");
            }
        }
        return todoId;
    }

    private String getValidTaskString() {
        String task;
        while (true) {
            task = todoView.getInput("Inserisci il nuovo titolo dell'impegno: ").trim();
            if (isStringNonEmpty(task)) {
                break; // Titolo valido
            } else {
                todoView.displayMessage("Errore: Il titolo non può essere vuoto.");
            }
        }
        return task;
    }

    private LocalDate getValidDueDate() {

        while(true){
            String dueDay = todoView.getInput("Inserisci la nuova data di conclusione dell'impegno (dd-MM-yyyy): ").trim();
            LocalDate localDateTime = TodoStringParser.formatDateFromString(dueDay);
            if(isStringNonEmpty(dueDay) && localDateTime != null){
                return localDateTime;
            }
            todoView.displayMessage("La data che hai inserito e' vuota oppure e' nel formato sbagliato");
        }
    }

    private boolean isStringNonEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public void showTodos() {
        // Mostra tutti gli impegni se richiesto
        String seeAllTodos = todoView.getInput("Vorresti vedere la lista dei tuoi impegni prima di scegliere " +
                "l'impegno da modificare?\nS = sì, N = no: ").trim().toUpperCase();
        if (seeAllTodos.equals("S")) {
            displayAllTodos();
        } else {
            todoView.displayMessage("Assicurati di sapere quale impegno stai modificando!");
        }
    }

    public void displayAllTodos(){
        todoView.displayMessage("Ecco qui a te la lista di tutti i tuoi impegni!");
        todoView.displayTodos(todoService.getAllTodos());
    }
}
