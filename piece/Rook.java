/**
 * Represents a Rook piece in a chess game
 * Subclass of the Piece class
 */
package piece;
import board.*;

public class Rook extends Piece {
    private char firstChar;

    /**
     * Initializes a Rook object with the desired color and position
     * @param color The color of the Rook ("white" or "black")
     */
    public Rook(String color) {
        super(color);
        firstChar = color.charAt(0);
    }

    /**
     * Validates whether the move inputted by the user is a valid move for this piece
     */
    @Override
    public boolean validMove(Spot[][] board, Spot start, Spot end, Player currentTurn) {
        if (end.getPiece() != null && !(start.getPiece().getColor().equals(currentTurn.getColor()))) { 
            return false;
        }

        int x = start.getX();
        int y = start.getY();

        if (x != end.getX() && y != end.getY()) {
            return false;
        }

        // Check if there are any pieces in the path
        if (x == end.getX()) {
            int min = Math.min(y, end.getY());
            int max = Math.max(y, end.getY());
            for (int i = min + 1; i < max; i++) {
                if (board[end.getX()][i].getPiece() != null) {
                    return false;
                }
            }
        } 
        else {
            int min = Math.min(x, end.getX());
            int max = Math.max(x, end.getX());
            for (int i = min + 1; i < max; i++) {
                if (board[end.getX()][i].getPiece() != null) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Overridden toString method to configure how a Rook is output to the screen
     */
    @Override
    public String toString() {
        return " " + firstChar + "R";
    }
}