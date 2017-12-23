import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame{
	
    public MyFrame(Drawer drawer,MenuPanel menu){
        super("Draw");
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(drawer,BorderLayout.WEST);
        container.add(menu,BorderLayout.EAST);
        add(container);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    public void updateFrame(Drawer drawer, MenuPanel menuPanel) {
    	JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(drawer,BorderLayout.WEST);
        container.add(menuPanel,BorderLayout.EAST);
        this.getContentPane().removeAll();
        this.getContentPane().add(container);
        this.revalidate();
    }
}
