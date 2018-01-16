package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TheUnits {
	// tablica sasiedztwa osobnikow
	private Unit[][] unitBoard;
	// lista osobnikow
	private List<Unit> unitList = new ArrayList<>();
	// parametry poczatkowe
	private int width,height,exist,sick,infected,immune,toInfect;
	private ThePoI thePoI;
	// skok czasowy
	private int timeInDayCounter = 1;

	private int dayCounter = 0;

	public TheUnits(int width, int height, int []initialParameters, ThePoI thePoI) {
		this.width = width;
		this.height = height;
		this.exist = initialParameters[0];
		this.sick = initialParameters[1];
		this.infected = initialParameters[2];
		this.immune = initialParameters[3];
		this.toInfect = initialParameters[4];
		this.thePoI = thePoI;
		generateUnits();
	}

	//generator jednostek
	public void generateUnits() {
		Unit[][] unitBoard = new Unit[height][width];
		Random generator = new Random();
		int existLevel;
		int sickLevel;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// exist to prawdopodobienstwo zaistnienia osobnika
				existLevel = generator.nextInt(100);
				if (existLevel < (exist)) {
					// dodawanie PoI dla osobnika
					List<PointOfInterest> PoI = new ArrayList<>();
					for (int k = 0; k < 3; k++) {
						int a = generator.nextInt(thePoI.getPointOfInterest().size());
						PoI.add(thePoI.getPointOfInterest().get(a));
					}
					// tworzenie osobnika
					Unit unit;
					sickLevel = generator.nextInt(100);
					if (sickLevel < (sick)) {
						int dos = generator.nextInt(7); // dos = day of sick
						unit = new Unit(j, i, dos + 5, PoI);
					} else if (sickLevel <= (sick + infected)) {
						int doi = generator.nextInt(3); // doi = day of infected
						unit = new Unit(j, i, doi + 2, PoI);
					} else if (sickLevel >= (100 - immune))
						unit = new Unit(j, i, 13, PoI);
					else {
						unit = new Unit(j, i, 1, PoI);
					}
					unitList.add(unit);
					unitBoard[i][j] = unit;
				}
			}
		}
		this.unitBoard = unitBoard;
	}

	public int getTimeInDayCounter() {
		return timeInDayCounter;
	}

	public void setTimeInDayCounter() {
		timeInDayCounter += 1;
		if (timeInDayCounter > 239) {
			timeInDayCounter = 0;
			setDayCounter();
		}
	}

	public int getDayCounter() {
		return dayCounter;
	}

	public void setDayCounter() {
		dayCounter += 1;
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
	public void updateUnitBoard(int previousX, int previousY, Unit unit) {
		unitBoard[previousY][previousX] = null;
		unitBoard[unit.getPosition().getY()][unit.getPosition().getX()] = unit;
	}

	// sprawdzanie czy pozycje w odpowiednim kierunku sa wolne
	public List<Position> checkLeftMove(List<Position> unitAvailableMoves, int unitX, int unitY) {
		if (unitX - 1 >= 0) {
			// ruch poziomo w lewo
			if (unitBoard[unitY][unitX - 1] == null) {
				unitAvailableMoves.add(new Position(unitX - 1, unitY));
			}
			// ruch po skosie w dol� i lewo
			if (unitY - 1 >= 0) {
				if (unitBoard[unitY - 1][unitX - 1] == null) {
					unitAvailableMoves.add(new Position(unitX - 1, unitY - 1));
				}
			}
			// ruch po skosie w gore i lewo
			if (unitY + 1 < unitBoard.length) {
				if (unitBoard[unitY + 1][unitX - 1] == null) {
					unitAvailableMoves.add(new Position(unitX - 1, unitY + 1));
				}
			}
		}
		return unitAvailableMoves;

	}

	public List<Position> checkRightMove(List<Position> unitAvailableMoves, int unitX, int unitY) {
		if (unitX + 1 < unitBoard[0].length) {
			// ruch poziomo w prawo
			if (unitBoard[unitY][unitX + 1] == null) {
				unitAvailableMoves.add(new Position(unitX + 1, unitY));
			}
			// ruch po skosie w dół i lewo
			if (unitY - 1 >= 0) {
				if (unitBoard[unitY - 1][unitX + 1] == null) {
					unitAvailableMoves.add(new Position(unitX + 1, unitY - 1));
				}
			}
			// ruch po skosie w górę i lewo
			if (unitY + 1 < unitBoard.length) {
				if (unitBoard[unitY + 1][unitX + 1] == null) {
					unitAvailableMoves.add(new Position(unitX + 1, unitY + 1));
				}
			}
		}
		return unitAvailableMoves;
	}

	public List<Position> checkUpMove(List<Position> unitAvailableMoves, int unitX, int unitY) {
		if (unitY - 1 >= 0) {
			if (unitBoard[unitY - 1][unitX] == null) {
				unitAvailableMoves.add(new Position(unitX, unitY - 1));
			}
			if (unitX + 1 < unitBoard[0].length) {
				if (unitBoard[unitY - 1][unitX + 1] == null) {
					unitAvailableMoves.add(new Position(unitX + 1, unitY - 1));
				}
			}
			if (unitX - 1 >= 0) {
				if (unitBoard[unitY - 1][unitX - 1] == null) {
					unitAvailableMoves.add(new Position(unitX - 1, unitY - 1));
				}
			}
		}
		return unitAvailableMoves;
	}

	public List<Position> checkDownMove(List<Position> unitAvailableMoves, int unitX, int unitY) {
		if (unitY + 1 < unitBoard.length) {
			if (unitBoard[unitY + 1][unitX] == null) {
				unitAvailableMoves.add(new Position(unitX, unitY + 1));
			}
			if (unitX + 1 < unitBoard[0].length) {
				if (unitBoard[unitY + 1][unitX + 1] == null) {
					unitAvailableMoves.add(new Position(unitX + 1, unitY + 1));
				}
			}
			if (unitX - 1 >= 0) {
				if (unitBoard[unitY + 1][unitX - 1] == null) {
					unitAvailableMoves.add(new Position(unitX - 1, unitY + 1));
				}
			}
		}
		return unitAvailableMoves;
	}

	// znalezienie mozliwych ruchow danej jednostki
	public List<Position> checkUnitAvailableMoves(Unit unit) {
		int PoIX = unit.getPoI().getX();
		int PoIY = unit.getPoI().getY();
		int unitX = unit.getPosition().getX();
		int unitY = unit.getPosition().getY();

		List<Position> unitAvailableMoves = new ArrayList<>();

		// ruch gora
		if (unitY > PoIY) {
			checkUpMove(unitAvailableMoves, unitX, unitY);
		}

		// ruch dol
		else if (unitY < PoIY) {
			checkDownMove(unitAvailableMoves, unitX, unitY);
		}

		// ruch prawo
		if (unitX < PoIX) {
			checkRightMove(unitAvailableMoves, unitX, unitY);
		}

		// ruch lewo
		else if (unitX > PoIX) {
			checkLeftMove(unitAvailableMoves, unitX, unitY);
		}

		if (unitAvailableMoves.size() == 0) {
			unitAvailableMoves.add(new Position(unitX, unitY));
		}

		return unitAvailableMoves;
	}

	//przesuniecie danej jednostki
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
			updateUnitBoard(previousX, previousY, unitList.get(i));
			unitList.get(i).changePoI();
			if (!unitList.get(i).isSick()) {
				availableMoves = checkUnitAvailableMoves(unitList.get(i));
				// losujemy ruch z dostepnych opcji i ustawiamy nowe wspolrzedne
				option = generator.nextInt(availableMoves.size());
				previousX = unitList.get(i).getPosition().getX();
				previousY = unitList.get(i).getPosition().getY();
				unitList.get(i).getPosition().setX(availableMoves.get(option).getX());
				unitList.get(i).getPosition().setY(availableMoves.get(option).getY());
				// aktualizacja planszy po kazdym ruchu pojedynczej jednostki
				updateUnitBoard(previousX, previousY, unitList.get(i));
				unitList.get(i).changePoI();
			}
		}
	}

	//zliczenie zainfekowanych sasiadow
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
						if (unitBoard[y][x] != null && unitBoard[y][x].isInfected()) {
							infected++;
						}
					}
				}
			}
		}
		return infected;
	}

	//proba zainfekowania zalezna od ilosci chorych sasiadow
	public void infect() {
		Random generator = new Random();
		int infection;
		int sickNeighbours;
		int x, y;
		Unit[][] nextMoveBoard = unitBoard.clone();
		for (int i = 0; i < unitList.size(); i++) {
			// osobnik jest zdrowy
			if (unitList.get(i).getSickLevel() == 1) {
				x = unitList.get(i).getPosition().getX();
				y = unitList.get(i).getPosition().getY();
				nextMoveBoard[y][x].setPosition(new Position(x, y));
				infection = generator.nextInt(100);

				sickNeighbours = countNeighboursInfected(x, y);
				if (infection < (toInfect * (Math.pow(1.4, sickNeighbours) - 1)/2 - 1)) {
					nextMoveBoard[y][x].setSickLevel(2);
				}
			}
			// osobnik niezdrowy oraz minal dzien
			else if (getTimeInDayCounter() == 0) {
				unitList.get(i).setSickLevel(unitList.get(i).getSickLevel() + 1);
			}

		}
		setTimeInDayCounter();
		this.unitBoard = nextMoveBoard.clone();
	}

	public int countExisting() {
		return unitList.size();
	}

	public int countInfected() {
		int counter = 0;
		for (int i = 0; i < unitList.size(); i++) {
			if (unitList.get(i).isInfected()) {
				counter++;
			}
		}
		return counter;
	}

	public int countCarriers() {
		int counter = 0;
		for (int i = 0; i < unitList.size(); i++) {
			if (unitList.get(i).isCarrier())
				counter++;
		}
		return counter;
	}

	public int countHealthy() {
		int counter = 0;
		for (int i = 0; i < unitList.size(); i++) {
			if (unitList.get(i).isHealthy())
				counter++;
		}
		return counter;
	}

	public int countImmune() {
		int counter = 0;
		for (int i = 0; i < unitList.size(); i++) {
			if (unitList.get(i).isImmune())
				counter++;
		}
		return counter;
	}

}
