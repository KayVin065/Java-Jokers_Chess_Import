
/**
 * Represents a game board for a chess game
 */
package board;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import piece.*;

public class Board {
    public Spot[][] board = new Spot[8][8];
    private Player currentTurn = null;
    private String userInput = null;

    //
    //private String userInput = null;
    //private String originalPieceText;
    private final int Rows = 8;
    private final int Columns = 8;
    private final JPanel[][] chessBoardPieces = new BoardPanel[Rows][Columns];
    // Variables to monitor the piece and piece selected
    private JLabel selectedPieceLabel = null;
    private BoardPanel selectedPiece = null;
    private final JFrame frame = new JFrame("Chess Board");
    //
    public void setCurrentPlayer(Player current)
    {
        this.currentTurn = current;
    }

    public Player getCurrentTurn()
    {
        return currentTurn;
    }

    private JLabel getLabelFromPanel(JPanel panel) 
    {
        return (JLabel) panel.getComponent(0);
    }

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
                panel.setBackground((i + j) % 2 == 0 ? Color.CYAN : Color.WHITE);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                // Creates another JLabel on top that displays chess piece

                JLabel piece = new JLabel("");
                setOriginalPieces();
                if(board[i][j].getPiece() != null)
                {
                    piece = new JLabel(board[i][j].getPiece().getUnicode()); //getUnicode is a function in piece.java

                }
                
                piece.setFont(new Font("Serif", Font.BOLD, 50));
                piece.setHorizontalAlignment(JLabel.CENTER);
                piece.setVerticalAlignment(JLabel.CENTER);
                panel.add(piece, BorderLayout.CENTER);

                // copied from example to handle mouse clicks
                panel.addMouseListener(new MouseAdapter() 
                {
                    @Override
                    public void mouseClicked(MouseEvent e) 
                    {
                        handleMouseClick(panel);
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
            handleForfeitButtonClick();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(forfeitButton);
        frame.add(buttonPanel, BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
    }

    //handles forfeit button
    private void handleForfeitButtonClick() 
   {
      int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to forfeit the game?"
    , "Forfeit Game", JOptionPane.YES_NO_OPTION);
    if (response == JOptionPane.YES_OPTION) 
     {
        setMove("forfeit");
        frame.dispose();
    }
    }

     /**
     * Handles Mouse input and switches location of piece if mouse selects a new location
     * @param clickedPanel
     */
    private void handleMouseClick(BoardPanel clickedPanel) {
        JLabel clickedLabel = getLabelFromPanel(clickedPanel);
    
        if (selectedPieceLabel == null) { // no piece selected
            if (clickedLabel != null && !clickedLabel.getText().isEmpty()) {
                selectedPieceLabel = clickedLabel;
                selectedPiece = clickedPanel;

                if (selectedPiece != null) { // Ensure selectedPiece is not null
                    selectedPiece.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        } else {
            if (clickedPanel != selectedPiece) {

                JLabel targetLabel = getLabelFromPanel(clickedPanel);
                setMove(selectedPiece, clickedPanel);
                if (targetLabel != null ) {
                    targetLabel.setText(selectedPieceLabel.getText());
                    selectedPieceLabel.setText(""); // Clears initial spot
                }
                if (selectedPiece != null) { // Ensure selectedPiece is not null
                    selectedPiece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                selectedPieceLabel = null;
                selectedPiece = null;
            }
        }
    }

    public void setMove(String move)
    {
        userInput = move;
    }

    public void setMove(BoardPanel selectedPiece, BoardPanel clickedPanel) 
    {
        userInput = String.valueOf(selectedPiece.getColumn());
        userInput += String.valueOf(selectedPiece.getRow());
        userInput += " ";
        userInput += String.valueOf(clickedPanel.getColumn());
        userInput += String.valueOf(clickedPanel.getRow());
        setMove(userInput);
    }

    public String getMove()
    {
        return userInput;
    }
    
    public void updateBoardDisplay() {
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                JLabel pieceLabel = getLabelFromPanel(chessBoardPieces[i][j]);
                Piece piece = board[i][j].getPiece();
                if (piece != null) {
                    pieceLabel.setText(piece.getUnicode());
                } else {
                    pieceLabel.setText("");
                }
            }
        }
    }

    /**
     * Executes the move on the board
     * @param fromX int representing the "from" x-coordinate
     * @param fromY int representing the "from" y-coordinate
     * @param toX int representing the "to" x-coordinate
     * @param toY int representing the "to" y-coordinate
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
        
        //display();
        
    }

    /**
     * Takes in user input and determines if that move is valid
     * If the move is valid, call movePiece() to move the piece on the board
     * @param input String representing the "to" and "from" coordinates as one line
     * @param player the Player object of the current player
     * @return true if the move is valid, false if the move is not valid
     */
    public boolean canMove(String input, Player player) {
        int fromPosy = Character.getNumericValue(input.charAt(0));//a
        int fromPosx = Character.getNumericValue(input.charAt(1)); //translateMove(input.charAt(1));
        int toPosy = Character.getNumericValue(input.charAt(3));
        int toPosx = Character.getNumericValue(input.charAt(4));//translateMove(input.charAt(4));

        Piece temp = board[fromPosx][fromPosy].getPiece();
        Piece toTemp = board[toPosx][toPosy].getPiece();

        if(temp.getColor() == null ? player.getColor() != null : !temp.getColor().equals(player.getColor()))
        {
            JOptionPane.showMessageDialog(null, "Error! you cannot move your opponets piece!",
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