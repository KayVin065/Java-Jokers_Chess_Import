import board.*;
import javax.swing.JOptionPane;

import piece.Piece;
import piece.Player;

public class Chess {
    public static Board board = new Board();
    Player white;
    Player black;
    String userInput = null;


    public void main(String[] args) 
    {
    start();  
    }
/* */
    /**
     * Initializes the game attributes
     */
    public void start() 
    {
        board.createChessBoard();
        white = new Player("white");
        black = new Player("black");
        board.setCurrentPlayer(white);
        play(white);
    }

    /**
     * The main loop that executes for playing the game
     * Alternates turns, checks for check/checkmate, gets moves from player
     */
    
     public void play(Player currentTurn) 
     {
        // currently outputs an infinite loop !!!
        // checkKing = holds piece that is checking the king
        Piece checkKing = null;
        // checkKingPosition = holds the Spot holding the piece checking the king
        Spot checkKingPosition = new Spot();
        // kingPosition = position of the king of the other team [SPOT]
        Spot kingPosition = new Spot();
        // isKingChecked = whether or not the king is currently checked [BOOLEAN]
        boolean isKingChecked = false;
        // isKingCheckmated = whether or not the king is currently checkmated [BOOLEAN].
        boolean isKingCheckmated = false;
        // if true, end game immediately because there is no shot of the king surviving.
        // if false, allow the player to make a move and call isChecked() immediately after canMove() is called.

            while (userInput == null) 
            {
                userInput = board.getMove(); 
                try {
                    Thread.sleep(1000); // Avoid busy-waiting
                } catch (InterruptedException e) 
                {
                   e.printStackTrace();
                }
            }

            System.out.println(userInput);

            if("forfeit".equals(userInput))
            {
                if("white".equals(currentTurn.getColor()))
                {
                    JOptionPane.showMessageDialog(null, "Black wins!", "Congratulation!", JOptionPane.PLAIN_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "White wins!", "Congratulation!", JOptionPane.PLAIN_MESSAGE);
                }
                end();
            }

            if(isKingChecked == true)
            {
                JOptionPane.showMessageDialog(null, currentTurn.getColor() + ", YOUR KING IS IN CHECK!!!", "WARNING!", JOptionPane.PLAIN_MESSAGE);

                // only if the king is checked do you call isKingCheckmated(). if that returns false, end the game. otherwise, set isKingCheckmated
                // to false again.
                isKingCheckmated = board.isCheckmated(kingPosition, currentTurn, checkKing, checkKingPosition);
            
                if(isKingCheckmated == true) {
                    end();
                } 
                else {
                     // checks for incorrect input like normal
                     if(board.canMove(userInput, currentTurn) == false)
                     {
                        System.out.println("\nERROR incorrect move");
                        board.setMove(null);
                        userInput = null;
                        board.setCurrentPlayer(currentTurn);
                        board.updateBoardDisplay();
        
                        play(currentTurn);
                     } 
                     else {
                        board.movePiece(userInput, currentTurn);
                     }
                     
                     // if move is valid, call isChecked()
                     if(currentTurn.getColor() == "black")
                     {
                         kingPosition = board.getTeamKingPosition(currentTurn);
                         isKingChecked = board.isChecked(kingPosition, currentTurn, checkKing, checkKingPosition);
                         // may have conflicts if prior piece checking king no longer checks, but another piece does
                         // if checkKing != null then there's a current piece checking the king; isKingChecked is still true, last effort failed and end game.
                         if(isKingChecked == true) {
                             end();
                         }
                         else { // isKingChecked == false
                             // if the king is no longer in danger, reset checkKing and play like normal
                             checkKing = null;
                             isKingChecked = false;
                         }
                         play(white);
         
                     }
                }
            }
            else {        
            System.out.println(userInput);
            
            if("black".equals(currentTurn.getColor()))
            {
                kingPosition = board.getOtherTeamKingPosition(currentTurn);
                isKingChecked = board.isChecked(kingPosition, currentTurn, checkKing, checkKingPosition);
                board.setCurrentPlayer(white);
                board.setMove(null);
                userInput = null;
                board.updateBoardDisplay();
                play(white);
            }
            else if("white".equals(currentTurn.getColor()))
            {
                kingPosition = board.getOtherTeamKingPosition(currentTurn);
                isKingChecked = board.isChecked(kingPosition, currentTurn, checkKing, checkKingPosition);
                board.setCurrentPlayer(black);
                board.setMove(null);
                userInput = null;
                board.updateBoardDisplay();
                play(black);
            }
        }
    }

    /**
     * Ends the game and determines a winner or a draw
     * 
     * calls isCheckmate & returns true if isCheckmate returns true - determines winner
     * calls isCheck & returns true if isCheck returns true - determines draw if both are checked (eh)
     */  
    public void end()
    {
        System.exit(0);
    }
}  