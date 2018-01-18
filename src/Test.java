import simulation.Simulation;

public class Test {
	public static void main(String[] args) {
		Simulation simulation;
		if(args.length<2){
			System.out.println("Nie podano odpowiedniej liczby argumentów");
			System.out.println("Ustawiono argumenty na domyślne");
			System.out.println("Log: log/sim.log | Obraz: img/img1.png");
			simulation = new Simulation("log/sim.log","img/img1.png");
		}
		else {
			simulation = new Simulation(args[0], args[1]);
		}
		simulation.simualtion();
	}
}