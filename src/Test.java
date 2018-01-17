import simulation.Simulation;

public class Test {
	public static void main(String[] args) {
		//System.out.println(args[1]);
		System.out.println(args.length);
		Simulation simulation = new Simulation("/home/dawid/GIT/Miss/log/sim.log", "/home/dawid/GIT/Miss/img/img2.png");
		simulation.simualtion();
	}
}