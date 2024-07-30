import java.awt.BorderLayout;
import javax.swing.*;

public class BoardPanel extends JPanel 
{
    private int row;
    private int column;

    public BoardPanel(int row, int column)
    {
        this.row = row;
        this.column = column;
        setLayout(new BorderLayout());
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}