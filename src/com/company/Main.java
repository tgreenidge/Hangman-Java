package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {



    //invokes a new hangman game
    public static void main(String[] args) {

        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine().toUpperCase();

        printMenu(username);

    }

    public static void printMenu(String username) throws InputMismatchException {
        Hangman hangmanGame = null;
        History history = History.getInstance(hangmanGame);
        boolean isRunning = true;

        do {
            Scanner scanner2 = new Scanner(System.in);

            System.out.println(username + ", Enter 1 to Play Hangman, 2 to See History, or 3 to Quit");


            try {
                int option = scanner2.nextInt();

                if (option == 1){
                    if (history.isPlayer(username)) {
                        hangmanGame = new Hangman(history.getPlayer(username), history);
                    } else {
                        Player player = new Player(username);
                        history.addPlayer(username, player);
                        hangmanGame = new Hangman(player, history);
                    }

                    hangmanGame.startGame();
                } else if(option ==2) {
                    history.printPlayerHistory(username);
                } else if (option == 3){
                    isRunning = false;
                    System.exit(0);
                } else {
                    throw new IllegalAccessException("Error: Enter 1 to Play Hangman, 2 to see History, or 3 to Quit");
                }
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("You did not enter a number between 1 and 3 inclusive");
            }

        } while(isRunning);
    }

}
