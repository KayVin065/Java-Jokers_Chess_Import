
public abstract class Piece {
    public String color;
    public boolean moved;
    public int location[][];


    abstract boolean MoveSet(String InitialLocation, String FinalLocation);
}
/*
 * Valid King move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if |X2-X1|<=1 and |Y2-Y1|<=1.

Valid Bishop move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if |X2-X1|=|Y2-Y1|.

Valid Rook move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if X2=X1 or Y2=Y1.

Valid Queen move, a queen's move is valid if it is either a valid bishop or rook move.

Valid Knight move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if (|X2-X1|=1 and |Y2-Y1|=2) or (|X2-X1|=2 and |Y2-Y1|=1).

Valid Pawn move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if X2=X1 and Y2-Y1=1 (only for a white pawn).
srce: https://stackoverflow.com/questions/54972474/how-to-determine-valid-chess-moves
 */