import java.util.List;

public class Infection {

	private Unit[][] unitBoard;
	private List<Unit> unitList;
	private int infectedNeighboursNeededToGetSick = 3;

	public Infection(Maker maker) {
		this.unitBoard = maker.getUnitBoard();
		this.unitList = maker.getUnitList();
	}

	public int countNeighboursInfected(int col, int row) {
		int x, y;
		int infected = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				x = col + j;
				y = row + i;
				// granice planszy
				if (y >= 0 && y < unitBoard.length && x >= 0 && x < unitBoard[0].length) {
					// nie liczymy samej siebie
					if (!(x == col && y == row)) {
						// jezeli sasiad zarazony, zwiekszamy licznik
						if (unitBoard[y][x] != null && unitBoard[y][x].isSick()) {
							infected++;
						}
					}
				}
			}
		}
		return infected;
	}

	public void infect() {
		int x = 0, y = 0;
		Unit[][] nextMoveBoard = unitBoard.clone();
		for (int i = 0; i < unitList.size(); i++) {
			x = unitList.get(i).getPosition().getX();
			y = unitList.get(i).getPosition().getY();
			nextMoveBoard[y][x].setPosition(new Position(x, y));
			if (countNeighboursInfected(x, y) > infectedNeighboursNeededToGetSick - 1) {
				nextMoveBoard[y][x].setSick(true);
			} else {
			}
		}
		this.unitBoard = nextMoveBoard.clone();
	}
}
