import board.*;
import javax.swing.JOptionPane;
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
    /**
     * Initializes the game attributes
     */
    public void start() 
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
        // currently outputs an infinite loop !!!
    
            System.out.println("Enter move formatted as \"[FROM] [TO]\" EX: \"E2 E4\": ");
            System.out.println(currentTurn.getColor() + " player enter move: ");
            System.out.println(userInput);
            // if(board.isChecked(kingPosition, currentTurn, checkKing, checkKingPosition)(currentTurn) == true)
            // {
            //     JOptionPane.showMessageDialog(null, currentTurn.getColor() + ", YOUR KING IS IN CHECK!!!", "WARNING!", JOptionPane.PLAIN_MESSAGE);
            // }
            while (userInput == null) 
            {
                userInput = board.getMove(); 
                try {
                    Thread.sleep(100); // Avoid busy-waiting
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
            else
            {  
                board.movePiece(userInput, currentTurn);
            }
            //board.movePiece(userInput, currentTurn);
            if("black".equals(currentTurn.getColor()))
            {
                board.setCurrentPlayer(white);
                board.setMove(null);
                userInput = null;
                board.updateBoardDisplay();
                board.updateTurnDisplay(white);
                play(white);
            }
            else if("white".equals(currentTurn.getColor()))
            {
                board.setCurrentPlayer(black);
                board.setMove(null);
                userInput = null;
                board.updateBoardDisplay();
                board.updateTurnDisplay(black);
                play(black);
            }
            
        }

    

    /**
     * Ends the game and determines a winner or a draw
     * 
     */
    public void end()
    {
        System.exit(0);
    }
}  