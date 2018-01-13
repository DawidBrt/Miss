package GUI;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	
    public MyFrame(Map map){
        super("Draw");
        add(map);
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

    public void updateFrame(Map map) {
        this.getContentPane().removeAll();
        this.getContentPane().add(map);
        this.revalidate();
    }
}
