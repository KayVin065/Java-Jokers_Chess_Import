package piece;
public abstract class Piece {
    protected String color;
    protected boolean isCaptured = false;

    public Piece(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public abstract boolean possibleMove();
}