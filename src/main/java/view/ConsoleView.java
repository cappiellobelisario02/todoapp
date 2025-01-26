package view;

import model.Todo;

import java.util.Map;
import java.util.Scanner;

public class ConsoleView implements View{

    private final Scanner scanner = new Scanner(System.in);

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

    @Override
    public void displayMenu() {
        System.out.println("1. Aggiungi un impegno");
        //System.out.println("2. Modifica un impegno");
        System.out.println("2. Mostra tutti gli impegni");
        System.out.println("3. Cancella un impegno");
        System.out.println("0. Esci dall'applicazione");
    }

    @Override
    public void displayTodos(Map<Integer, Todo> todos) {
        System.out.println("Ecco qui la lista di tutti gli impegni che hai, spero tu non li abbia dimenticati >D");
        if(todos.isEmpty()){
            System.out.println("Oh wow, non hai impegni! Ricorda che puoi aggiungerne quanti ne vuoi!");
            return;
        }
        todos.forEach((key, todo)-> System.out.println("N.:" + key + ",\n Titolo: " + todo.getTitle() + ",\nDescrizione: " + todo.getDescription()));
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

    public void waitForEnter(){
        System.out.println("\nPer continuare ad inviare comandi premi il tasto invio...");
        try {
            System.in.read();
        } catch (Exception e) {
            //empty body
        }
    }
}
