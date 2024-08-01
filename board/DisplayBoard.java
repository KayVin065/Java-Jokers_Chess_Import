// /*
// package board;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import javax.swing.*;
// public class DisplayBoard {
//     // defines the dimensions of the chess board
//     private String userInput = null;
//     private String originalPieceText;
//     private final int Rows = 8;
//     private final int Columns = 8;
//     private final JPanel[][] chessBoardPieces = new BoardPanel[Rows][Columns];
//     private boolean moveValid;
//     // Variables to monitor the piece and piece selected
//     private JLabel selectedPieceLabel = null;
//     private BoardPanel selectedPiece = null;
//     private int originalRow, originalColumn;
//     private final JFrame frame = new JFrame("Chess Board");

//     // unicode for chess pieces
//     private static final String[] UNICODE_PIECES = {
//         "\u2654","\u2655","\u2656","\u2657","\u2658","\u2659",
//         "\u265A","\u265B","\u265C","\u265D","\u265E","\u265F"
//     };
//     /*
//      * 
//      * \u2654 - White King
//         \u2655 - White Queen
//         \u2656 - White Rook
//         \u2657 - White Bishop
//         \u2658 - White Knight
//         \u2659 - White Pawn
//         \u265A - Black King
//         \u265B - Black Queen
//         \u265C - Black Rook
//         \u265D - Black Bishop
//         \u265E - Black Knight
//         \u265F - Black Pawn
//      */

//     public void createChessBoard() {
//         // creates the empty board
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(new BorderLayout());
//         frame.setSize(600, 600);

//         JPanel boardPanel = new JPanel(new GridLayout(Rows, Columns));
//         // create each piece & colors the tile
//         for (int i = 0; i < Rows; i++) {
//             for (int j = 0; j < Columns; j++) {
//                 // creates JPanel for each tile initialized
//                 BoardPanel panel = new BoardPanel(i, j);

//                 // colors each tile
//                 panel.setBackground((i + j) % 2 == 0 ? Color.darkGray : Color.WHITE);
//                 panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//                 // Creates another JLabel on top that displays chess piece



//                 JLabel piece = new JLabel(getPieceUnicode(i, j));
//                 piece.setFont(new Font("Serif", Font.BOLD, 32));
//                 piece.setHorizontalAlignment(JLabel.CENTER);
//                 piece.setVerticalAlignment(JLabel.CENTER);
//                 panel.add(piece, BorderLayout.CENTER);

//                 // copied from example to handle mouse clicks
//                 panel.addMouseListener(new MouseAdapter() 
//                 {
//                     @Override
//                     public void mouseClicked(MouseEvent e) 
//                     {
//                         handleMouseClick(panel);
//                     }
//                 });

//                 chessBoardPieces[i][j] = panel;
//                 boardPanel.add(panel);
//             }
//         }
//         frame.add(boardPanel, BorderLayout.CENTER);

//         // Create the forfeit button and add it to the frame
//         JButton forfeitButton = new JButton("Forfeit");
//         forfeitButton.addActionListener((ActionEvent e) -> {
//             handleForfeitButtonClick();
//         });
//         JPanel buttonPanel = new JPanel();
//         buttonPanel.add(forfeitButton);
//         frame.add(buttonPanel, BorderLayout.EAST);

//         frame.pack();
//         frame.setVisible(true);
//     }

//     /**
//      * Handles the forfeit button click
//      */
//     private void handleForfeitButtonClick() 
//     {
//         int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to forfeit the game?"
//         , "Forfeit Game", JOptionPane.YES_NO_OPTION);
//         if (response == JOptionPane.YES_OPTION) 
//         {
//             userInput = "forfeit";
//             frame.dispose();
//         }
//     }

//     /**
//      * Handles Mouse input and switches location of piece if mouse selects a new location
//      * @param clickedPanel
//      */
//     private void handleMouseClick(BoardPanel clickedPanel) {
//         JLabel clickedLabel = getLabelFromPanel(clickedPanel);
    
