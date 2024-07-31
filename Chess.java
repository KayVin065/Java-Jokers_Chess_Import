import board.*;
import javax.swing.JOptionPane;
import piece.Player;

public class Chess {
    public static Board board = new Board();
    Player white;
    Player black;
    DisplayBoard gameBoard = new DisplayBoard();

    public void main(String[] args) {
    start();  
    }
/* */
    /**
     * Initializes the game attributes
     */
    public void start() 
    {
        gameBoard.createChessBoard();
        white = new Player("white");
        black = new Player("black");
        board.display();
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
            System.out.println("Enter move formatted as \"[FROM] [TO]\" EX: \"E2 E4\": ");
            System.out.println(currentTurn.getColor() + " player enter move: ");
            String userInput = gameBoard.getMove();
            while (userInput == null) {
                userInput = gameBoard.getMove(); 
                try {
                    Thread.sleep(1000); // Avoid busy-waiting
                } catch (InterruptedException e) 
                {
                   // e.printStackTrace();
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
            //        JOptionPane.showMessageDialog(null, "message", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(userInput);
            if(board.canMove(userInput, currentTurn) == false)
            {
                System.out.println("\nERROR incorrect move");
                gameBoard.setMove(null);
                gameBoard.setDisplayMoveValid(false);
                board.setCurrentPlayer(currentTurn);
                play(currentTurn);
            }
            else
            {
                board.movePiece(userInput, currentTurn);
                gameBoard.setMove(null);
                gameBoard.setDisplayMoveValid(true);
            }
            //board.movePiece(userInput, currentTurn);
            if("black".equals(currentTurn.getColor()))
            {
                gameBoard.setDisplayMoveValid(true);
                gameBoard.setMove(null);
                board.setCurrentPlayer(white);
                play(white);
            }
            else if("white".equals(currentTurn.getColor()))
            {
                gameBoard.setDisplayMoveValid(true);
                gameBoard.setMove(null);
                board.setCurrentPlayer(black);
                play(black);
            }
            if (end()) {
                System.out.println("Game Over.");
                System.exit(0);
            }
        }

    

    /**
     * Ends the game and determines a winner or a draw
     * 
     * calls isCheckmate & returns true if isCheckmate returns true - determines winner
     * calls isCheck & returns true if isCheck returns true - determines draw if both are checked (eh)
     */
    
       
    public boolean end()
    {

        return false;
    }
}  