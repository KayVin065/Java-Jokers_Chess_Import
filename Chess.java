import board.*;
import piece.Player;
import java.util.Scanner;
public class Chess {
    protected Board board = new Board();
    Player white;
    Player black;
    Scanner scnr = new Scanner(System.in);
    String userInput;
    
    public void main(String[] args) {
        start();  

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
            System.out.println("Enter move formatted as \"[FROM] [TO]\" EX: \"E2 E4\": ");
            System.out.print(currentTurn.getColor() + " player enter move: ");
            userInput = scnr.nextLine();
            board.movePiece(userInput, currentTurn);
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
}  