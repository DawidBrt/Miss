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
		    int w = pointOfInterests.get(i).getWidth();
		    int h = pointOfInterests.get(i).getHeight();
		    int x = pointOfInterests.get(i).getX();
		    int y = pointOfInterests.get(i).getY();
		    g2d.fillRect(x*size,y*size,w*size,h*size);
        }

		for (int i = 0; i < units.size(); i++) {
			int sick = units.get(i).getSickLevel();
			if (sick==1) {
				g2d.setColor(Color.yellow);
			} else if(sick<=4){
				g2d.setColor(Color.gray);
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
