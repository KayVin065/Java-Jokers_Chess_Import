import board.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Chess {
    protected Board board = new Board();
    Player white;
    Player black;
    String currentTurn;
    Scanner scnr = new Scanner(System.in);
    String userInput;

    // GUI stuff
    //defines the dimensions of the chess board
    private static final int Rows = 8;
    private static final int Columns = 8;
    private final JPanel[][] chessBoardpieces = new JPanel[Rows][Columns];
    //Variables to monitor the piece and piece selected
    private JLabel selectedPieceLabel = null;
    private JPanel selectedpiece = null;
    //unicode for chess pieces
    private static final String[] UNICODE_PIECES =
            {
                    "\u2654","\u2655","\u2656","\u2657","\u2658","\u2659",
                    "\u265A","\u265B","\u265C","\u265D","\u265E","\u265F"
            };
    
    public static void main(String[] args) {
        Chess newChessGame = new Chess();
        newChessGame.createChessBoard();
        //newChessGame.start();  
        


    }

    /**
     * Initializes the game attributes
     */
    public void start() {
        white = new Player("white");
        black = new Player("black");
        currentTurn = "black";

        //board.display();
        System.out.println("White player moves first");
        System.out.print("Enter move formatted as \"[FROM] [TO]\" EX: \"E2 E4\": ");
        userInput = scnr.nextLine();

        System.out.println();
        board.movePiece(userInput);

        play();

    }

    /*
        // use try-catch to try calling translateMove(), catch it by looping until an exception is not thrown
     */

    /**
     * The main loop that executes for playing the game
     * Alternates turns, checks for check/checkmate, gets moves from player
     */
    public void play() {
        // currently outputs an infinite loop !!!
        while(!end()) {
            System.out.print(currentTurn + " player enter move: ");
            userInput = scnr.nextLine();
            board.movePiece(userInput);
            currentTurn = setCurrentTurn(currentTurn); // toggles the turn
        }
    }

    /**
     * Ends the game and determines a winner or a draw
     * 
     * calls isCheckmate & returns true if isCheckmate returns true - determines winner
     * calls isCheck & returns true if isCheck returns true - determines draw if both are checked (eh)
     */
    
    public boolean end() {
        return false;
    }


    /**
     * Toggles the current Player to determine whose turn it is
     * @param currentTurn String of the current player whose turn it is
     * @return The opposite color of the currentTurn color
     */
    public String setCurrentTurn(String currentTurn) {
        if(currentTurn.equals("white")) {
            return "black";
        } else {
            return "white";
        }
    }

    /* 

    public boolean isKingChecked(Board board, Spot board[fromPosx][fromPosy], Spot board[toPosx][toPosy]) {
        // is there a piece there? NO? return false
        if(board[toPosx][toPosy].piece == null) {
            return false;
        }
        // is the piece in the desired position on the same team? YES? return false
        if(board[toPosx][toPosy].piece.getColor() == board[fromPosx][fromPosy].piece.getColor()) {
            return false;
        }
        // i
        if(board[fromPosx][fromPosy] instanceof King) {
            return false;
        }

        return true;
    }
        */
    
        public void createChessBoard()
        {
            //creates the empty board
            JFrame frame = new JFrame("Chess Board");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(Rows, Columns));
            frame.setSize(600, 600);
    
            //create each piece
            for (int i = 0; i < Rows; i++) {
                for (int j = 0; j < Columns; j++) {
                    //creates jPanel for each tile initialized
                    JPanel panel = new JPanel(new BorderLayout());
    
                    //colors each tile
                    panel.setBackground((i + j) % 2 == 0 ? Color.darkGray : Color.WHITE);
                    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    //Creates another JLabel on top that displays chess piece
                    //idk how lines 41-43 work but i think they make it bigger and centered
                    //(i just added it from the example)
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
        private void handleMouseClick(JPanel clickedPanel)
        {
            JLabel clickedLabel = getLabelFromPanel(clickedPanel);
    
            if (selectedPieceLabel == null) //no piece selected
            {
                //if a piece is selected without an end location
                if (clickedLabel != null && !clickedLabel.getText().isEmpty())
                {
                    selectedPieceLabel = clickedLabel;
                    selectedpiece = clickedPanel;
                    selectedpiece.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
            else
            {
                // Move the piece if its different from beginnning location
                if (clickedPanel != selectedpiece)
                {
                    JLabel targetLabel = getLabelFromPanel(clickedPanel);
                    //TODO: add error handling here for when pieces conflict!!!
                    if (targetLabel != null)
                    {
                        // Move the piece to the target piece
                        targetLabel.setText(selectedPieceLabel.getText());
                        selectedPieceLabel.setText(""); // clears initial spot
                    }
                    //else{should display error message of some sort}
                    //this is to create a sense of movement (if that makes sense im tired)
                    selectedpiece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    selectedPieceLabel = null;
                    selectedpiece = null;
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