public class Queen extends Piece
{
    public Queen(String color){this.color = color;}
    public String MakeString()
    {
        return color.charAt(0) + "Q";
    }
}
