import java.awt.*;
import javax.swing.JPanel;

public class Drawer extends JPanel {
    public int size;
    public int x;
    public int y;
    private Maker m;
    public Drawer(Maker m, int x, int y, int size) {
        this.m = m;
        this.x = x;
        this.y = y;
        this.size = size;
        setPreferredSize(new Dimension(x*size,y*size));}


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < m.units.size(); i++) {
            if (m.units.get(i).isSick()) {
                g2d.setColor(Color.red);
            } else {
                g2d.setColor(Color.yellow);
            }
            g2d.fillRect(m.units.get(i).getX() * size+1, m.units.get(i).getY() * size+1, size-2, size-2);
        }

    }

}
