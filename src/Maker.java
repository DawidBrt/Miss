import java.util.*;

public class Maker {
	private List<Unit> units = new ArrayList<>();
	private boolean[][] isCellBlank;

	public Maker(int width, int height, int rand, int sick) {
		Random generator = new Random();
		int exist;
		int sicked;
		boolean[][] isCellBlank = new boolean[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				exist = generator.nextInt(100);
				isCellBlank[i][j] = true;
				if (exist < (rand - 1)) {
					Unit unit;
					sicked = generator.nextInt(100);
					if (sicked > (sick - 1)) {
						unit = new Unit(j, i, false);
					} else {
						unit = new Unit(j, i, true);
					}
					units.add(unit);
					isCellBlank[i][j] = false;

				}
			}
		}
		this.isCellBlank = isCellBlank;
	}

	public List<Unit> getUnitList() {
		return this.units;
	}

	// zaktualizowanie gdzie znajduja sie puste komorki
	public void updateCellBlank() {
		for (int i = 0; i < this.isCellBlank.length; i++) {
			for (int j = 0; j < this.isCellBlank[i].length; j++) {
				isCellBlank[i][j] = true;
			}
		}
		for (int i = 0; i < units.size(); i++) {
			int x = units.get(i).getPosition().getX();
			int y = units.get(i).getPosition().getY();
			this.isCellBlank[y][x] = false;
		}
	}

	public boolean[][] getIsCellBlank() {
		updateCellBlank();
		return this.isCellBlank;
	}
}
