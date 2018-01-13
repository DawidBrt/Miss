import java.awt.BorderLayout;
import java.awt.Dimension;

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

    public MyFrame(MenuPanel menuPanel){
        super("Menu");
        add(menuPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void updateFrame(Drawer drawer) {
        this.getContentPane().removeAll();
        this.getContentPane().add(drawer);
        this.revalidate();
    }
}
