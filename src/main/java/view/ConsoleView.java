package view;

import model.Todo;

import java.util.Map;
import java.util.Scanner;

public class ConsoleView implements View{

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("""
                ░▒▓████████▓▒░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░░▒▓█▓▒░      ░▒▓████████▓▒░\s
                   ░▒▓█▓▒░  ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░       \s
                   ░▒▓█▓▒░  ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░       \s
                   ░▒▓█▓▒░  ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓███████▓▒░░▒▓█▓▒░      ░▒▓██████▓▒░  \s
                   ░▒▓█▓▒░  ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░       \s
                   ░▒▓█▓▒░  ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░       \s
                   ░▒▓█▓▒░   ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓████████▓▒░▒▓████████▓▒░                                                                                                   \s
                \s""");

        System.out.println("\\u001B[1m Benvenuto in To-Doable, l'agenda in cui tu ricordi a te stesso che tutto e' possibile!");
        System.out.println("Cosa desideri fare? In basso sono riportate le operazioni");
        System.out.println("Per eseguire un comando ti basta digitare da tastiera uno dei numeri in elenco");
        System.out.println("1. Aggiungi un impegno");
        System.out.println("2. Modifica un impegno");
        System.out.println("3. Mostra tutti gli impegni");
        System.out.println("4. Cancella un impegno");
    }

    @Override
    public void displayTodos(Map<Integer, Todo> todos) {
        System.out.println("Ecco qui la lista di tutti gli impegni che hai, spero tu non li abbia dimenticati >D");
        if(todos.isEmpty()){
            System.out.println("Oh wow, non hai impegni! Ricorda che puoi aggiungerne quanti ne vuoi!");
            return;
        }
        todos.forEach((_, todo)-> System.out.println(todo.getId() + ",\n Titolo: " + todo.getTitle() + ",\nDescrizione: " + todo.getDescription()));
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
