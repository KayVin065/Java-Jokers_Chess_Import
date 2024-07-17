

import board.*;
import java.util.Scanner;
public class Chess {
    protected Board board;
    protected Player white;
    protected Player black;
    protected String currentTurn;
    public void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        start();  

    }

    /**
     * Initializes the game attributes
     */
    public void start() {
        board = new Board();
        white = new Player("white");
        black = new Player("black");
        currentTurn = "white";

        board.display();
        System.out.println("Enter move formatted as \"[FROM] [TO]\" EX: \"E2 E4\": ");
        //play();

    }

    /**
     * The main loop that executes for playing the game
     * Alternates turns, checks for check/checkmate, gets moves from player
     */
    /*
    public void play() {
        while(!end()) {

        }
        currentTurn = setCurrentTurn(currentTurn); // toggles the turn 
    }
    */

    /**
     * Ends the game and determines a winner or a draw
     * 
     * calls isCheckmate & returns true if isCheckmate returns true - determines winner
     * calls isCheck & returns true if isCheck returns true - determines draw if both are checked (eh)
     */
    
    /*
    public bool end() {
        return true;
    }
    */


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

}  