public class Test {
	public static void main(String[] args) {
		int size = 9;
		int width = 200;
		int height = 100;
		int exist = 80;
		int sick = 10;
		Maker maker = new Maker(width, height, exist, sick);
		UnitMove unitMove = new UnitMove(maker);
		Drawer drawer = new Drawer(maker, width, height, size);
		MyFrame myFrame = new MyFrame(drawer);
		Infection infection = new Infection(maker);
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			unitMove.makeMove();
			drawer.repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			infection.infect();
			drawer.repaint();

		}
	}
}