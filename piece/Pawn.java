package piece;

import board.*;

/**
 * Represents a Pawn piece in a chess game
 * Subclass of the Piece class
 */

public class Pawn extends Piece {
    private String color;
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
     * Outputs a list of possible moves that the Pawn can make
     */
    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().getColor().equals(this.getColor())) {
            return false;
        }

        int x = start.getX();
        int y = start.getY();
        int xEnd = end.getX();
        int yEnd = end.getY();
        int direction = this.getColor().equals("white") ? -1 : 1; // White pawns move up (-1), black pawns move down (+1)

        // Move forward one square
        if (xEnd == x && yEnd == y + direction && end.getPiece() == null) {
            return true;
        }

        // Initial two-square move
        if (xEnd == x && yEnd == y + 2 * direction && end.getPiece() == null) {
            // Ensure both squares are empty
            if ((y == 6 && this.getColor().equals("white")) || (y == 1 && this.getColor().equals("black"))) {
                if (board.getBox(x, y + direction).getPiece() == null) {
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
    }

    @Override
    public String toString() {
        return " " + firstChar + "p";
    }
}