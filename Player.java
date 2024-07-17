/**
 * Represents a player of the Chess game
 */
public class Player {
    private String color;

    /**
     * Initializes a player with the specified color
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
     * @param x Integer value for the board row
     * @param y Integer value for the board column
     */
    public static void makeMove(int x, int y) {
        
    }
}