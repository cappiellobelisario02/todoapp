package view;

import model.Todo;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView implements View{

    private final Scanner scanner = new Scanner(System.in);

    /**
     *  Displays the logo and a welcome message
     */
    public void displayWelcomeMessage(){
        System.out.println("""
                8888888 8888888888 ,o888888o.     8 888888888o.          ,o888888o.           .8.          8 888888888o   8 8888         8 8888888888  \s
                      8 8888    . 8888     `88.   8 8888    `^888.    . 8888     `88.        .888.         8 8888    `88. 8 8888         8 8888        \s
                      8 8888   ,8 8888       `8b  8 8888        `88. ,8 8888       `8b      :88888.        8 8888     `88 8 8888         8 8888        \s
                      8 8888   88 8888        `8b 8 8888         `88 88 8888        `8b    . `88888.       8 8888     ,88 8 8888         8 8888        \s
                      8 8888   88 8888         88 8 8888          88 88 8888         88   .8. `88888.      8 8888.   ,88' 8 8888         8 888888888888\s
                      8 8888   88 8888         88 8 8888          88 88 8888         88  .8`8. `88888.     8 8888888888   8 8888         8 8888        \s
                      8 8888   88 8888        ,8P 8 8888         ,88 88 8888        ,8P .8' `8. `88888.    8 8888    `88. 8 8888         8 8888        \s
                      8 8888   `8 8888       ,8P  8 8888        ,88' `8 8888       ,8P .8'   `8. `88888.   8 8888      88 8 8888         8 8888        \s
                      8 8888    ` 8888     ,88'   8 8888    ,o88P'    ` 8888     ,88' .888888888. `88888.  8 8888    ,88' 8 8888         8 8888        \s
                      8 8888       `8888888P'     8 888888888P'          `8888888P'  .8'       `8. `88888. 8 888888888P   8 888888888888 8 888888888888                                                                                                    \s
                \s""");

        System.out.println("\u001B[1mBenvenuto in To-Doable, l'agenda in cui tu ricordi a te stesso che tutto e' possibile!\u001B[0m");
        System.out.println("Cosa desideri fare? In basso sono riportate le operazioni");
    }

    /**
     *  Displays a simple options menu
     */
    @Override
    public void displayMenu() {
        System.out.println("1. Aggiungi un impegno");
        System.out.println("2. Mostra tutti gli impegni");
        System.out.println("3. Cancella un impegno");
        System.out.println("4. Modifica un impegno");
        System.out.println("0. Esci dall'applicazione");
    }

    /**
     * Displays all todos.
     *
     * @param todos structure that contains the todos
     */
    @Override
    public void displayTodos(Map<Integer, Todo> todos) {
        System.out.println("Ecco qui la lista di tutti gli impegni che hai, spero tu non li abbia dimenticati >D");
        if(todos.isEmpty()){
            System.out.println("Oh wow, non hai impegni! Ricorda che puoi aggiungerne quanti ne vuoi!");
            return;
        }
        todos.forEach((key, todo)-> System.out.println(key + ". Titolo: " + todo.getTask() + ", Termine entro il: " + todo.getDueDay().format(DateTimeFormatter.ISO_LOCAL_DATE)));
    }


    /**
     * This function prints the message with no delay
     *
     * @param message the message you want to get printed
     */
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * This function prompts a message for the user and then requests an input from the keyboard
     *
     * @param prompt the message you want to display before entering the input
     * @return string
     */
    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     *  Support function. Waits for a user input to let the flow of the program continue
     */
    public void waitForEnter(){
        System.out.println("\nPer continuare ad inviare comandi premi il tasto invio...");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
