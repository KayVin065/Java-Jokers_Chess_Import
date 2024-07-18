/**
 * Represents a game board for a chess game
 */
package board;
import piece.*;

public class Board {
    public Spot[][] board = new Spot[8][8];
    protected char newTempPositionX;
    protected char newTempPositionY;
    protected char oldTempPositionX;
    protected char oldTempPositionY;

    /**
     * Initializes an 8x8 board with pieces in original positions
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

    /**
     * Displays a board with rows numbered 1-8 and columns labeled A-H
     * This will be called every time the board is updated or displayed
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
                    System.out.print(" " + board[i][j].piece.toString());
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

    /**
     * Takes in user input and translates character into an int. Creates ints to be 
     * used as actual element numbers for the board
     * @param x The character that is supposed to be translated into an int
     * @return Returns the translated number to aid with coordinate conversion
     */
    public static int translateMove(char x) {
        switch (x)
        {
            case '8', 'A' -> {
                return 0;
            }
            case '7', 'B' -> {
                return 1;
            }
            case '6', 'C' -> {
                return 2;
            }
            case '5', 'D' -> {
                return 3;
            }
            case '4', 'E' -> {
                return 4;
            }
            case '3', 'F' -> {
                return 5;
            }
            case '2', 'G' -> {
                return 6;
            }
            case '1', 'H' -> {
                return 7;
            }
            default -> throw new AssertionError();
        }
    }

    /**
     * Takes in two Strings that represent the user input coordinates
     * @param input String representing the "to" and "from" coordinates as one line
     */
    public void movePiece(String input)
    {
        int fromPosy = translateMove(input.charAt(0));
        int fromPosx = translateMove(input.charAt(1));
        int toPosy = translateMove(input.charAt(3));
        int toPosx = translateMove(input.charAt(4));

        Piece temp = board[fromPosx][fromPosy].piece;
        board[fromPosx][fromPosy].piece = null;
        board[toPosx][toPosy].piece = temp;
        display();
        
        /*
        if(temp.validMove(board, board[fromPosx][fromPosy], board[toPosx][toPosy])) {
            board[fromPosx][fromPosy].piece = null;
            board[toPosx][toPosy].piece = temp;
            display();
        } else {
            System.out.println("Not a valid move");
        }
        */
    }

    /** Method to return spot at given coordinates
    * @param x x-coordinate of spot
    * @param y y-coordinate of spot
    */
    /*
    public Spot getBox(int x, int y) {
        return board[x][y];
    } */
}