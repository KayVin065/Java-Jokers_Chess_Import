/**
 * Represents a Pawn piece in a chess game
 * Subclass of the Piece class
 */
package piece;
import board.*;
public class Pawn extends Piece {
    private char firstChar;

    /**
     * Initializes a Pawn object with the desired color and position
     * @param color The color of the Pawn ("white" or "black")
     */
    public Pawn(String color) {
        super(color);
        firstChar = color.charAt(0);
    }

    /**
     * Validates whether the move inputted by the user is a valid move for this piece
     * @param <Player>
     */
    @Override
    public boolean validMove(Spot[][] board, Spot start, Spot end, Player currentTurn) {
        if (end.getPiece() != null && end.getPiece().getColor().equals(currentTurn.getColor())) {
            System.out.println("ERROR: cannot move piece thats not yours!!!");
            Chess.play(currentTurn);

            return false;
        }

        int y = start.getX();
        int x = start.getY();
        int yEnd = end.getX();
        int xEnd = end.getY();
        int direction = this.getColor().equals("white") ? -1 : 1; // White pawns move up (-1), black pawns move down (+1)

        // Move forward one square
        if (xEnd == x && yEnd == y + direction && end.getPiece() == null) {
            return true;
        }

        // Initial two-square move
        if (xEnd == x && yEnd == y + 2 * direction && end.getPiece() == null) {
            // Ensure both squares are empty
            if ((y == 6 && this.getColor().equals("white")) || (y == 1 && this.getColor().equals("black"))) {
                if (board[end.getX()][end.getY() + direction].getPiece() == null) {
                    return true;
                }
            }
        }

        // Capture diagonally
        if (Math.abs(xEnd - x) == 1 && yEnd == y + direction && end.getPiece() != null && !end.getPiece().getColor().equals(this.getColor())) {
            return true;
        }

        return false;
    }

    /**
     * Overridden toString method to configure how a Pawn is output to the screen
     */
    @Override
    public String toString() {
        return " " + firstChar + "p";
    }
}