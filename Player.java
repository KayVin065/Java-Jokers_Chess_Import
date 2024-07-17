/**
 * Represents a player of the Chess game
 */
import piece.*;
public class Player {
    protected String color;

    /**
     * Initializes a player with the specified color
     *
     * @param color The color representing the player ('White' or 'Black')
     */
    public Player(String color) {
        this.color = color;
    }

    /**
     * Returns the color representing the player
     * @return The player's color
     */
    public String getColor() {
        return color;
    }

    /**
     * Allows the player to input move and attempts to execute on the board
     *
     * @param x Integer value for the board row
     * @param y Integer value for the board column
     */
    public void makeMove(int x, int y) {

    }

    @Override
    public String toString() {
        return "Player toString()";
    }
}