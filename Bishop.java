public class Bishop extends Piece {
    public Bishop(String color){this.color = color;}

    public String MakeString()
    {
        return color.charAt(0) + "B";
    }
}
