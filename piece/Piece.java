/**
 * Abstract class for Piece objects
 */
package piece;
import board.*;


public abstract class Piece {
    protected String color;
    protected boolean isCaptured = false;

    /**
     * Initializes a Piece superclass object with the desired color
     * @param color the color, "white" or "black"
     */
    public Piece(String color) {
        this.color = color;
    }

    /**
     * Gets the color of the Piece
     * @return the color of the Piece as a String
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Implemented in each Piece subclass to check if a move is valid
     * @param <Player>
     * @param board the board to be changed
     * @param start the "from" Spot 
     * @param end the "to" Spot
     * @return true if the move is valid, false if the move is not
     */
    public abstract boolean validMove(Spot[][] board, Spot start, Spot end, Player currentTurn);
}