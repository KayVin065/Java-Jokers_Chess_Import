import java.util.Scanner;
public class Jokers 
{
    public static void main(String[] args)
    {
        Scanner scnr = new Scanner(System.in);
        Board GameBoard = new Board();
        String input;

        input = scnr.nextLine();

        /**
         * TODO: get user input()
         * TODO: translate user input in location variables
         * TODO: Set move set for the pieces()
         * TODO: Create play game
         *          *the game ends thru forfeit or checkmate
         * TODO:Create a player() class
         */
    }
    
    public static int convert(String x) {
        int output = 0;
        switch(x) {
            case "A" : 
                return 0;

            case "B" : 
                return 1;
            
            case "C" : 
                return 2;
            
            case "D" : 
                return 3;
            
            case "E" : 
                output = 2;
                break;
        
            case "F" : 
                output = 2;
                break;
            
            case "G" : 
                output = 2;
                break;
            
            case "H" : 
                output = 2;
                break;
            
            default: 
                System.out.println("Invalid column");
        }

        return output;
    }

}
