import java.util.*;

public class Chomp extends AbstractStrategyGame {
    private int rows;
    private int columns;
    private int winner;
    private int nextPlayer;
    private boolean[][] chomped;

    public Chomp() {
        this.rows = 3;
        this.columns = 3;
        this.chomped = new boolean[rows][columns];
        this.nextPlayer = 1;
        this.winner = -1;

    }

    public String instructions() {
        return "Players will take turns choosing a square written as: row column.\n"
            + "Choosing (row, column) chomps that square and all squares below and to \n" 
            + "the right. The top left square (0, 0) is poison and who ever chomps that \n"
            + "square loses immediately.";
    }

    public String toString() {
        String result = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (chomped[i][j]) {
                    result += "X ";

                } else {
                    result += ". ";
                }
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

    public int getWinner() {
        return winner;
    }

    public int getNextPlayer() {
        return nextPlayer;
    }

    public String getMove(Scanner input) {
        System.out.print("Enter move (row, column): ");
        return input.nextLine();
    }

    public void makeMove(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        if (winner != -1) {
            throw new IllegalArgumentException();
        }

        int space = input.indexOf(" ");
        int row = Integer.parseInt(input.substring(0, space));
        int column = Integer.parseInt(input.substring(space + 1));
        
        if (row < 0 || row >= rows) {
            throw new IllegalArgumentException();

        } else if (column < 0 || column >= columns) {
            throw new IllegalArgumentException();

        } else if (chomped[row][column]) {
            throw new IllegalArgumentException();
        }

        for (int i = row; i < rows; i++) {
            for (int j = column; j < columns; j++) {
                chomped[i][j] = true;
            }
        }

        if (row == 0 && column == 0) {
            winner = 3 - nextPlayer;
            nextPlayer = -1;

        } else {
            nextPlayer = 3 - nextPlayer;
        }
    }
}