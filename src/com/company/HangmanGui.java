package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * This class holds the container for the UI for the Hangman Game
 *
 */
public class HangmanGui extends JFrame {
    protected Hangman hangGame;
    private int numGuessesRemaining;
    protected String secrWord;

    // protected frames that need to be accessed by the main Hangman Program
    protected JLabel secret = new JLabel ("");
    protected JTextField letterGuessed = new JTextField ();
    protected JTextField wordGuessed = new JTextField();

    protected JLabel guessesRemainingLabel =  new JLabel ();
    protected JLabel incorrectLettersLabel =  new JLabel ();
    protected JLabel incorrectWordsLabel =  new JLabel ();

    // Picture that is displayed when Game first starts
    protected JLabel stake = new JLabel(
            "<html>_ _ _ <br>|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>|<br>|<br>|<br>|<br>|<br>|<html>",
            SwingConstants.CENTER);

    //Normal font Settings
    public static Font f = new Font ("Helvetica", Font.BOLD, 10);

    //Section header font settings
    public static Font header1 = new Font("Helvetica", Font.BOLD, 18);
    public static Font header2 = new Font("Helvetica", Font.BOLD, 14);

    //Various panel formattings used for the UI
    public static void formatPanels (JPanel panel, JLabel label1, JLabel label2, JLabel label3) {
        panel.setFont(f);

        label1.setForeground (Color.WHITE);
        label2.setForeground (Color.WHITE);
        label3.setForeground (Color.WHITE);

        panel.setLayout (new BorderLayout());
        panel.add (label1, BorderLayout.NORTH);
        panel.add (label2, BorderLayout.CENTER);
        panel.add (label3, BorderLayout.SOUTH);
        panel.setBackground (Color.BLACK);
    }

    public static void formatPanels (JPanel panel, JLabel label, JTextField textBox,
                                     JPanel container) {
        label.setFont(f);
        panel.setLayout ( new BorderLayout() );
        panel.add (label, BorderLayout.WEST);
        panel.add (textBox, BorderLayout.CENTER);
        container.add (panel);
    }

    public static void formatPanels (JPanel panel, JButton button, JTextField textBox) {
        panel.setLayout ( new BorderLayout() );
        panel.add (textBox, BorderLayout.CENTER);
        panel.add (button, BorderLayout.SOUTH);
        panel.setBackground (Color.CYAN);
    }

    public static void formatPanels (JPanel panel1, JPanel panel2, JPanel container,
                                     int layoutType) {
        container.setLayout ( new BorderLayout() );

        if (layoutType == 1) {
            container.add (panel1, BorderLayout.WEST);
            container.add (panel2, BorderLayout.CENTER);
        } else {
            container.add (panel1, BorderLayout.NORTH);
            container.add (panel2, BorderLayout.SOUTH);
        }

    }


