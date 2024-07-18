package piece;
public class King extends Piece {
    private String color;
    private char firstChar;

    /**
     * Initializes a King object with the desired color and position
     * @param color The color of the King ("white" or "black")
     */
    public King(String color) {
        super(color);
        firstChar = color.charAt(0);
    }

    /**
     * Outputs a list of possible moves that the King can make
     */
    @Override
    public boolean possibleMove() {
        return false;
    }

    @Override
    public String toString() {
        return firstChar + "K";
    }
}
