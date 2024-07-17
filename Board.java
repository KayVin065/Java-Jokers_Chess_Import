public final class Board 
{
    public Piece[][] board = new Piece[8][8];

    public Board()
    {
        SetupBoard();
    }



    //this intializes all spots to null so like theirs something ig
    private void SetupBoard()
    {
        for (Piece[] board1 : board) {
            for (int j = 0; j < board[0].length; j++) {
                board1[j] = null;
            }
        }
     
    
        /*8 BR BN BB BQ BQ BB BK BR [0]
        * 7 BP BP BP BP BP BP BP BP [1]
        * 6    ##    ##    ##    ## [2]
        * 5 ##    ##    ##    ##    [3]
        * 4    ##    ##    ##    ## [4]
        * 3 ##    ##    ##    ##    [5]
        * 2 WP WP WP WP WP WP WP WP [6]
        * 1 WR WK WB WQ WK WB WK WR [7]
        *   A  B  C  D  E  F  G  H
        *  [0][1][2][3][4][5][6][7]
        * REMEMBER first part of the array is 1-8 (also [0] = 1), 2nd part is A-H
        */


        /**
         * @collin
         * this function sets all the pieces 
         * in their place for game start
         */

        //sets rooks
        board[0][0] = new Rook("black");
        board[0][7] = new Rook("black");
        board[7][0] = new Rook("white");
        board[7][7] = new Rook("white");

        //set knights
        board[0][1] = new Knight("black");
        board[0][6] = new Knight ("black");
        board[7][1] = new Knight("white");
        board[7][6] = new Knight("white");

        //Set Bishop
        board[0][2] = new Bishop("black");
        board[0][5] = new Bishop("black");
        board[7][2] = new Bishop("white");
        board[7][5] = new Bishop("white");

        //YES QUEEN!!!
        board[0][3] = new Queen("black");
        board[7][3] = new Queen("white");

        //Set King 
        board[0][4] = new King("black");
        board[7][4] = new King("white");

        //run loop to set pawns
        for(int i = 0; i < board.length; i++)
        {
            board[1][i] = new Pawn("black");
        }
        for(int i = 0; i < board.length; i++)
        {
            board[6][i] = new Pawn("white");
        }  
        System.out.println(MakeString());
    }

    public static int translateMove(char x)
    {
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

    public void movePiece(String Begin, String End)
    {
        int iPosy = translateMove(Begin.charAt(0));
        int iPosx = translateMove(Begin.charAt(1));
        int fPosy = translateMove(End.charAt(0));
        int fPosx = translateMove(End.charAt(1));
        

        Piece swap = board[iPosx][iPosy];
        board[iPosx][iPosy] = null;
        board[fPosx][fPosy] = swap;
        board[fPosx][fPosy].moved = true;
        System.out.println(MakeString());
    }

    

    public String MakeString()
    {
        System.out.println("   A  B  C  D  E  F  G  H ");
        String string = "";
        int ChessNum = 0;
        int numDisplay = 8;
        string += numDisplay + "  ";
        for(Piece[] row: board)
        {
            int ChessCount = 0;
            for(Piece piece : row)
            {
                if(piece == null)
                {
                    if (ChessNum % 2 == 0) 
                    {
                        if(ChessCount % 2 == 0)
                        {
                            string += "##";
                        }
                        else
                        {
                            string += "  ";
                        }
                    }
                    else
                    {
                        if(ChessCount % 2 == 0)
                        {
                            string += "  ";
                        }
                        else
                        {
                            string += "##";
                        }
                    }
                }
                else
                {
                    String test = String.valueOf(piece);
                    String test1 = String.valueOf(piece.color);
                    string += test1.charAt(0);
                    string += test.charAt(0);
                    //this is me testing; 
                }
                string += " ";
                ChessCount ++;
            }
            ChessNum++;
            string += "\n";
            if(numDisplay > 1)
            { 
                numDisplay--;
                string += numDisplay + "  ";
            }

        }
        return string;
    }
}
