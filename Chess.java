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
        board.createChessBoard();
        white = new Player("white");
        black = new Player("black");
        board.setCurrentPlayer(white);
        board.updateTurnDisplay(white);
        play(white);
    }

    /**
     * The main loop that executes for playing the game
     * Alternates turns, checks for check/checkmate, gets moves from player
     */
    
     public void play(Player currentTurn) 
     {
        Piece checkKing = null; // the piece that is checking the king
        Spot checkKingPosition = new Spot(); // the position of the piece checking the king
        Spot kingPosition = new Spot(); // the position of the opposing king (the king that's being checked)
        boolean isKingChecked = false; // whether or not the king is checked
        boolean isKingCheckmated = false; // whether or not the king is checkmated
        
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
            
            // if true, end game immediately because there is no shot of the king surviving.
            // if false, allow the player to make a move and call isChecked() immediately after canMove() is called.

            if(isKingChecked == true)
            {
                JOptionPane.showMessageDialog(null, currentTurn.getColor() + ", YOUR KING IS IN CHECK!!!", "WARNING!", JOptionPane.PLAIN_MESSAGE);

                // only if the king is checked do you call isKingCheckmated(). if that returns false, end the game. otherwise, set isKingCheckmated
                // to false again.
                isKingCheckmated = board.isCheckmated(kingPosition, currentTurn, checkKing, checkKingPosition);
            
                if(isKingCheckmated == true) {
                    end();
                } 
            }
            else {       

            System.out.println(userInput);

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

                // if move is valid, call isChecked()
                if(currentTurn.getColor().equals("black"))
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

                    board.setCurrentPlayer(white);
                    board.updateTurnDisplay(white);
                    board.setMove(null);
                    userInput = null;
                    board.updateBoardDisplay();
                    play(white);
            
                } 
                else if(currentTurn.getColor().equals("white"))
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

                    board.setCurrentPlayer(black);
                    board.updateTurnDisplay(black);
                    board.setMove(null);
                    userInput = null;
                    board.updateBoardDisplay();
                    play(black);
                }
            }
        }
    }

    /**
     * Ends the game 
     */  
    public void end()
    {
        System.exit(0);
    }
}  