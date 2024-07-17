import java.util.Scanner;

public class Jokers 
{
    public static void main(String[] args)
    {
        Board GameBoard = new Board();
        /**
         * TODO: get user input()-aj
         * TODO: translate user input in location variables
         * TODO: Set move set for the pieces()
         * TODO: Create play game
         *          *the game ends thru forfeit or checkmate
         * TODO:Create a player() class
         */


         Scanner scanner = new Scanner(System.in);
         /** 
          * loop that repeatedly displays the board's
            current state and prompts the user to enter their move in standard chess
            notation.
            Accept and parse the player's input to determine the source and
            destination squares for the intended move.
            For this phase, implement a simple validation check to ensure
            the entered move is in the correct format.
         */

         while (true) {
            System.out.println("Player " + /*function to get currentTurn */  " enter your move. Ex. E2 E4: "); //FIXME 

            String moveInput = scanner.next().toUpperCase();
            String DestinationInput = scanner.next().toUpperCase();
            if(!isValidMoveInput(moveInput) && !isValidMoveInput(DestinationInput))
             {
                System.out.println("Invalid move format. Try again.");
                continue;
            }
            GameBoard.movePiece(moveInput, DestinationInput);


            /*try {
            boolean moveSuccessful = ; // FIXME x will be function to check if move is even valid 
            if (!moveSuccessful) {
                System.out.println("Invalid move. Try again.");
            }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }*/


         }

    }

    //MIGHT MOVE THIS
    private static boolean isValidMoveInput (String input) 
    {
        return input.matches("[A-H][1-8] [A-H][1-8]");
        
    }
}
