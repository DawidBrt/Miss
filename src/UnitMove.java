import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnitMove {

	private Unit[][] unitBoard;
	private List<Unit> unitList;

	public UnitMove(Maker maker) {
		this.unitBoard = maker.getUnitBoard();
		this.unitList = maker.getUnitList();
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
}
