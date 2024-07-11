public class Knight extends Piece
{
    public Knight(String color){this.color = color;}
    public String MakeString()
    {
        return color.charAt(0) + "N";
    }
}
