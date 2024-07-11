public class King extends Piece {
//TODO: Make king
public King(String color){this.color = color;}

public String MakeString()
    {
        return color.charAt(0) + "K";
    }
}
