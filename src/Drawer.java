import java.awt.*;
import java.util.List;

import javax.swing.JPanel;

public class Drawer extends JPanel {
	private int size;
	private List<Unit> units;

	public Drawer(Maker maker, int width, int height, int size) {
		this.size = size;
		this.units = maker.getUnitList();
		setPreferredSize(new Dimension(width * size, height * size));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < units.size(); i++) {
			if (units.get(i).isSick()) {
				g2d.setColor(Color.red);
			} else {
				g2d.setColor(Color.yellow);
			}
			g2d.fillRect(units.get(i).getPosition().getX() * size + 1, units.get(i).getPosition().getY() * size + 1,
					size - 2, size - 2);
		}
	}

}
