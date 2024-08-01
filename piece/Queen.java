/**
 * Represents a Queen piece in a chess game
 * Subclass of the Piece class
 */
package piece;
import board.*;

public class Queen extends Piece {
    private final char firstChar;

    /**
     * Initializes a Queen object with the desired color and position
     * @param color The color of the Queen ("white" or "black")
     */
    public Queen(String color, String unicode) {
        super(color, unicode);
        firstChar = color.charAt(0);
    }

    /**
     * Validates whether the move inputted by the user is a valid move for this piece
     */
    @Override
    public boolean validMove(Spot[][] board, Spot start, Spot end, Player currentTurn) {
    // Check if the end spot is occupied by a piece of the same color
    if (end.getPiece() != null && end.getPiece().getColor().equals(start.getPiece().getColor())) {
        return false;
    }

    int xStart = start.getX();
    int yStart = start.getY();
    int xEnd = end.getX();
    int yEnd = end.getY();

    int xDifference = xEnd - xStart;
    int yDifference = yEnd - yStart;

    // Check for horizontal, vertical, or diagonal movement
    if (xDifference == 0 || yDifference == 0 || Math.abs(xDifference) == Math.abs(yDifference)) {
        int xDirection = Integer.compare(xEnd, xStart);
        int yDirection = Integer.compare(yEnd, yStart);

        int xCurrent = xStart + xDirection;
        int yCurrent = yStart + yDirection;

        // Check all spots along the path for obstruction
        while (xCurrent != xEnd || yCurrent != yEnd) {
            if (board[xCurrent][yCurrent].getPiece() != null) {
                return false; // Path is obstructed
            }
            xCurrent += xDirection;
            yCurrent += yDirection;
        }

        return true; // Valid move
    }

    return false; // Not a valid move for a queen
}