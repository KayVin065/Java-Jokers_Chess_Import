/**
 * Represents a Rook piece in a chess game
 * Subclass of the Piece class
 */
package piece;

import board.*;

public class Rook extends Piece {
    private String color;
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
     * Outputs a list of possible moves that the Rook can make
     */
    @Override
    public boolean validMove(Spot[][] board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) {
            return false;
        }

        int x = start.getX();
        int y = start.getY();

        if (x != end.getX() && y != end.getY()) {
            return false;
        }

        /*  Check if there are any pieces in the path
        if (x == end.getX()) {
            int min = Math.min(y, end.getY());
            int max = Math.max(y, end.getY());
            for (int i = min + 1; i < max; i++) {
                if (board.getBox(x, i).getPiece() != null) {
                    return false;
                }
            }
        } 
        else {
            int min = Math.min(x, end.getX());
            int max = Math.max(x, end.getX());
            for (int i = min + 1; i < max; i++) {
                if (board.getBox(i, y).getPiece() != null) {
                    return false;
                }
            }
        }*/

        return true;
    }

    @Override
    public String toString() {
        return " " + firstChar + "R";
    }
}
