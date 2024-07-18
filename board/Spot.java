package board;
import piece.*;

public class Spot {

    // attributes to hold Spot position and Piece.
    protected int x;
    protected int y;
    protected Piece piece;

    // String attribute for displaying the original state of the Spot on the board in the event that a Piece is
    // captured and the Spot must revert back to it's look upon initialization.
    protected String originalPlacement;

    /*
    IDEA: Make 2 constructors depending on whether they're initialized with (occupied) or without (not occupied) a
    Piece to ease initialization. This general abstract constructor is the base for the other 2 constructors and
    is used to set the attributes that both will have in common.
    */
    public Spot(int x, int y, String originalPlacement, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        this.originalPlacement = originalPlacement;
    }

    /**
     * Gets the x-coordinate for the invoking Spot object
     * @return the x-coordinate
     */
    public int getX () {
        return x;
    }
    
    /**
     * Gets the y-coordinate for the invoking Spot object
     * @return the y-coordinate
     */
    public int getY () {
        return y;
    }
    
    /**
     * Gets the Piece type for the invoking Spot object
     * @return a Piece object
     */
    public Piece getPiece () {
        return piece;
    }
}