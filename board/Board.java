/**
 * Represents a game board for a chess game
 */
package board;
import piece.*;

public class Board {
    public Spot[][] board = new Spot[8][8];

    /**
     * Initializes an 8x8 board with pieces in original positions
     */
    public Board() {
        int spotNum = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(spotNum % 2 == 0) {
                    board[i][j] = new Spot(i, j, " ##", null);
                } else {
                    board[i][j] = new Spot(i, j, "   ", null);
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
        System.out.println("  A  B  C  D  E  F  G  H ");
        for(int i = 0; i < 8; i++) {
            System.out.print(columnNum);
            
            for(int j = 0; j < 8; j++) {
                if(board[i][j].piece != null) {
                    System.out.print(board[i][j].piece.toString());
                } else {
                    System.out.print(board[i][j].originalPlacement);
                }
            }
            columnNum--;
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
    public void movePiece(int fromX, int fromY, int toX, int toY, Player player)
    {
        Piece temp = board[fromX][fromY].getPiece();

        board[fromX][fromY].piece = null;
        board[toX][toY].piece = temp;

        System.out.println();
        display();
        
    }

    // translates the userInput, saves the current player spot PIECE, and calls validMove(). if move is valid, calls movePiece and returns true.
    // if move is not valid, returns false.
    public boolean canMove(String input, Player player) {
        int fromPosy = translateMove(input.charAt(0));
        int fromPosx = translateMove(input.charAt(1));
        int toPosy = translateMove(input.charAt(3));
        int toPosx = translateMove(input.charAt(4));

        Piece temp = board[fromPosx][fromPosy].getPiece();

        if(temp.validMove(board, board[fromPosx][fromPosy], board[toPosx][toPosy], player)) {
            movePiece(fromPosx, fromPosy, toPosx, toPosy, player);
            return true;
        } else {
            return false;
        }
    }

    // returns the other team's king's position in a Spot variable (AKA board[x][y])
    public Spot getOtherTeamKingPosition(Player currentTurn) {

        Spot kingPosition = new Spot();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j].getPiece() instanceof King && !board[i][j].getPiece().getColor().equals(currentTurn.getColor())) {
                    kingPosition = board[i][j];
                }
            }
        }
        return kingPosition;
    }

        // returns the current team's king's position in a Spot variable (AKA board[x][y])
        public Spot getTeamKingPosition(Player currentTurn) {

            Spot kingPosition = new Spot();
    
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(board[i][j].getPiece() instanceof King && board[i][j].getPiece().getColor().equals(currentTurn.getColor())) {
                        kingPosition = board[i][j];
                    }
                }
            }
            return kingPosition;
        }

    // returns true if the king is currently checked, returns false otherwise
    // Piece checkKing is only passed so it can be updated in the main code and we can know what piece is able to check the king
    public boolean isChecked(Spot kingPosition, Player currentTurn, Piece checkKing, Spot checkKingPosition) {
        Piece temp = null;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                // if a spot has a piece of same color as currentTurn, and validMove() to the king's position returns true, king is checked
                // and return true

                if(board[i][j].getPiece() != null && board[i][j].getPiece().getColor().equals(currentTurn.getColor())) {

                    // get temp piece for the potential piece that can check the king to pass to validMove()
                    temp = board[i][j].getPiece();
                    // if it can move to the king, it has the king checked
                
                    if(checkKing != null && checkKing.validMove(board, board[i][j], kingPosition, currentTurn) == true) {
                        checkKing = board[i][j].getPiece();
                        checkKingPosition = board[i][j];
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmated(Spot kingPosition, Player currentTurn, Piece checkKing, Spot checkKingPosition) {
        // CASE1: King cannot make a move.
        // checkPiece = current temp piece from each spot to pass to validMove() to see if it can be moved to by the King
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
    
                if(kingPosition.getPiece().validMove(board, kingPosition, board[i][j], currentTurn)) {
                    // if king can move to a spot (this loop checks for every spot), see if it is stil checked in that current spot. The king
                    // must be able to not only move, but not be captured in the new position. Use checkKing in this because it holds the saved 
                    // piece from isChecked() that is checking the king.
                    if(isChecked(kingPosition, currentTurn, checkKing, checkKingPosition) == false) {
                        return false;
                    }
                }
                // CASE2: No piece on the King's team can capture the piece checking the King.
                // checks if every spot on the board has a piece that is on the king's team that is capable of capturing the piece checking the king.
                if(board[i][j].getPiece() != null && board[i][j].getPiece().getColor().equals(currentTurn.getColor()) && board[i][j].getPiece().validMove(board, board[i][j], checkKingPosition, currentTurn)) {
                    return false;
                }

                // CASE3: A piece on the King's team can sacrifice themselves by blocking the piece checking the king from capturing it.
                // MAY NOT IMPLEMENT, LACK OF TIME
            }
        }
        return true;
    }

}