//         if (selectedPieceLabel == null) { // no piece selected
//             if (clickedLabel != null && !clickedLabel.getText().isEmpty()) {
//                 selectedPieceLabel = clickedLabel;
//                 selectedPiece = clickedPanel;
//                 originalRow = selectedPiece.getRow();
//                 originalColumn = selectedPiece.getColumn();
//                 originalPieceText = selectedPieceLabel.getText();
//                 if (selectedPiece != null) { // Ensure selectedPiece is not null
//                     selectedPiece.setBorder(BorderFactory.createLineBorder(Color.RED));
//                 }
//             }
//         } else {
//             if (clickedPanel != selectedPiece) {

//                 JLabel targetLabel = getLabelFromPanel(clickedPanel);
//                 setMove(selectedPiece, clickedPanel);
//                 /* 
//                  try 
//                 {
//                     waitForMoveValidation(); // Handle the InterruptedException
//                 } 
//                 catch (InterruptedException e) 
//                 {
//                     e.printStackTrace(); // Handle the exception as needed
//                     Thread.currentThread().interrupt(); // Restore the interrupted status
    
//                     // exit the current operation
//                     return; 
//                 }
//                 */
//                 if (targetLabel != null ) {
//                     targetLabel.setText(selectedPieceLabel.getText());
//                     selectedPieceLabel.setText(""); // Clears initial spot
//                     setDisplayMoveValid(false);
//                 }
//                 if (!getDisplayMoveValid()) {
//                     resetPiecePosition();
//                 }
//                 if (selectedPiece != null) { // Ensure selectedPiece is not null
//                     selectedPiece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//                 }
//                 selectedPieceLabel = null;
//                 selectedPiece = null;
//             }
//         }
//     }

//     private void resetPiecePosition() {
//         JLabel originalLabel = getLabelFromPanel(chessBoardPieces[originalRow][originalColumn]);
//         originalLabel.setText(originalPieceText);
//         selectedPieceLabel.setText("");
//         selectedPiece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//         selectedPieceLabel = null;
//         selectedPiece = null;
//     }

//     public void setMove(String input) {
//         userInput = input;
//     }

//     public void setMove(BoardPanel selectedPiece, BoardPanel clickedPanel) {
//         userInput = String.valueOf(selectedPiece.getColumn());
//         userInput += String.valueOf(selectedPiece.getRow());
//         userInput += " ";
//         userInput += String.valueOf(clickedPanel.getColumn());
//         userInput += String.valueOf(clickedPanel.getRow());
//     }

//     public void waitForMoveValidation() throws InterruptedException 
//     {
//         synchronized (this) 
//         {
//             while (!moveValid) 
//             {
//                 wait(); // Wait until another thread notifies us
//             }
//         }
//     }
//     public synchronized  void setDisplayMoveValid(boolean statement) {
//         moveValid = statement;
//         notify();
//     }

//     public boolean getDisplayMoveValid() {
//         return moveValid;
//     }

//     public String getMove() {
//         return userInput;
//     }

//     /**
//      * returns label from JPanel
//      * @param panel
//      * @return label
//      */
//     private JLabel getLabelFromPanel(JPanel panel) {
//         return (JLabel) panel.getComponent(0);
//     }

//     // I copied this directly from example... it works so idc
//     /**
//      * used to provide location and unicode type for each piece
//      * @param row
//      * @param column
//      * @return
//      */
//     private static String getPieceUnicode(int row, int column) {
//         if (row == 0 || row == 7) {
//             int offset = (row == 7) ? 0 : 6; // Determines white or black pieces
//             switch (column) {
//                 case 0, 7 -> {
//                     return UNICODE_PIECES[2 + offset]; // Rook
//                 }
//                 case 1, 6 -> {
//                     return UNICODE_PIECES[4 + offset]; // Knight
//                 }
//                 case 2, 5 -> {
//                     return UNICODE_PIECES[3 + offset]; // Bishop
//                 }
//                 case 3 -> {
//                     return (row == 7) ? UNICODE_PIECES[1] : UNICODE_PIECES[7]; // Queen
//                 }
//                 case 4 -> {
//                     return (row == 7) ? UNICODE_PIECES[0] : UNICODE_PIECES[6]; // King
//                 }
//             }
//         } else if (row == 1 || row == 6) {
//             return (row == 6) ? UNICODE_PIECES[5] : UNICODE_PIECES[11]; // Pawns
//         }
//         return ""; // Empty space for non-piece areas
//     }

//     private class BoardPanel extends JPanel {
//         private final int row, column;

