import board.*;
import piece.Piece;
import piece.Player;
import java.util.Scanner;
public class Chess {
    protected Board board = new Board();
    Player white;
    Player black;
    Scanner scnr = new Scanner(System.in);
    String userInput;
    
    public static void main(String[] args) {
        Chess newChess = new Chess();
        newChess.start();  

    }

    /**
     * Initializes the game attributes
     */
    public void start() {
        white = new Player("white");
        black = new Player("black");

        board.display();
        play(white);

    }

    /**
     * The main loop that executes for playing the game
     * Alternates turns, checks for check/checkmate, gets moves from player
     */
    public void play(Player currentTurn) {
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
        
        

            if(isKingChecked == true) {
                // only if the king is checked do you call isKingCheckmated(). if that returns false, end the game. otherwise, set isKingCheckmated
                // to false again.
                isKingCheckmated = board.isCheckmated(kingPosition, currentTurn, checkKing, checkKingPosition);
            
                if(isKingCheckmated == true) {
                    end();
                }
                else {
                    // call canMove() like normal, call isChecked() immediately after to see if the the king is still in danger.
                    // PLAYER may still make the wrong move, which is why isChecked() should compare checkKing to see if it's still checked from 
                    // that AKA nothing has changed.
                    // if isChecked() == true, the king is still in danger, and we continue on like normal.
                    // if isChecked() == false, call the next player

                    System.out.println("Enter move formatted as \"[FROM] [TO]\" EX: \"E2 E4\": ");
                    System.out.print(currentTurn.getColor() + " player enter move: ");
                    userInput = scnr.nextLine();
                    
                    // checks for incorrect input like normal
                    if(board.canMove(userInput, currentTurn) == false)
                    {
                        System.out.println("\nERROR incorrect move");
                        play(currentTurn);
                    }
                    
                    // if move is valid, call isChecked()
                    if(currentTurn.getColor() == "black")
                    {
                        kingPosition = board.getTeamKingPosition(currentTurn);
                        isKingChecked = board.isChecked(kingPosition, currentTurn, checkKing, checkKingPosition);
                        // may have conflicts if prior piece checking king no longer checks, but another piece does
                        // if checkKing != null then there's a current piece checking the king; isKingChecked is still true, last effort failed and end game.
                        if(isKingChecked == true) {
                            System.out.println(currentTurn.getColor() + " lost!");
                            end();
                        }
                        else { // isKingChecked == false
                            // if the king is no longer in danger, reset checkKing and play like normal
                            checkKing = null;
                            isKingChecked = false;
                        }
                        play(white);
        
                    }

                else if(currentTurn.getColor() == "white")
                {
                    kingPosition = board.getTeamKingPosition(currentTurn);
                    isKingChecked = board.isChecked(kingPosition, currentTurn, checkKing, checkKingPosition);
                    // may have conflicts if prior piece checking king no longer checks, but another piece does
                    // if checkKing != null then there's a current piece checking the king; isKingChecked is still true, last effort failed and end game.
                    if(isKingChecked == true) {
                        System.out.println(currentTurn.getColor() + " lost!");
                        end();
                    }
                    else { // isKingChecked == false
                        // if the king is no longer in danger, reset checkKing and play like normal
                        checkKing = null;
                        isKingChecked = false;
                        System.out.println(currentTurn.getColor() + " player's king is no longer checked!");
                    }
                    play(black);
                }
            }
        }
        else {


            System.out.println("Enter move formatted as \"[FROM] [TO]\" EX: \"E2 E4\": ");
            System.out.print(currentTurn.getColor() + " player enter move: ");
            userInput = scnr.nextLine();
            // if move is not valid, print error and play again. if move is valid, should deal with it in the canMove() and go past this loop.
            if(board.canMove(userInput, currentTurn) == false)
            {
                System.out.println("\nERROR incorrect move");
                play(currentTurn);
            }
            
            if(currentTurn.getColor() == "black")
            {
                kingPosition = board.getOtherTeamKingPosition(currentTurn);
                isKingChecked = board.isChecked(kingPosition, currentTurn, checkKing, checkKingPosition);
                if(isKingChecked == true) {
                    System.out.println(currentTurn.getColor() + " player's king has been checked!");
                }
                play(white);
            }
            else if(currentTurn.getColor() == "white")
            {
                kingPosition = board.getOtherTeamKingPosition(currentTurn);
                isKingChecked = board.isChecked(kingPosition, currentTurn, checkKing, checkKingPosition);
                if(isKingChecked == true) {
                    System.out.println(currentTurn.getColor() + " player's king has been checked!");
                }
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
    
    public void end() {
        System.exit(0);
    }
}  