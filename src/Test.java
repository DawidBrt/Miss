public class Test {
	public static void main(String[] args) {

		//
		int size = 7;
		// szerokość okna
		int width = 200;
		// wysokość okna
		int height = 100;
		//
		ThePoI thePoI = new ThePoI();
		//TODO: zmiana wielkosci (width,heigh) powinna wplywac na wielkosc/ilosc/polozenie points of interest

		//
		int exist = 40;
		// 1 - zdrowy, 2-4 - zakaza(bez objawow), 5-12 - chory,13 - "niesmiertelny" przynajmniej na grype
		int sick = 10;
		int infected = 3; //infekujacy jeszcze nie chorzy
		int immune = 5; //odporni na chorobe
		int toInfect = 5; //szansa na zainfekowanie w %

		// obiekt zawierający świat oraz generujący losowo mieszkańców
		TheUnits theUnits = new TheUnits(width, height, exist, sick, infected, immune, toInfect, thePoI);

		// obiekty do rysowania
		Drawer drawer = new Drawer(theUnits, width, height, size, thePoI);
		MyFrame myFrame = new MyFrame(drawer);

		// pętla symulacji
		while (true) {
            System.out.println("Units: "+ theUnits.countExisting());
            System.out.println("Infected: " + theUnits.countInfected());
            System.out.println();
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