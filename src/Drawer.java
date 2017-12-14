import java.awt.*;
import java.util.List;
import javax.swing.JPanel;

public class Drawer extends JPanel {
	private int size;
	private List<Unit> units;
	private List<PointOfInterest> pointOfInterests;

	public Drawer(TheUnits theUnits, int width, int height, int size, ThePoI thePoI) {
		this.size = size;
		this.units = theUnits.getUnitList();
		this.pointOfInterests = thePoI.getPoIList();
		setPreferredSize(new Dimension(width * size, height * size));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for(int i=0;i<pointOfInterests.size();i++){
		    g2d.setColor(Color.black);
		    int s = pointOfInterests.get(i).getSize();
		    int x = pointOfInterests.get(i).getPosition().getX();
		    int y = pointOfInterests.get(i).getPosition().getY();
		    g2d.drawRect(x*size,y*size,s*size,s*size);
        }

		for (int i = 0; i < units.size(); i++) {
			int sick = units.get(i).getSickLevel();
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