    //GUI Constructor
    public HangmanGui (Hangman hangmanGame, String secretWord) {

        setPreferredSize(new Dimension(900,700));
        setTitle ("Hangman");
        setResizable(false);

        hangGame = hangmanGame;
        numGuessesRemaining = hangGame.MAX_GUESSES;
        secrWord = secretWord;

        //add Hangman title to the panel
        JPanel gameTitle = new JPanel();
        JLabel hangmanTitle = new JLabel ("H A N G M A N");
        hangmanTitle.setFont (header1);

        gameTitle.add (hangmanTitle);

        //panel that holds letters for the secret word
        JPanel blanks = new JPanel();
        blanks.setLayout ( new BorderLayout()  );

        secret.setFont (header2);
        secret.setPreferredSize(new Dimension(450, 150));
        blanks.add(secret, BorderLayout.CENTER);

        //panel that holds man to be filled in
        JPanel man = new JPanel();


        stake.setFont (header1);
        stake.setPreferredSize(new Dimension(450, 150));

        //set stake text color to brown
        stake.setForeground (new Color(156, 93, 82));

        // grass below stake
        JLabel grass = new JLabel("////////////////////", SwingConstants.CENTER );
        grass.setFont (header1);
        grass.setForeground (Color.GREEN);

        man.setLayout ( new BorderLayout()  );
        man.add (stake, BorderLayout.NORTH);
        man.add (grass, BorderLayout.CENTER);
        man.setBorder (new EmptyBorder (10, 10, 10, 10) );

        JPanel hangmanPanel = new JPanel();
        formatPanels ( man, blanks, hangmanPanel, 1);


        JPanel topPanel = new JPanel();

        topPanel.setLayout ( new BorderLayout()  );
        topPanel.add (gameTitle, BorderLayout.NORTH);
        topPanel.add (hangmanPanel, BorderLayout.SOUTH);

        JPanel gameStats = new JPanel();
        gameStats.setLayout ( new BoxLayout(gameStats, BoxLayout.PAGE_AXIS) );

        //JTextField toBox = new JTextField ("", 40 );
        JLabel gameStatsLabel =  new JLabel ("Game Statistics");
        gameStatsLabel.setForeground (Color.WHITE);
        gameStatsLabel.setFont (header1);
        JPanel gameStatsHeader = new JPanel();

        gameStatsHeader.add (gameStatsLabel);
        gameStatsHeader.setBackground (Color.BLACK);


        JPanel gameInfo = new JPanel();
        formatPanels (gameInfo, guessesRemainingLabel, incorrectWordsLabel,
                incorrectLettersLabel);

        gameStats.setBorder(new EmptyBorder(10, 10, 10, 10));
        formatPanels (gameStatsHeader, gameInfo, gameStats, 2);


        // limit letter to 1 character
        letterGuessed.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (letterGuessed.getText().length() >= 1 )
                    e.consume();
            }
        });


        JButton letterButton = new JButton ("Guess A letter");

        //make button unclickable
        letterButton.setEnabled (false);
        JPanel letterPanel = new JPanel();

        formatPanels (letterPanel, letterButton, letterGuessed);


        // limit word to 8 characters
        wordGuessed.addKeyListener (new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ( wordGuessed.getText().length() >= 8 )
                    e.consume();
            }
        });

        JButton wordButton = new JButton ("Guess A Word");
        wordButton.setEnabled (false);

        JPanel wordPanel = new JPanel();
        formatPanels(wordPanel, wordButton, wordGuessed);

        // listens for a letter entered by user
        // if letters only not entered, error message displayed
        letterGuessed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String letter = letterGuessed.getText().toUpperCase();

                //test for letters only
                if (letter.matches ("^[a-zA-Z]+$")){
                    playGame (letter);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter letters only");
                    letterGuessed.setText("");
                }
            }

        });

        //listens for a word entered by user
        // if letters only not entered, error message displayed
        wordGuessed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String word = wordGuessed.getText().toUpperCase();


                //test for letters only
                if (word.matches ("^[a-zA-Z]+$")){
                    playGame (word);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter letters only");
                    //reset word field
                    wordGuessed.setText("");
                }
            }
        });


        JPanel guesses = new JPanel();

        //add some padding
        guesses.setBorder(new EmptyBorder(10, 10, 10, 10));

        formatPanels(letterPanel, wordPanel, guesses, 1);

        add (topPanel, BorderLayout.NORTH);
        add (guesses, BorderLayout.CENTER);
        add (gameStats, BorderLayout.SOUTH);

        setDefaultCloseOperation (EXIT_ON_CLOSE);
        pack();

    }

    // displays the letters guessed correctly in the hangman Panel
    public void printLettersGuessed( StringBuilder letterToDisplay ) {
        secret.setText( letterToDisplay.toString() );
        secret.setFont( header2 );
    }


    // This method displays update game statistics
    public void printGameStatistics(String guessesRemaining, String incorrectWordsGuessed,
                                    String incorrectLettersGuessed) {

        String remainingGuessesText = "Guesses Remaining: " +  guessesRemaining;
        String incorrectWordsText =  "Incorrect Words: " + incorrectWordsGuessed;
        String incorrectLettersText = "Incorrect Letters: " + incorrectLettersGuessed;

        guessesRemainingLabel.setText (remainingGuessesText);
        incorrectLettersLabel.setText (incorrectLettersText);
        incorrectWordsLabel.setText (incorrectWordsText);
    }

    public void playGame( String guess) {

        numGuessesRemaining --;

        if (guess.length() == 1) {
            ArrayList<Integer> indicesOfLetters = new ArrayList<Integer>();
            indicesOfLetters = getIndicesOfLetterInWord(guess);
            if (indicesOfLetters.size() == 0) {
                hangGame.incorrectLettersGuessed.add (guess);
            } else {
                replaceDashesWithLetters( indicesOfLetters, guess);
                if (hangGame.correctLettersGuessed.toString().matches("^[a-zA-Z]+$")){
                    userWinsGame();
                }
            }

            letterGuessed.setText("");

        } else {
            // user guessed word
            if ( guess.equals (secrWord) ){
                userWinsGame();
            } else {
                hangGame.incorrectWordsGuessed.add (guess);
            }

            wordGuessed.setText("");
        }

        printGameStatistics(Integer.toString(numGuessesRemaining),
                hangGame.incorrectWordsGuessed.toString(),
                hangGame.incorrectLettersGuessed.toString());

        fillMan (hangGame.MAX_GUESSES - numGuessesRemaining);

        if (numGuessesRemaining == 0) userLosesGame();

    }

    /**
     * This method determines the indices of the correct letters in the word
     **/
    public ArrayList<Integer> getIndicesOfLetterInWord( String response) {
        ArrayList<Integer> letterIndices = new ArrayList<Integer>();

        for (int i = 0; i < secrWord.length(); i++){
            if (response.charAt(0) ==  secrWord.charAt(i) ) letterIndices.add(i);
        }

        return letterIndices;
    }

    //this method is used to replace the dashes with the letters
    public void replaceDashesWithLetters (ArrayList<Integer> indices, String userResponse){
        for (int i : indices){
            if(i == 0) {
                hangGame.correctLettersGuessed.setCharAt(i, userResponse.charAt(0));
            } else {
                hangGame.correctLettersGuessed.setCharAt(i * 2, userResponse.charAt(0));
            }

        }

        printLettersGuessed (hangGame.correctLettersGuessed);
    }

    public void fillMan( int numTries) {
        String [] bodyParts = {"-----", "<br>|&nbsp;&nbsp;&nbsp;&nbsp;|", "<br>|&nbsp;&nbsp;&nbsp;O ",
                "<br>|&nbsp;&nbsp;&nbsp;- -", "<br>|&nbsp;&nbsp;&nbsp;&nbsp;| ", "<br>|&nbsp;&nbsp;&nbsp;/ \\"};
        String parts = "<html>";

        for (int i = 0; i < numTries; i++){
            parts += bodyParts[i];
        }

        stake.setText ( parts + "</html>" );

    }

    // Displays Congratulatory message that user Won, and ends game
    public void userWinsGame() {
        JOptionPane.showMessageDialog(null, "Congrats, you Won!");
        hangGame.player.incrementWins();
        hangGame.history.addPlayer(hangGame.player.username, hangGame.player);
        hangGame.history.updatePlayerHistory(hangGame.player.username, 1);
        hangGame.endGame();
    }

    // Displays message that user lost, and ends game
    public void userLosesGame() {
        JOptionPane.showMessageDialog(null, "Sorry, you lost! the secret word was " +
                secrWord);
        hangGame.history.addPlayer(hangGame.player.username, hangGame.player);
        hangGame.history.updatePlayerHistory(hangGame.player.username, 0);
        hangGame.endGame();
    }

}
