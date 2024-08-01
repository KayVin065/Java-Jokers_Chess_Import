/**
 * Displays the actual componets of the chess board
 * NOTE:does not communicate with rest of project yet
 */
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import piece.Piece;
import piece.Player;

public class DisplayBoard {
    //defines the dimensions of the chess board
    private  String userInput = null;
    private String originalPieceText;
    private final int Rows = 8;
    private final int Columns = 8;
    private final JPanel[][] chessBoardpieces = new BoardPanel[Rows][Columns];
    private boolean moveValid = true;

    //Variables to monitor the piece and piece selected
    private JLabel selectedPieceLabel = null;
    private BoardPanel selectedpiece = null;
    private int originalRow, originalColumn;

    //unicode for chess pieces
    private static final String[] UNICODE_PIECES = 
    {
        "\u2654","\u2655","\u2656","\u2657","\u2658","\u2659",
        "\u265A","\u265B","\u265C","\u265D","\u265E","\u265F" 
    };
     
    public void createChessBoard() 
    {
        //creates the empty board
        JFrame frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(Rows, Columns));
        frame.setSize(600, 600);

        //create each piece & colors the tile
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                //creates jPanel for each tile initialized
                BoardPanel panel = new BoardPanel(i, j);
                
                //colors each tile
                panel.setBackground((i + j) % 2 == 0 ? Color.darkGray : Color.WHITE);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                //Creates another JLabel on top that displays chess piece
                JLabel piece = new JLabel(getPieceUnicode(i, j));
                piece.setFont(new Font("Serif", Font.BOLD, 32));
                piece.setHorizontalAlignment(JLabel.CENTER);
                piece.setVerticalAlignment(JLabel.CENTER);
                panel.add(piece, BorderLayout.CENTER);

                //copied from example to handle mouse clicks
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) 
                    {
                        handleMouseClick(panel);
                    }
                });

                chessBoardpieces[i][j] = panel;
                frame.add(panel);
            }
        }
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Handles Mouse input and switches location of piece
     * if mouse selects a new location
     * @param clickedPanel
     */
    private void handleMouseClick(BoardPanel clickedPanel) 
    {
        JLabel clickedLabel = getLabelFromPanel(clickedPanel);
        
        if (selectedPieceLabel == null) //no piece selected
        {
            //if a piece is selected without an end location
            if (clickedLabel != null && !clickedLabel.getText().isEmpty()) 
            {
                selectedPieceLabel = clickedLabel;
                selectedpiece = clickedPanel;
                originalRow = selectedpiece.getRow();
                originalColumn = selectedpiece.getColumn();
                originalPieceText = selectedPieceLabel.getText();
                selectedpiece.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }   
        else 
        {
            // Move the piece if it's different from beginnning location
            if (clickedPanel != selectedpiece) 
            {

                JLabel targetLabel = getLabelFromPanel(clickedPanel);
                setMove(selectedpiece, clickedPanel);
                
                if (targetLabel != null) 
                {
                    // Move the piece to the target place
                    targetLabel.setText(selectedPieceLabel.getText());
                    selectedPieceLabel.setText(""); // clears initial spot
                    setDisplayMoveValid(false);
                }
                else 
                {
                    // Display error message if the move is invalid
                    originalRow = selectedpiece.getRow();
                    originalColumn = selectedpiece.getColumn();
                    originalPieceText = selectedPieceLabel.getText();
                    resetPiecePosition();
                } 
                selectedpiece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                selectedPieceLabel = null;
                selectedpiece = null;
                
            }

            // allows you to deselect a piece if you click the same piece again
            if(clickedPanel == selectedpiece) {
                selectedpiece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                selectedPieceLabel = null;
                selectedpiece = null;
            }
        }
    }

    private void resetPiecePosition() {
        JLabel originalLabel = getLabelFromPanel(chessBoardpieces[originalRow][originalColumn]);
        selectedPieceLabel.setText("");
        originalLabel.setText(originalPieceText);
    }

    public void setMove(String input)
    {
        userInput = input;
    }

    public void setMove(BoardPanel selectedpiece, BoardPanel clickedPanel)
    {
        userInput = String.valueOf(selectedpiece.getColumn());
        userInput += String.valueOf(selectedpiece.getRow());
        userInput += " ";
        userInput += String.valueOf(clickedPanel.getColumn());
        userInput += String.valueOf(clickedPanel.getRow());

    }

    public void setDisplayMoveValid(boolean statement)
    {
        moveValid = statement;
    }

    public boolean getDisplayMoveValid()
    {
        return moveValid;
    }

    public String getMove()
    {
        return userInput;
    }
    
    /**
     * returns label from JPanel
     * @param panel
     * @return label
     */
    private JLabel getLabelFromPanel(JPanel panel) {
        return (JLabel) panel.getComponent(0);
    }

    /**
     * used to provide location and unicode type for each piece
     * @param row
     * @param column
     * @return
     */
    private static String getPieceUnicode(int row, int column) {
        if (row == 0 || row == 7) {
            int offset = (row == 7) ? 0 : 6; // Determines white or black pieces
            switch (column) {
                case 0, 7 -> {
                    return UNICODE_PIECES[2 + offset]; // Rook
                }
                case 1, 6 -> {
                    return UNICODE_PIECES[4 + offset]; // Knight
                }
                case 2, 5 -> {
                    return UNICODE_PIECES[3 + offset]; // Bishop
                }
                case 3 -> {
                    return (row == 7) ? UNICODE_PIECES[1] : UNICODE_PIECES[7]; // Queen
                }
                case 4 -> {
                    return (row == 7) ? UNICODE_PIECES[0] : UNICODE_PIECES[6]; // King
                }
            }
        } else if (row == 1 || row == 6) {
            return (row == 6) ? UNICODE_PIECES[5] : UNICODE_PIECES[11]; // Pawns
        }
        return ""; // Empty space for non-piece areas
    }
}