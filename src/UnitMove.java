import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnitMove {
	private Maker maker;
	private int width;
	private int height;

	public UnitMove(Maker maker, int width, int height) {
		this.maker = maker;
		this.width = width;
		this.height = height;
	}

	// znalezienie mozliwych ruchow danej jednostki
	public List<Position> checkUnitAvailableMoves(Unit unit) {
		boolean[][] blankCells = maker.getIsCellBlank();
		int unitX = unit.getPosition().getX();
		int unitY = unit.getPosition().getY();
		ArrayList<Position> unitAvailableMoves = new ArrayList<Position>();

		// aktualna pozycja (bezruch)
		unitAvailableMoves.add(new Position(unitX, unitY));

		// ruch gora
		if (unitY - 1 >= 0) {
			if (blankCells[unitY - 1][unitX]) {
				unitAvailableMoves.add(new Position(unitX, unitY - 1));
			}
		}
		// ruch prawo
		if (unitX + 1 < width) {
			if (blankCells[unitY][unitX + 1]) {
				unitAvailableMoves.add(new Position(unitX + 1, unitY));
			}
		}
		// ruch dol
		if (unitY + 1 < height) {
			if (blankCells[unitY + 1][unitX]) {
				unitAvailableMoves.add(new Position(unitX, unitY + 1));
			}
		}

		// ruch lewo
		if (unitX - 1 >= 0) {
			if (blankCells[unitY][unitX - 1]) {
				unitAvailableMoves.add(new Position(unitX - 1, unitY));
			}
		}
		return unitAvailableMoves;

	}

	public void makeMove(List<Unit> units) {
		Random generator = new Random();
		for (int i = 0; i < units.size(); i++) {
			List<Position> availableMoves = checkUnitAvailableMoves(units.get(i));
			// losujemy ruch z dostepnych opcji i ustawiamy nowe wspolrzedne
			int option = generator.nextInt(availableMoves.size());
			units.get(i).getPosition().setX(availableMoves.get(option).getX());
			units.get(i).getPosition().setY(availableMoves.get(option).getY());
		}
	}
}
