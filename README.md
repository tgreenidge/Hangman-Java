# Background
About a year ago, I created a Command Line Application Hangman Game that was built in Python. For this project java, was used, however, due to time constraints this project is a blend of CLI and GUI using Java Swing.

The original Python Command Line Application game may be found and downloaded at https://github.com/tgreenidge/HangmanCLI. The API that was used to create the dictionary is no longer publicly available, since it was provided by LinkedIn as part of an interview process.

# Technologies Used
  Java
  Swing

# Installation instructions
* Download the repo and open project in an IDE - (preferrably Intellij)
* Run the src/com.company.Main.java file
* Follow instructions in the command line to start, but play game in the GUI that pops up when game play is selected.

# Process/approach
The dictionary that was used in the project mentioned above was condensed into an array of words. The word used in play was randomly accessed via a random index selection.


# Unsolved problems
* Although a history may be accessed, need to fix the flow menu to allow multiple games to be played in the same session, and the history of those games be displayed.
* Due to time constraints, did not put everything into the GUI. Need to create widgets for the menu options and game history so that all of the application resides in the GUI.

# Your biggest wins and challenges
## Wins
* Getting the dashes and letters to match up correctly
* Getting the interface to be fixed. Previously parts of the game window changed size when input was submitted.

## Challenges
* History Class - getting history of all games played previously.
* Menu logic - not working correctly at time of submission. Need to refactor.

  
# Hangman Rules

* A secret word is retrieved from the dictionary (file) by the computer.
* The player tries to solve the secret word by guessing either a letter or word at a time.
* Should the user guess a correct letter, the player gets another chance.
* The game ends when the user solves the problem, or if the user has 6 guesses, whichever comes first.

# Dictionary
The dictionary is simply an array of words, condensed from the original dictionary used before.

# User Interface
User is prompted to select game play, see history, or quit game.

## Game Play
* Below is the diagram that represents shows play in the GUI only.

* At the start of the game, the user is shown blanks, one for each letter in the secret word.

* Words are restricted to 8 letters, and letters are restricted to 1 letter. When a user submits a letter of word, it is validated to ensure that only letters are submitted.

* Should the user guess an incorrect word or letter, the Hangman gets filled in, one body part at a time. 

* Should the user guess a correct word or letter, the letters are filled in by replacing the blanks.

* The user input would be typed via the keyboard

* Game ends if user makes 6 guesses before making correct guess

