import javax.swing.*;
import java.awt.*;

public class ChessGUI {
    public static void main(String[] args) {

        // create a new frame
        JFrame frame = new JFrame();

        // set basic info (defaultclose, size, visibility)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);

        // set the title and tile colors for easy updating
        frame.setTitle("Chessboard! Isn't this awesome?");
        frame.setLayout(new BorderLayout());
        Color tileColor1 = new Color(255,178,102);
        Color tileColor2 = new Color(255, 255, 204);

        // to resemble a chess board, make a panel to hold an 8x8 grid layout for individual spots
        JPanel chessPanel = new JPanel();
        chessPanel.setLayout(new GridLayout(8,8));

        // make 64 buttons, one for each individual spot; every element is originally assigned to null, so
        // loop through initializing and adding to frame. essentially, we allocate memory for the array
        // needed to hold 64 buttons, then we create the objects and add them to the frame.
        JButton[] buttons = new JButton[64];
        for(int i = 0; i < 64; i++) {
            buttons[i] = new JButton();
            chessPanel.add(buttons[i]);
        }

        // set up the board (colors), for every row (8 spots) alternating black vs.white spots based on
        // even/odd status.
        for(int i = 0; i < 8; i++) {
            if(i%2 == 0) {  // if even, set to WHITE
                buttons[i].setBackground(tileColor1);
            }
            else {  // if odd, set to BLACK
                buttons[i].setBackground(tileColor2);
            }
        }
        for(int i = 8; i < 16; i++) {
            if(i%2 == 0) {  // if even, set to BLACK
                buttons[i].setBackground(tileColor2);
            }
            else {  // if odd, set to WHITE
                buttons[i].setBackground(tileColor1);
            }
        }
        for(int i = 16; i < 24; i++) {
            if(i%2 == 0) {  // if even, set to WHITE
                buttons[i].setBackground(tileColor1);
            }
            else {  // if odd, set to BLACK
                buttons[i].setBackground(tileColor2);
            }
        }
        for(int i = 24; i < 32; i++) {
            if(i%2 == 0) {  // if even, set to BLACK
                buttons[i].setBackground(tileColor2);
            }
            else {  // if odd, set to WHITE
                buttons[i].setBackground(tileColor1);
            }
        }
        for(int i = 32; i < 40; i++) {
            if(i%2 == 0) {  // if even, set to WHITE
                buttons[i].setBackground(tileColor1);
            }
            else {  // if odd, set to BLACK
                buttons[i].setBackground(tileColor2);
            }
        }
        for(int i = 40; i < 48; i++) {
            if(i%2 == 0) {  // if even, set to BLACK
                buttons[i].setBackground(tileColor2);
            }
            else {  // if odd, set to WHITE
                buttons[i].setBackground(tileColor1);
            }
        }
        for(int i = 48; i < 56; i++) {
            if(i%2 == 0) {  // if even, set to WHITE
                buttons[i].setBackground(tileColor1);
            }
            else {  // if odd, set to BLACK
                buttons[i].setBackground(tileColor2);
            }
        }
        for(int i = 56; i < 64; i++) {
            if(i%2 == 0) {  // if even, set to BLACK
                buttons[i].setBackground(tileColor2);
            }
            else {  // if odd, set to WHITE
                buttons[i].setBackground(tileColor1);
            }
        }

        // center the frame and make the frame fit the screen.
        frame.add(chessPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);

        // idea -> make another panel to sit on top of the previous panel that holds the locations of
        // pieces
    }
}
