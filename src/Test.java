public class Test {
	public static void main(String[] args) {
		int size = 9;
		int width = 100;
		int height = 50;
		int exist = 40;
		int sick = 10;
		int toInfect = 5; //szansa na zainfekowanie w %
		TheUnits theUnits = new TheUnits(width, height, exist, sick, toInfect);
		Drawer drawer = new Drawer(theUnits, width, height, size);
		MyFrame myFrame = new MyFrame(drawer);
		while (true) {
            System.out.println("Units: "+ theUnits.countExist());
            System.out.println("Infected: " + theUnits.countInfected());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theUnits.makeMove();
			drawer.repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theUnits.infect();
			drawer.repaint();

		}
	}
}