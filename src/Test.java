public class Test {
	public static void main(String[] args) {

		int size = 7; // wielkosc "piksela"+2
		int width = 200; // szerokość okna
		int height = 100; // wysokość okna
		ThePoI thePoI = new ThePoI(); //Points of Interest
		int exist = 10; // % jednostek na mapie
		int sick = 10; // % chorych z zyjacych jednostek
		int infected = 3; // % infekujacy jeszcze nie chorych
		int immune = 5; // % odpornych na chorobe
		int toInfect = 5; //szansa na zainfekowanie w %

		// obiekt zawierający świat oraz generujący losowo mieszkańców
		TheUnits theUnits = new TheUnits(width, height, exist, sick, infected, immune, toInfect, thePoI);

		// obiekty do rysowania
		Drawer drawer = new Drawer(theUnits, width, height, size, thePoI);
		MyFrame myFrame = new MyFrame(drawer);

		// nieskonczona pętla symulacji
		while (true) {
            System.out.println("Units: "+ theUnits.countExisting());
            System.out.println("Infected: " + theUnits.countInfected());
            System.out.println();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theUnits.makeMove();
			drawer.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theUnits.infect();
			drawer.repaint();

		}
	}
}