//         public BoardPanel(int row, int column) {
//             this.row = row;
//             this.column = column;
//             setLayout(new BorderLayout());
//         }

//         public int getRow() {
//             return row;
//         }

//         public int getColumn() {
//             return column;
//         }
//     }
// }
// */
// package board;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import javax.swing.*;
// public class DisplayBoard {
//     // defines the dimensions of the chess board
//     private String userInput = null;
//     private String originalPieceText;
//     private final int Rows = 8;
//     private final int Columns = 8;
//     private final JPanel[][] chessBoardPieces = new BoardPanel[Rows][Columns];
//     private boolean moveValid;
//     // Variables to monitor the piece and piece selected
//     private JLabel selectedPieceLabel = null;
//     private BoardPanel selectedPiece = null;
//     private int originalRow, originalColumn;
//     private final JFrame frame = new JFrame("Chess Board");

//     // unicode for chess pieces
//     private static final String[] UNICODE_PIECES = {
//         "\u2654","\u2655","\u2656","\u2657","\u2658","\u2659",
//         "\u265A","\u265B","\u265C","\u265D","\u265E","\u265F"
//     };
//     /*
//      * 
//      * \u2654 - White King
//         \u2655 - White Queen
//         \u2656 - White Rook
//         \u2657 - White Bishop
//         \u2658 - White Knight
//         \u2659 - White Pawn
//         \u265A - Black King
//         \u265B - Black Queen
//         \u265C - Black Rook
//         \u265D - Black Bishop
//         \u265E - Black Knight
//         \u265F - Black Pawn
//      */

//     public void createChessBoard() {
//         // creates the empty board
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(new BorderLayout());
//         frame.setSize(600, 600);

//         JPanel boardPanel = new JPanel(new GridLayout(Rows, Columns));
//         // create each piece & colors the tile
//         for (int i = 0; i < Rows; i++) {
//             for (int j = 0; j < Columns; j++) {
//                 // creates JPanel for each tile initialized
//                 BoardPanel panel = new BoardPanel(i, j);

//                 // colors each tile
//                 panel.setBackground((i + j) % 2 == 0 ? Color.darkGray : Color.WHITE);
//                 panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//                 // Creates another JLabel on top that displays chess piece



//                 JLabel piece = new JLabel(getPieceUnicode(i, j));
//                 piece.setFont(new Font("Serif", Font.BOLD, 32));
//                 piece.setHorizontalAlignment(JLabel.CENTER);
//                 piece.setVerticalAlignment(JLabel.CENTER);
//                 panel.add(piece, BorderLayout.CENTER);

//                 // copied from example to handle mouse clicks
//                 panel.addMouseListener(new MouseAdapter() 
//                 {
//                     @Override
//                     public void mouseClicked(MouseEvent e) 
//                     {
//                         handleMouseClick(panel);
//                     }
//                 });

//                 chessBoardPieces[i][j] = panel;
//                 boardPanel.add(panel);
//             }
//         }
//         frame.add(boardPanel, BorderLayout.CENTER);

//         // Create the forfeit button and add it to the frame
//         JButton forfeitButton = new JButton("Forfeit");
//         forfeitButton.addActionListener((ActionEvent e) -> {
//             handleForfeitButtonClick();
//         });
//         JPanel buttonPanel = new JPanel();
//         buttonPanel.add(forfeitButton);
//         frame.add(buttonPanel, BorderLayout.EAST);

//         frame.pack();
//         frame.setVisible(true);
//     }

//     /**
//      * Handles the forfeit button click
//      */
//     private void handleForfeitButtonClick() 
//     {
//         int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to forfeit the game?"
//         , "Forfeit Game", JOptionPane.YES_NO_OPTION);
//         if (response == JOptionPane.YES_OPTION) 
//         {
//             userInput = "forfeit";
//             frame.dispose();
//         }
//     }

//     /**
//      * Handles Mouse input and switches location of piece if mouse selects a new location
//      * @param clickedPanel
//      */
//     private void handleMouseClick(BoardPanel clickedPanel) {
//         JLabel clickedLabel = getLabelFromPanel(clickedPanel);
    
