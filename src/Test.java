public class Test {
	public static void main(String[] args) {
		int size = 7;
		int width = 200;
		int height = 100;
		ThePoI thePoI = new ThePoI();
		//TODO: zmiana wielkosci (width,heigh) powinna wplywac na wielkosc/ilosc/polozenie points of interest

		int exist = 40;
		int sick = 10;
		int infected = 3; //infekujacy jeszcze nie chorzy
		int immune = 5; //odporni na chorobe
		int toInfect = 5; //szansa na zainfekowanie w %
		TheUnits theUnits = new TheUnits(width, height, exist, sick, infected, immune, toInfect, thePoI);
		Drawer drawer = new Drawer(theUnits, width, height, size, thePoI);
		MyFrame myFrame = new MyFrame(drawer);
		while (true) {
            System.out.println("Units: "+ theUnits.countExist());
            System.out.println("Infected: " + theUnits.countInfected() + "\n");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theUnits.makeMove();
			drawer.repaint();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theUnits.infect();
			drawer.repaint();

		}
	}
}