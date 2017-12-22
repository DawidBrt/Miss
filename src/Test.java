public class Test {
	public static void main(String[] args) {

		int size = 3; // wielkosc "piksela"+2
		int width = 200; // szerokość okna
		int height = 100; // wysokość okna
		// ThePoI thePoI = new ThePoI(); //Points of Interest
		ThePoI thePoI = null;
		int exist = 5; // % jednostek na mapie
		int sick = 5; // % chorych z zyjacych jednostek
		int infected = 2; // % infekujacy jeszcze nie chorych
		int immune = 5; // % odpornych na chorobe
		int toInfect = 2; //szansa na zainfekowanie w %

		// TODO(1): {--DONE--} obiekt wczytujący obraz png (monochromatyczny) - planowana funkcjonalność do symulacji: zwrócenie znalezionych PoI (czarne obszary), zwrócenie wymiarów obrazu (jako wymiary obszaru na którym rozpatrywana jest symulacja
		// TODO(2): w związku z (1) potrzebna będzie rekalibracja rozmiarów unitów do 1x1 px ewentualnie wprowadzić jakieś skalowanie

		String imgPath = "testImg1.png";
		String logPath = "log/sim.log";

		// logger
		Log logger = new Log(logPath);

		// obsługa obrazu
		ImageReader image = new ImageReader(imgPath);

		// obiekt zawierający świat oraz generujący losowo mieszkańców
		// TheUnits theUnits = new TheUnits(width, height, exist, sick, infected, immune, toInfect, thePoI);
		TheUnits theUnits = new TheUnits(image.getWidth(), image.getHeight(), exist, sick, infected, immune, toInfect, image.getPoIsFromImage());

		// obiekty do rysowania
		Drawer drawer = new Drawer(theUnits, width, height, size, image.getPoIsFromImage());
		MyFrame myFrame = new MyFrame(drawer);

		// głupi separator dla wejść loga
		String separator = ",";

		int unitsC = theUnits.countExisting();
		int immuneC = theUnits.countImmune();

		//teraz skończona pętla symulacji
		while (unitsC > immuneC) {

			System.out.println("Day: "+theUnits.getDayCounter());
			System.out.println("Time: "+theUnits.getTimeInDayCounter());
            System.out.println("Units: "+ theUnits.countExisting());
            System.out.println("Healthy: " + theUnits.countHealthy());
			System.out.println("Infected/NotSick: " + theUnits.countCarriers());
			System.out.println("Infected: " + theUnits.countInfected());
			System.out.println("Immune: " + theUnits.countImmune());
            System.out.println();


            logger.getLogLine(""
            		+theUnits.getDayCounter()+separator
            		+theUnits.getTimeInDayCounter()+separator
					+theUnits.countHealthy()+separator
					+theUnits.countCarriers()+separator
					+(theUnits.countInfected()-theUnits.countCarriers())+separator
					+theUnits.countImmune()
			);

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theUnits.makeMove();
			drawer.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theUnits.infect();
			drawer.repaint();
			logger.makeLogFile(logPath);
		}

	}
}