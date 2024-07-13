public class Pawn extends Piece {
public Pawn(String color){this.color = color;}

public String MakeString()
    {
        return color.charAt(0) + "P";
    }
    
}
