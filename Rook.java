public class Rook extends Piece
{
    public Rook(String color){this.color = color;}
    public String MakeString()
    {
        return color.charAt(0) + "R";
    }
}
