import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TheUnits {

	private Unit[][] unitBoard;
	private List<Unit> unitList = new ArrayList<>();
	private int toInfect;

	public TheUnits(int width, int height, int rand, int sick, int toInfect) {
		this.toInfect = toInfect;

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

	public Unit[][] getUnitBoard() {
		return unitBoard;
	}

	public void setUnitBoard(Unit[][] unitBoard) {
		this.unitBoard = unitBoard;
	}

	public List<Unit> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<Unit> unitList) {
		this.unitList = unitList;
	}

	// aktualizacja planszy
	// poprzednia pozycja -> null
	public void updateBoard(int previousX, int previousY, Unit unit) {
		unitBoard[previousY][previousX] = null;
		unitBoard[unit.getPosition().getY()][unit.getPosition().getX()] = unit;
	}

	// znalezienie mozliwych ruchow danej jednostki
	public List<Position> checkUnitAvailableMoves(Unit unit) {
		int unitX = unit.getPosition().getX();
		int unitY = unit.getPosition().getY();

		ArrayList<Position> unitAvailableMoves = new ArrayList<Position>();
		// aktualna pozycja (bezruch)
		unitAvailableMoves.add(new Position(unitX, unitY));

		if (unitY - 1 >= 0) {
			if (unitBoard[unitY - 1][unitX] == null) {
				unitAvailableMoves.add(new Position(unitX, unitY - 1));
			}
		}
		// ruch prawo
		if (unitX + 1 < unitBoard[0].length) {
			if (unitBoard[unitY][unitX + 1] == null) {
				unitAvailableMoves.add(new Position(unitX + 1, unitY));
			}
		}
		// ruch dol
		if (unitY + 1 < unitBoard.length) {
			if (unitBoard[unitY + 1][unitX] == null) {
				unitAvailableMoves.add(new Position(unitX, unitY + 1));
			}
		}

		// ruch lewo
		if (unitX - 1 >= 0) {
			if (unitBoard[unitY][unitX - 1] == null) {
				unitAvailableMoves.add(new Position(unitX - 1, unitY));
			}
		}

		return unitAvailableMoves;
	}

	public void makeMove() {
		Random generator = new Random();
		for (int i = 0; i < unitList.size(); i++) {
			List<Position> availableMoves = checkUnitAvailableMoves(unitList.get(i));
			// losujemy ruch z dostepnych opcji i ustawiamy nowe wspolrzedne
			int option = generator.nextInt(availableMoves.size());
			int previousX = unitList.get(i).getPosition().getX();
			int previousY = unitList.get(i).getPosition().getY();
			unitList.get(i).getPosition().setX(availableMoves.get(option).getX());
			unitList.get(i).getPosition().setY(availableMoves.get(option).getY());
			// aktualizacja planszy po kazdym ruchu pojedynczej jednostki
			updateBoard(previousX, previousY, unitList.get(i));
		}
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
		Random generator = new Random();
		int infection;
		int sickNeighbours;
		int x, y;
		Unit[][] nextMoveBoard = unitBoard.clone();
		for (int i = 0; i < unitList.size(); i++) {
			x = unitList.get(i).getPosition().getX();
			y = unitList.get(i).getPosition().getY();
			nextMoveBoard[y][x].setPosition(new Position(x, y));
			infection = generator.nextInt(100);

			sickNeighbours = countNeighboursInfected(x,y);
			if (infection < (toInfect*sickNeighbours) - 1) {
				nextMoveBoard[y][x].setSick(true);
			}
		}
		this.unitBoard = nextMoveBoard.clone();
	}

	public int countExist(){
		return unitList.size();
	}

	public int countInfected(){
		int counter = 0;
		for(int i=0;i<unitList.size();i++){
			if(unitList.get(i).isSick()){
				counter ++;
			}
		}
		return counter;
	}

}
