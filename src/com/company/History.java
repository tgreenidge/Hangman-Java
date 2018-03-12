package com.company;

import java.util.HashMap;
import java.util.ArrayList;

public class History {
    private static History history = null;
    private static Hangman hangman;
    private static HashMap<String, Player> players;
    private static HashMap<String, ArrayList<Integer> > playerHistory;

    private History(Hangman hangman){
        this.hangman = hangman;
        players = new HashMap<String, Player>();
        playerHistory = new HashMap<String, ArrayList<Integer> >();
    }

    public static History getInstance(Hangman hangman){
        if(history == null){
            history = new History(hangman);
        }
        return history;
    }

    public void addPlayer (String username, Player player) {
//        if (players.get(username) == null) {
//            players.put(username, );
//        }

        players.put(username, player);

    }

    public static boolean isPlayer(String username) {
        return playerHistory.get(username) != null;
    }

    public static Player getPlayer(String username){
        return  players.get(username);
    }

    public static void updatePlayerHistory(String username, int didWin){
        if(!isPlayer(username)){
            playerHistory.put(username, new ArrayList<Integer>());
        }
        playerHistory.get(username).add(didWin);
    }

    public static void printPlayerHistory(String username) {
        if (isPlayer(username)) {
            System.out.println("Game           Result");
            for (int i = playerHistory.get(username).size() - 1; i >= 0; i--){
                if (playerHistory.get(username).get(i) == 1) {
                    System.out.println((i + 1) +  "             Won");
                } else {
                    System.out.println((i + 1) + "              Lost");
                }
            }
        } else {
            System.out.println("Nothing to display");
        }

    }
}
