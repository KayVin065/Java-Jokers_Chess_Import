/**
 * Represents a Queen piece in a chess game
 * Subclass of the Piece class
 */
package piece;

import board.*;

public class Queen extends Piece {
    private String color;
    private char firstChar;

    /**
     * Initializes a Queen object with the desired color and position
     * @param color The color of the Queen ("white" or "black")
     */
    public Queen(String color) {
        super(color);
        firstChar = color.charAt(0);
    }

    /**
     * Validates whether user input is valid move
     */
    @Override
    public boolean validMove(Spot[][] board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        if (x == y || start.getX() == end.getX() || start.getY() == end.getY()) {
            int xDirection = Integer.compare(end.getX(), start.getX());
            int yDirection = Integer.compare(end.getY(), start.getY());

            int xCurrent = start.getX() + xDirection;
            int yCurrent = start.getY() + yDirection;

            /*  Check all spots along the path to ensure there are no pieces in the way
            while (xCurrent != end.getX() || yCurrent != end.getY()) {
                if (board.getBox(xCurrent, yCurrent).getPiece() != null) {
                    return false;
                }
                xCurrent += xDirection;
                yCurrent += yDirection;
            }*/

            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return " " + firstChar + "Q";
    }
}
