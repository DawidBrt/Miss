import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame{
    public MyFrame(Drawer drawer){
        super("Draw");
        add(drawer);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
