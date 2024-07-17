/**
 * Represents a game board for a chess game
 */
package board;
import piece.*;

public class Board {
    public Spot[][] board = new Spot[8][8];

    /**
     * Initializes an 8x8 board
     */
    public Board() {
        int spotNum = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(spotNum % 2 == 0) {
                    board[i][j] = new Spot(i, j, "##", null);
                } else {
                    board[i][j] = new Spot(i, j, "    ", null);
                }
                spotNum++;
            }
            spotNum++;
        }
        setOriginalPieces();
    }


    /*
     * Returns information for the Piece at pos Position
     * @param pos Desired user input Position
     * @return Returns the Piece at input Position
     */
    /*
    public Piece getPiece(Position pos) {
        // temporary nonsense
        Rook temp = new Rook("white");
        return temp;
    } */

    /**
     * Moves the piece at "from" position to "to" position on the board
     * @param from Position object for "from" coordinates
     * @param to Position object for "to" coordinates
     */
    /* public void movePiece(Position from, Position to) {

    } i commented this

    /**
     * Displays a blank board with rows numbered 1-8 and columns labeled A-H
     * Board will be updated to display pieces
     */
    public void display() {
        int columnNum = 8;
        int col = 0;
        System.out.println("  A  B  C  D  E  F  G  H ");
        for(int i = 0; i < 8; i++) {
            if(columnNum % 2 == 0 && board[i][col].piece == null) {
                System.out.print(columnNum + " ");
            } else {
                System.out.print(columnNum);
            }

            for(int j = 0; j < 8; j++) {
                if(board[i][j].piece != null) {
                    System.out.print(board[i][j].piece.toString());
                } else {
                    System.out.print(board[i][j].originalPlacement);
                }
            }
            columnNum--;
            col++;
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Method to create new Piece objects for the first and last 2 rows of the board.
     * Completely resets the placement of the pieces
     */
    public void setOriginalPieces() {
        for(int i = 0; i < 8; i++) {
            board[1][i].piece = new Pawn("black");
            board[6][i].piece = new Pawn("white");
        }
        board[0][0].piece = new Rook("black");
        board[0][1].piece = new Knight("black");
        board[0][2].piece = new Bishop("black");
        board[0][3].piece = new Queen("black");
        board[0][4].piece = new King("black");
        board[0][5].piece = new Bishop("black");
        board[0][6].piece = new Knight("black");
        board[0][7].piece = new Rook("black");

        board[7][0].piece = new Rook("white");
        board[7][1].piece = new Knight("white");
        board[7][2].piece = new Bishop("white");
        board[7][3].piece = new Queen("white");
        board[7][4].piece = new King("white");
        board[7][5].piece = new Bishop("white");
        board[7][6].piece = new Knight("white");
        board[7][7].piece = new Rook("white");
    }
}
