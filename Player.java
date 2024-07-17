/**
 * Represents a player of the Chess game
 */
import piece.*;
public class Player {
    protected String color;
    protected char newTempPositionX;
    protected char newTempPositionY;
    protected char oldTempPositionX;
    protected char oldTempPositionY;
    protected char buffer = ' '; // checks for spacing when validating userInput

    protected int newPositionX;
    protected int newPositionY;
    protected int oldPositionX;
    protected int oldPositionY;

    /**
     * Initializes a player with the specified color
     *
     * @param color The color representing the player ('White' or 'Black')
     */
    public Player(String color) {
        this.color = color;
    }

    /**
     * Returns the color representing the player
     *
     * @return The player's color
     */
    public String getColor() {
        return color;
    }

    /**
     * Allows the player to input move and attempts to execute on the board
     *
     * @param x Integer value for the board row
     * @param y Integer value for the board column
     */
    public void makeMove(int x, int y) {

    }

    public String getUserInput(String userInput) {
        // String should be 5 chars long; separate into separate variables @string positions. Ex. "E2 E4".
        oldTempPositionX = userInput.charAt(0);     // E
        oldTempPositionY = userInput.charAt(1);     // 2

        newTempPositionX = userInput.charAt(3);     // E
        newTempPositionY = userInput.charAt(4);     // 4

        // change both new and old positions into integers to put in board[x][y] by using switch statements, 16 total
        // oldPositionX letters A-H
        switch (oldTempPositionX) {
            case 'A':
                oldPositionX = 0;
                break;
            case 'B':
                oldPositionX = 1;
                break;
            case 'C':
                oldPositionX = 2;
                break;
            case 'D':
                oldPositionX = 3;
                break;
            case 'E':
                oldPositionX = 4;
                break;
            case 'F':
                oldPositionX = 5;
                break;
            case 'G':
                oldPositionX = 6;
                break;
            case 'H':
                oldPositionX = 7;
                break;
        }

        // newPositionX letters A-H
        switch (newTempPositionX) {
            case 'A':
                newPositionX = 0;
                break;
            case 'B':
                newPositionX = 1;
                break;
            case 'C':
                newPositionX = 2;
                break;
            case 'D':
                newPositionX = 3;
                break;
            case 'E':
                newPositionX = 4;
                break;
            case 'F':
                newPositionX = 5;
                break;
            case 'G':
                newPositionX = 6;
                break;
            case 'H':
                newPositionX = 7;
                break;
        }

        // oldPositionY numbers 1-8
        switch (oldTempPositionY) {
            case '1':
                oldPositionY = 7;
                break;
            case '2':
                oldPositionY = 6;
                break;
            case '3':
                oldPositionY = 5;
                break;
            case '4':
                oldPositionY = 4;
                break;
            case '5':
                oldPositionY = 3;
                break;
            case '6':
                oldPositionY = 2;
                break;
            case '7':
                oldPositionY = 1;
                break;
            case '8':
                oldPositionY = 0;
                break;
        }
        // newPositionY numbers 1-8
        switch (newTempPositionY) {
            case '1':
                newPositionY = 7;
                break;
            case '2':
                newPositionY = 6;
                break;
            case '3':
                newPositionY = 5;
                break;
            case '4':
                newPositionY = 4;
                break;
            case '5':
                newPositionY = 3;
                break;
            case '6':
                newPositionY = 2;
                break;
            case '7':
                newPositionY = 1;
                break;
            case '8':
                newPositionY = 0;
                break;
        }
        // At this point, the user input should be converted into characters which were then converted into integers.
        // The next step is to use those integers as the x and y positions in the board[][] to direct the move.

        //System.out.print(toString());
        return toString();
    }

    @Override
    public String toString() {
        return "Old: " + oldPositionX + ", " + oldPositionY + " New: " +  newPositionX + ", " + newPositionY;
    }

    /*
    public boolean movePiece(int oldPositionX, int oldPositionY, int newPositionX, int newPositionY) {
        // first checks for if there's a piece already in the new board position
        // no = spot is open, so update the board and move there
        // yes = spot is taken; check if the piece is the king of the other team (in which case the game is terminated)
        // if the spot is taken but not by the king, check if the piece is on the same team
        // if yes, return false as you cannot move there (print error message and ask for another input)
        // if no, move to that spot, capture the other team, and update accordingly


    }

    public boolean isKingChecked(int oldPositionX, int oldPositionY, int newPositionX, int newPositionY) {
        // check if new position is the king of the other team -> is it a king? is it on the other team?
        // if either = no, return false, otherwise return true at end
        // checks if king
        if(!(board[newPositionX][newPositionY].piece instanceof King)) {
            return false;
        }
        // checks if on other team
        else if (board[newPositionX][newPositionY].piece.color == board[oldPositionX][oldPositionY].piece.color) {
            return false;
        }
        // returns true if is king and is not on the same team AKA the king is checked and the game needs to be
        // terminated.
        return true;
    }

     */


}