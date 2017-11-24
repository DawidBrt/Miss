public class Test {
	public static void main(String[] args) {
		int size = 9;
		int width = 200;
		int height = 100;
		int exist = 20;
		int sick = 10;
		Maker maker = new Maker(width, height, exist, sick);
		UnitMove unitMove = new UnitMove(maker, width, height);
		Drawer drawer = new Drawer(maker, width, height, size);
		MyFrame myFrame = new MyFrame(drawer);
		while (true) {
			unitMove.makeMove(maker.getUnitList());
			drawer.repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}