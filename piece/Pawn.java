
/**
 * Represents a Pawn piece in a chess game
 * Subclass of the Piece class
 */
package piece;
import board.*;
public class Pawn extends Piece {
    private final char firstChar;

    /**
     * Initializes a Pawn object with the desired color and position
     * @param color The color of the Pawn ("white" or "black")
     */
    public Pawn(String color, String unicode) {
        super(color, unicode);
        firstChar = color.charAt(0);
    }

    /**
     * Validates whether the move inputted by the user is a valid move for this piece
     * @param <Player>
     */
    @Override
    public boolean validMove(Spot[][] board, Spot start, Spot end, Player currentTurn) {
        if (end.getPiece() != null && !(start.getPiece().getColor().equals(currentTurn.getColor()))) { 
            return false;
            
        }

        int x = start.getX();
        int y = start.getY();
        int xEnd = end.getX();
        int yEnd = end.getY();
        int direction = currentTurn.getColor().equals("white") ? -1 : 1; // White pawns move up (-1), black pawns move down (+1)

        // Move forward one square
        if (xEnd == (x + direction) && yEnd == y && end.getPiece() == null) {
            return true;
        }

        // Initial two-square move
        if (xEnd == x + (2 * direction) && yEnd == y && end.getPiece() == null) {
            // Ensure both squares are empty
            if ((x == 6 && currentTurn.getColor().equals("white")) || (x == 1 && currentTurn.getColor().equals("black"))) {
                if (board[end.getX()][end.getY()].getPiece() == null) {
                    return true;
                }
            }
        }
        // Capture diagonally

        return(Math.abs(yEnd - y) == 1 && xEnd == x + direction && end.getPiece() != null && !end.getPiece().getColor().equals(this.getColor()));
                
    }

    /**
     * Overridden toString method to configure how a Pawn is output to the screen
     */
    @Override
    public String toString() {
        return " " + firstChar + "p";
    }
}
