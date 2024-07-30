import board.*;
import piece.Player;

import java.util.Scanner;
public class Chess {
    protected Board board = new Board();
    Player white;
    Player black;
    String currentTurn;
    Scanner scnr = new Scanner(System.in);
    String userInput;
    
    public void main(String[] args) {
        start();  

    }

    /**
     * Initializes the game attributes
     */
    public void start() 
    {
        DisplayBoard gameBoard = new DisplayBoard();
        gameBoard.createChessBoard();
        white = new Player("white");
        black = new Player("black");
        currentTurn = "black";

        board.display();
        //System.out.println("White player moves first");
        //System.out.print("Enter move formatted as \"[FROM] [TO]\" EX: \"E2 E4\": ");
        //userInput = scnr.nextLine();

        //System.out.println();
        //board.movePiece(userInput); 

        play(white);

    }

    /**
     * The main loop that executes for playing the game
     * Alternates turns, checks for check/checkmate, gets moves from player
     */
    
     public void play(Player currentTurn) {
        // currently outputs an infinite loop !!!
            System.out.println("Enter move formatted as "[FROM] [TO]" EX: "E2 E4": ");
            System.out.print(currentTurn.getColor() + " player enter move: ");
            userInput = scnr.nextLine();
            board.movePiece(userInput);
            if(currentTurn.getColor() == "black")
            {
                play(white);
            }
            else if(currentTurn.getColor() == "white")
            {
                play(black);
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

}  