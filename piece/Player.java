/**
 * Represents a player of the Chess game
 */
package piece; 
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
}