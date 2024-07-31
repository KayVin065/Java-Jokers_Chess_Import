
/**
 * Represents a game board for a chess game
 */
package board;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import piece.*;

public class Board {
    public Spot[][] board = new Spot[8][8];
    private Player currentTurn = null;

    //
    private String userInput = null;
    private String originalPieceText;
    private final int Rows = 8;
    private final int Columns = 8;
    private final JPanel[][] chessBoardPieces = new BoardPanel[Rows][Columns];
    private boolean moveValid;
    // Variables to monitor the piece and piece selected
    private JLabel selectedPieceLabel = null;
    private BoardPanel selectedPiece = null;
    private int originalRow, originalColumn;
    private final JFrame frame = new JFrame("Chess Board");
    //
    public void setCurrentPlayer(Player currentTurn)
    {
        this.currentTurn = currentTurn;
    }

    public Player getCurrentTurn()
    {
        return currentTurn;
    }

    /**
     * Initializes an 8x8 board with pieces in original positions
     */
    public Board() {
        int spotNum = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(spotNum % 2 == 0) {
                   // board[i][j] = new Spot(i, j, " ##", null);
                } else {
                    //board[i][j] = new Spot(i, j, "   ", null);
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
/*
 * the idea is that the gui implentation mirrors the base code (original)
 */
    /**
     * Method to create new Piece objects for the first and last 2 rows of the board.
     * Completely resets the placement of the pieces
     */
    public final void setOriginalPieces() {
        for(int i = 0; i < 8; i++) {
            board[1][i].piece = new Pawn("black", "\u265F");
            board[6][i].piece = new Pawn("white", "\u2659");
        }
        board[0][0].piece = new Rook("black", "\u265C");
        board[0][1].piece = new Knight("black", "\u265E");
        board[0][2].piece = new Bishop("black", "\u265D");
        board[0][3].piece = new Queen("black", "\u265B");
        board[0][4].piece = new King("black", "\u265A");
        board[0][5].piece = new Bishop("black", "\u265D");
        board[0][6].piece = new Knight("black", "\u265E");
        board[0][7].piece = new Rook("black", "\u265C");

        board[7][0].piece = new Rook("white", "\u2656");
        board[7][1].piece = new Knight("white", "\u2658");
        board[7][2].piece = new Bishop("white", "\u2657");
        board[7][3].piece = new Queen("white", "\u2655");
        board[7][4].piece = new King("white", "\u2654");
        board[7][5].piece = new Bishop("white", "\u2657");
        board[7][6].piece = new Knight("white","\u2658" );
        board[7][7].piece = new Rook("white", "\u2656");
    }
/* 
    * \u2654 - White King
    \u2655 - White Queen
    \u2656 - White Rook
    \u2657 - White Bishop
    \u2658 - White Knight
    \u2659 - White Pawn
    \u265A - Black King
    \u265B - Black Queen
    \u265C - Black Rook
    \u265D - Black Bishop
    \u265E - Black Knight
    \u265F - Black Pawn
 */


    public void createChessBoard() {
        // creates the empty board
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 600);

        JPanel boardPanel = new JPanel(new GridLayout(Rows, Columns));
        // create each piece & colors the tile
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                // creates JPanel for each tile initialized
                BoardPanel panel = new BoardPanel(i, j);

                // colors each tile
                panel.setBackground((i + j) % 2 == 0 ? Color.darkGray : Color.WHITE);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                // Creates another JLabel on top that displays chess piece


                setOriginalPieces();
                JLabel piece = new JLabel(board[i][j].getPieceUnicode());
                piece.setFont(new Font("Serif", Font.BOLD, 32));
                piece.setHorizontalAlignment(JLabel.CENTER);
                piece.setVerticalAlignment(JLabel.CENTER);
                panel.add(piece, BorderLayout.CENTER);

                // copied from example to handle mouse clicks
                panel.addMouseListener(new MouseAdapter() 
                {
                    @Override
                    public void mouseClicked(MouseEvent e) 
                    {
                        //handleMouseClick(panel);
                    }
                });

                chessBoardPieces[i][j] = panel;
                boardPanel.add(panel);
            }
        }
        frame.add(boardPanel, BorderLayout.CENTER);