//         if (selectedPieceLabel == null) { // no piece selected
//             if (clickedLabel != null && !clickedLabel.getText().isEmpty()) {
//                 selectedPieceLabel = clickedLabel;
//                 selectedPiece = clickedPanel;
//                 originalRow = selectedPiece.getRow();
//                 originalColumn = selectedPiece.getColumn();
//                 originalPieceText = selectedPieceLabel.getText();
//                 if (selectedPiece != null) { // Ensure selectedPiece is not null
//                     selectedPiece.setBorder(BorderFactory.createLineBorder(Color.RED));
//                 }
//             }
//         } else {
//             if (clickedPanel != selectedPiece) {

//                 JLabel targetLabel = getLabelFromPanel(clickedPanel);
//                 setMove(selectedPiece, clickedPanel);
//                 /* 
//                  try 
//                 {
//                     waitForMoveValidation(); // Handle the InterruptedException
//                 } 
//                 catch (InterruptedException e) 
//                 {
//                     e.printStackTrace(); // Handle the exception as needed
//                     Thread.currentThread().interrupt(); // Restore the interrupted status
    
//                     // exit the current operation
//                     return; 
//                 }
//                 */
//                 if (targetLabel != null ) {
//                     targetLabel.setText(selectedPieceLabel.getText());
//                     selectedPieceLabel.setText(""); // Clears initial spot
//                     setDisplayMoveValid(false);
//                 }
//                 if (!getDisplayMoveValid()) {
//                     resetPiecePosition();
//                 }
//                 if (selectedPiece != null) { // Ensure selectedPiece is not null
//                     selectedPiece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//                 }
//                 selectedPieceLabel = null;
//                 selectedPiece = null;
//             }
//         }
//     }

//     private void resetPiecePosition() {
//         JLabel originalLabel = getLabelFromPanel(chessBoardPieces[originalRow][originalColumn]);
//         originalLabel.setText(originalPieceText);
//         selectedPieceLabel.setText("");
//         selectedPiece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//         selectedPieceLabel = null;
//         selectedPiece = null;
//     }

//     public void setMove(String input) {
//         userInput = input;
//     }

//     public void setMove(BoardPanel selectedPiece, BoardPanel clickedPanel) {
//         userInput = String.valueOf(selectedPiece.getColumn());
//         userInput += String.valueOf(selectedPiece.getRow());
//         userInput += " ";
//         userInput += String.valueOf(clickedPanel.getColumn());
//         userInput += String.valueOf(clickedPanel.getRow());
//     }

//     public void waitForMoveValidation() throws InterruptedException 
//     {
//         synchronized (this) 
//         {
//             while (!moveValid) 
//             {
//                 wait(); // Wait until another thread notifies us
//             }
//         }
//     }
//     public synchronized  void setDisplayMoveValid(boolean statement) {
//         moveValid = statement;
//         notify();
//     }

//     public boolean getDisplayMoveValid() {
//         return moveValid;
//     }

//     public String getMove() {
//         return userInput;
//     }

//     /**
//      * returns label from JPanel
//      * @param panel
//      * @return label
//      */
//     private JLabel getLabelFromPanel(JPanel panel) {
//         return (JLabel) panel.getComponent(0);
//     }

//     // I copied this directly from example... it works so idc
//     /**
//      * used to provide location and unicode type for each piece
//      * @param row
//      * @param column
//      * @return
//      */
//     private static String getPieceUnicode(int row, int column) {
//         if (row == 0 || row == 7) {
//             int offset = (row == 7) ? 0 : 6; // Determines white or black pieces
//             switch (column) {
//                 case 0, 7 -> {
//                     return UNICODE_PIECES[2 + offset]; // Rook
//                 }
//                 case 1, 6 -> {
//                     return UNICODE_PIECES[4 + offset]; // Knight
//                 }
//                 case 2, 5 -> {
//                     return UNICODE_PIECES[3 + offset]; // Bishop
//                 }
//                 case 3 -> {
                    // return (row == 7) ? UNICODE_PIECES[1] : UNICODE_PIECES[7]; // Queen
//                 }
    //             case 4 -> {
    //                 return (row == 7) ? UNICODE_PIECES[0] : UNICODE_PIECES[6]; // King
    //             }
    //         }
    //     } else if (row == 1 || row == 6) {
    //         return (row == 6) ? UNICODE_PIECES[5] : UNICODE_PIECES[11]; // Pawns
    //     }
    //     return ""; // Empty space for non-piece areas
    // }

    
// }
