/**
 * Represents a Bishop piece in a chess game
 * Subclass of the Piece class
 */
package piece;
import board.*;

public class Bishop extends Piece {

    /**
     * Initializes a Bishop object with the desired color and position
     * @param color The color of the Bishop ("white" or "black")
     */
    public Bishop(String color, String unicode) {
        super(color, unicode);
    }

    /**
     * Validates whether the move inputted by the user is a valid move for this piece
     */
    @Override
    public boolean validMove(Spot[][] board, Spot start, Spot end, Player currentTurn) {
        if (end.getPiece() != null && end.getPiece().getColor().equals(start.getPiece().getColor())) { 
            return false;
        }
        
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        
        if (x != y) {
            return false;
        }
        

        int xDirection = start.getX() < end.getX() ?  1 : -1;
        int yDirection = start.getY() < end.getY() ?  1 : -1;

        int xCurrent = start.getX() + xDirection;
        int yCurrent = start.getY() + yDirection;
        //System.out.println(board[xCurrent][yCurrent].getPiece());
        //this will check if there's any pieces in the way if there is, then the move isn't valid
        while (xCurrent != end.getX() && yCurrent != end.getY()) {
        //    System.out.println(board[xCurrent][yCurrent].getPiece());
            if (board[xCurrent][yCurrent].getPiece() != null) {

                return false;
            }
            xCurrent += xDirection;
            yCurrent += yDirection; 
        }
        return true;
    }
}