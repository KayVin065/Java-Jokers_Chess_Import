/**
 * Displays the actual componets of the chess board
 * NOTE:does not communicate with rest of project yet
 */
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DisplayBoard {
    //defines the dimensions of the chess board
    private static final int Rows = 8;
    private static final int Columns = 8;
    private final JPanel[][] chessBoardSquares = new JPanel[Rows][Columns];
    //Variables to monitor the square and piece selected
    private JLabel selectedPieceLabel = null;
    private JPanel selectedSquare = null;
    //unicode for chess pieces
    private static final String[] UNICODE_PIECES = 
    {
        "\u2654","\u2655","\u2656","\u2657","\u2658","\u2659",
        "\u265A","\u265B","\u265C","\u265D","\u265E","\u265F" 
    };
     
    public void createChessBoard() 
    {
        //creates the general board
        JFrame frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(Rows, Columns));
        frame.setSize(600, 600);

        //create each square
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                //creates jPanel for each square initialized
                JPanel panel = new JPanel(new BorderLayout());
                
                //colors each square
                panel.setBackground((i + j) % 2 == 0 ? Color.darkGray : Color.WHITE);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                //Creates another JLabel on top that displays chess piece
                //idk how lines 41-43 work but i think they make it bigger and centered
                //(i just added it from the example)
                JLabel square = new JLabel(getPieceUnicode(i, j));
                square.setFont(new Font("Serif", Font.BOLD, 32));
                square.setHorizontalAlignment(JLabel.CENTER);
                square.setVerticalAlignment(JLabel.CENTER);
                panel.add(square, BorderLayout.CENTER);

                //copied from example to handle mouse clicks
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) 
                    {
                        handleMouseClick(panel);
                    }
                });

                chessBoardSquares[i][j] = panel;
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
    private void handleMouseClick(JPanel clickedPanel) 
    {
        JLabel clickedLabel = getLabelFromPanel(clickedPanel);

        if (selectedPieceLabel == null) //no piece selected
        {
            //if a piece is selected without an end location
            if (clickedLabel != null && !clickedLabel.getText().isEmpty()) 
            {
                selectedPieceLabel = clickedLabel;
                selectedSquare = clickedPanel;
                selectedSquare.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }   
        else 
        {
            // Move the piece if its different from beginnning location
            if (clickedPanel != selectedSquare) 
            {
                JLabel targetLabel = getLabelFromPanel(clickedPanel);
                //TODO: add error handling here for when pieces conflict!!!
                if (targetLabel != null) 
                {
                    // Move the piece to the target square
                    targetLabel.setText(selectedPieceLabel.getText());
                    selectedPieceLabel.setText(""); // clears initial spot
                }
                //else{should display error message of some sort}
                //this is to create a sense of movement (if that makes sense im tired)
                selectedSquare.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                selectedPieceLabel = null;
                selectedSquare = null;
            }
        }
    }

    /**
     * returns label from JPanel
     * @param panel
     * @return label
     */
    private JLabel getLabelFromPanel(JPanel panel) {
        return (JLabel) panel.getComponent(0);
    }

    //I copied this directly from example... it works so idc
    /**
     * used to provide location and unicode type for each piece
     * @param row
     * @param column
     * @return
     */
    private static String getPieceUnicode(int row, int column) {
        if (row == 0 || row == 7) {
            int offset = (row == 0) ? 0 : 6; // Determines white or black pieces
            switch (column) {
                case 0:
                case 7:
                    return UNICODE_PIECES[2 + offset]; // Rook
                case 1:
                case 6:
                    return UNICODE_PIECES[4 + offset]; // Knight
                case 2:
                case 5:
                    return UNICODE_PIECES[3 + offset]; // Bishop
                case 3:
                    return (row == 0) ? UNICODE_PIECES[1] : UNICODE_PIECES[7]; // Queen
                case 4:
                    return (row == 0) ? UNICODE_PIECES[0] : UNICODE_PIECES[6]; // King
            }
        } else if (row == 1 || row == 6) {
            return (row == 1) ? UNICODE_PIECES[5] : UNICODE_PIECES[11]; // Pawns
        }
        return ""; // Empty space for non-piece areas
    }
}