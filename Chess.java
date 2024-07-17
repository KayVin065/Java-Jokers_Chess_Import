import board.*;
import java.util.Scanner;
public class Chess {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        start();
        Player orb = new Player("orb");

        String temp = scnr.nextLine();
        String temp2 = orb.getUserInput(temp);
        System.out.println(temp2);

        /*System.out.println("Checking possible move func");
        System.out.println(board[7][2].getPiece());
        System.out.println("Checking possible move func");*/


    }

    /**
     * Initializes the game attributes
     */
    public static void start() {
        Board board = new Board();
        Player white = new Player("white");
        Player black = new Player("black");
        String currentTurn = "white";

        board.display();
        System.out.println("Enter move formatted as \"[FROM] [TO]\" EX: \"E2 E4\": ");

       


    }

    /**
     * Ends the game and determines a winner or a draw
     */
    public static void end() {

    }

}   
