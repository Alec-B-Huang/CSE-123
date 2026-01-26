// ALEC HUANG
// CSE 123
// C1
// 01/25/26
// TA: Ishita Mundra

import java.util.*;

// Class comment: Class represents a game of Chomp that can be played by implementing
// the AbstractStrategyGame interface.
public class Chomp extends AbstractStrategyGame {
    private int rows;
    private int columns;
    private int winner;
    private int nextPlayer;
    private int[][] chompedLayers;

    // BEHAVIOR: constructs a new Chomp game with 3 layers and 
    // sets the starting player.
    // EXCEPTIONS: none
    // RETURNS: none
    // PARAMETERS: none
    public Chomp() {
        this.rows = 6;
        this.columns = 6;
        this.nextPlayer = 1;
        this.winner = -1;
        this.chompedLayers = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                chompedLayers[i][j] = 3;
            }
        }
    }

    // BEHAVIOR: returns instructions for how to play Chomp and how to format moves
    // EXCEPTIONS: none
    // RETURNS: returns a long string with the instructions to the game
    // PARAMETERS: none
    public String instructions() {
        return "Players will take turns choosing a square written as: row column.\n"
            + "Each square starts with 3 layers, a move only chomps the squares on \n"
            + "the topmost available layer with the chosen rectangle below and to the right.\n"
            + "Chomped squares reveal the layer below which decreases layers by 1.\n"
            + "Taking the top left square (0 0) from the final layer makes the player lose."; 
    }

    // BEHAVIOR: returns a string showing the current board with remaining layers
    // and either the next player or the winner if the game is over.
    // EXCEPTIONS: none
    // RETURNS: returns updated board
    // PARAMETERS: none
    public String toString() {
        String result = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result += chompedLayers[i][j] + " ";
            }
            result += "\n";
        }

        if (winner == -1) {
            result +=  "Next player: " + nextPlayer;
        } else {
            result += "Winner: " + winner;
        }
        return result;
    }

    // BEHAVIOR: returns winning player number if the game is over. Returns -1 if 
    // game is not over.
    // EXCEPTIONS: none
    // RETURNS: returns winner 
    // PARAMETERS: none
    public int getWinner() {
        return winner;
    }

    // BEHAVIOR: returns which player's turn is next or returns -1 if game is over.
    // EXCEPTIONS: none
    // RETURNS: nextPlayer
    // PARAMETERS: none
    public int getNextPlayer() {
        return nextPlayer;
    }

    // BEHAVIOR: gets the player's inputted move and returns as string formatted as 
    // [row] [column]
    // EXCEPTIONS: none
    // RETURNS: string player's move
    // PARAMETERS: Scanner input to receive player's move
    public String getMove(Scanner input) {
        System.out.print("Enter move (row, column): ");
        return input.nextLine();
    }

    // BEHAVIOR: makeMove processes the player's move by "chomping" the
    // top most layer in the square, updates whose turn it is and 
    // ends game if poison square is chosen.
    // EXCEPTIONS: IllegalArgumentException
    //             if input == null;
    //             if winner != -1;
    //             if row < 0 || row >= rows;
    //             if column < 0 || column >= columns;
    //             if chomped[row][column] == 0;
    //             if parts.length != 2
    // RETURNS: none
    // PARAMETERS: String input the player's move
    public void makeMove(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        if (winner != -1) {
            throw new IllegalArgumentException();
        }

        input = input.trim();
        String[] parts = input.split("\\s+");
        if (parts.length != 2) {
            throw new IllegalArgumentException();
        }

        int row = Integer.parseInt(parts[0]);
        int column = Integer.parseInt(parts[1]);
        
        if (row < 0 || row >= rows) {
            throw new IllegalArgumentException();

        } else if (column < 0 || column >= columns) {
            throw new IllegalArgumentException();

        } else if (chompedLayers[row][column] == 0) {
            throw new IllegalArgumentException();
        }

        int maxLayer = 0;
        for (int i = row; i < rows; i++) {
            for (int j = column; j < columns; j++) {
                maxLayer = Math.max(maxLayer, chompedLayers[i][j]);
            }
        }

        for (int i = row; i < rows; i++) {
            for (int j = column; j < columns; j ++){
                if (chompedLayers[i][j] == maxLayer && chompedLayers[i][j] > 0) {
                    chompedLayers[i][j]--;
                }
            }
        }

        if (row == 0 && column == 0 && chompedLayers[0][0] == 0) {
            winner = 3 - nextPlayer;
            nextPlayer = -1;

        } else {
            nextPlayer = 3 - nextPlayer;
        }
    }
}
