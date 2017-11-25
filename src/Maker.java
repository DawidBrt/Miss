import java.util.*;

public class Maker {
	private List<Unit> unitList = new ArrayList<>();
	private Unit[][] unitBoard;

	public Maker(int width, int height, int rand, int sick) {
		Random generator = new Random();
		int exist;
		int sicked;

		Unit[][] unitBoard = new Unit[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				exist = generator.nextInt(100);
				if (exist < (rand - 1)) {
					Unit unit;
					sicked = generator.nextInt(100);
					if (sicked > (sick - 1)) {
						unit = new Unit(j, i, false);
					} else {
						unit = new Unit(j, i, true);
					}
					unitList.add(unit);
					unitBoard[i][j] = unit;
				}
			}
		}
		this.unitBoard = unitBoard;
	}

	public List<Unit> getUnitList() {
		return this.unitList;
	}

	public Unit[][] getUnitBoard() {
		return this.unitBoard;
	}
}