        // Create the forfeit button and add it to the frame
        JButton forfeitButton = new JButton("Forfeit");
        forfeitButton.addActionListener((ActionEvent e) -> {
            //handleForfeitButtonClick();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(forfeitButton);
        frame.add(buttonPanel, BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
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

    public static int translateDisplayMove(char x) {
        switch (x)
        {
            
            case '7' -> {
                return 1;
            }
            case '6' -> {
                return 2;
            }
            case '5' -> {
                return 3;
            }
            case '4' -> {
                return 4;
            }
            case '3' -> {
                return 5;
            }
            case '2' -> {
                return 6;
            }
            case '1' -> {
                return 7;
            }
            case '0' -> {
                return 8;
            }
            default -> throw new AssertionError();
        }
    }

    /**
     * Takes in two Strings that represent the user input coordinates
     * @param input String representing the "to" and "from" coordinates as one line
     */
    public void movePiece(String input, Player player)
    {
        int fromY = Character.getNumericValue(input.charAt(0));//a
        int fromX = Character.getNumericValue(input.charAt(1)); //translateMove(input.charAt(1));
        int toY = Character.getNumericValue(input.charAt(3));
        int toX = Character.getNumericValue(input.charAt(4));//translateMove(input.charAt(4));
        Piece temp = board[fromX][fromY].getPiece();

        board[fromX][fromY].piece = null;
        board[toX][toY].piece = temp;

        System.out.println();
        display();
        
    }

    public boolean canMove(String input, Player player) {
        int fromPosy = Character.getNumericValue(input.charAt(0));//a
        int fromPosx = Character.getNumericValue(input.charAt(1)); //translateMove(input.charAt(1));
        int toPosy = Character.getNumericValue(input.charAt(3));
        int toPosx = Character.getNumericValue(input.charAt(4));//translateMove(input.charAt(4));

        Piece temp = board[fromPosx][fromPosy].getPiece();
        Piece toTemp = board[toPosx][toPosy].getPiece();

        if(temp.getColor() == null ? player.getColor() != null : !temp.getColor().equals(player.getColor()))
        {
            JOptionPane.showMessageDialog(null, "Error! you cannot your opponets piece!",
             "Error", JOptionPane.ERROR_MESSAGE);
             return false;
        }
        if(toTemp != null && temp.getColor().equals(toTemp.getColor()))
        {
            System.out.println("Error! you cannot take your own piece!");
            JOptionPane.showMessageDialog(null, "Error! you cannot take your own piece!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(temp.validMove(board, board[fromPosx][fromPosy], board[toPosx][toPosy], player))
        {
            return true;
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Error! wrong piece movement!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean isKingChecked(Player currentTurn) {
        int kingX = -1, kingY = -1;
        
        // Find the king's position for the current player
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j].getPiece();
                if (piece != null && piece instanceof King && piece.getColor().equals(currentTurn.getColor())) {
                    kingX = i;
                    kingY = j;
                    break;
                }
            }
        }
    
        if (kingX == -1 || kingY == -1) {
            JOptionPane.showMessageDialog(null, currentTurn.getColor() + ",You lose!!!", "booo!", JOptionPane.PLAIN_MESSAGE);
            System.exit(1);
        }
    
        // Check if any opponent piece can move to the king's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j].getPiece();
                if (piece != null && !piece.getColor().equals(currentTurn.getColor())) {//error could e here
                    if (piece.validMove(board, board[i][j], board[kingX][kingY], currentTurn)) {
                        return true;
                    }
                }
            }
        }
    
        return false;
    }
    
}