package com.company;

import java.util.*;
import java.io.*;


/**
 * This contains the logic for the hangman game
 *
 */
public class Hangman {

    protected static int MAX_GUESSES = 6;
    protected int guessesRemaining = MAX_GUESSES;
    private int randomNum;
    private int dictLength;
    protected Player player;
    protected History history;

    protected String secretWord;
    protected ArrayList<String> incorrectLettersGuessed = new ArrayList<String>();
    protected ArrayList<String> incorrectWordsGuessed = new ArrayList<String>();
    protected StringBuilder correctLettersGuessed = new StringBuilder();
    private int numGuessesSofar;
    private HangmanGui hangmanGui;
    private Dictionary dictionary;


    public Hangman (Player player, History history) {
        this.player = player;
        this.history = history;

        //dictionary to be used for game play
        dictionary = new Dictionary();

        //max number of guesses that user has
        guessesRemaining = MAX_GUESSES;

        //number of guesses the user made so far
        numGuessesSofar = 0;

        //length of the dictionary to be used to create a random word in the dictionary
        dictLength = dictionary.words.length;

        randomNum = (int) Math.floor(Math.random() * dictLength);

        // secret word that user must guess
        secretWord = dictionary.words[randomNum].toUpperCase();

        //correct letters that the user guessed with position.
        correctLettersGuessed = getBlanks();

        //pass this hangman game into the Hangman GUI constructor
        hangmanGui = new HangmanGui (this, secretWord);

    }

    public void startGame() {
        //make UI visible
        hangmanGui.setVisible (true);

        //print dashes of secret word on display
        hangmanGui.printLettersGuessed (correctLettersGuessed);
        hangmanGui.printGameStatistics (Integer.toString(guessesRemaining),
                incorrectWordsGuessed.toString(),
                incorrectLettersGuessed.toString() );

    }

    /**
     * This method prints blanks at start of the game
     **/
    public StringBuilder getBlanks() {
        StringBuilder dashes = new StringBuilder();
        for (int i = 0; i < secretWord.length(); i++) {

            if (i == secretWord.length() - 1) {
                dashes.append("_");
            } else {
                dashes.append("_ ");
            }
        }
        return dashes;
    }


    //ends game
    public void endGame() {
        hangmanGui.setVisible(false);
        Main.printMenu(player.username);
    }

}

