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

    // Function to return the caller's x coordinate
    public int getX () {
        return x;
    }

    // Function to return the caller's y coordinate
    public int getY () {
        return y;
    }

    // Function that returns the piece in a spot
    public Piece getPiece () {
        return piece;
    }
}
/*
    // Subclass of Spot for Empty Spot Initialization
    class emptySpot extends Spot {
        public emptySpot(int x, int y, String originalPlacement, String color) {
            super(x, y, originalPlacement);
            this.color = color;
            this.piece = null;
        }
    }

    // Subclass of Spot for Occupied Spot Initialization
    class occupiedSpot extends Spot {
        public occupiedSpot(int x, int y, Piece piece, String originalPlacement, String color) {
            super(x, y, originalPlacement);
            this.color = color;
            this.piece = piece;
        }
    }
*/