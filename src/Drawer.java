import java.awt.*;
import java.util.List;

import javax.swing.JPanel;

public class Drawer extends JPanel {
	private int size;
	private List<Unit> units;

	public Drawer(TheUnits theUnits, int width, int height, int size) {
		this.size = size;
		this.units = theUnits.getUnitList();
		setPreferredSize(new Dimension(width * size, height * size));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < units.size(); i++) {
			int sick = units.get(i).getSick();
			if (sick==1) {
				g2d.setColor(Color.yellow);
			} else if(sick<=4){
				g2d.setColor(Color.orange);
			} else if(sick<=12){
				g2d.setColor(Color.red);
			} else{
				g2d.setColor(Color.green);
			}
			g2d.fillRect(units.get(i).getPosition().getX() * size + 1, units.get(i).getPosition().getY() * size + 1,
					size - 2, size - 2);
		}
	}

}
