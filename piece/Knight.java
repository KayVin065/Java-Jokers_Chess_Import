package piece;

import board.*;

public class Knight extends Piece {
    private String color;
    private char firstChar;

    /**
     * Initializes a Knight object with the desired color and position
     * @param color The color of the Knight ("white" or "black")
     */
    public Knight(String color) {
        super(color);
        firstChar = color.charAt(0);
    }

    /**
     * Outputs a list of possible moves that the Knight can make
     */
    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().getColor().equals(this.getColor())) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        if (x * y == 2) {
            return true;
        }
        
        return false;
    }

    @Override
    public String toString() {
        return " " + firstChar + "N";
    }


    // returns a bool value describing the state of the potential move (true = okay move, false = not an okay move). Can be used in 
    // addition to other functions to make decisions. Takes in a 2D array called board of type Piece, the current x and y positions of 
    // the knight, and then the desired x and y positions of the knight after the move.
    // there are 2 moves for the knight: move 1 vertical and 2 horizontal positions, OR move 2 vertical and 1 horizontal posiitons. Use
    // absolute values to get rid of direction (up/down, left/right doesn't matter).
    /* 
    public boolean validMove(Piece[][] board, currX, currY, newX, newY) {
        if(Math.abs(newX - currX) == 2 && Math.abs(newY - currY) == 1) {
            return true;
        }
       if(Math.abs(newX - currX) == 1 && Math.abs(newY - currY) == 2) {
            return true;
        }
        return false;
    }
    */
}
