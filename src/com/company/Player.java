package com.company;

public class Player {
    protected String username;
    private int numWins;


    public Player(String name){
        this.username = name;
        this.numWins = 0;
    }

    void incrementWins() {
        numWins ++;
    }

    @Override
    public String toString() {
        return "Player" +
                "name='" + username + '\'' +
                ", wins=" + numWins;
    }
}
