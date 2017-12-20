import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TheUnits {
	// tablica sąsiedztwa osobnikow
	private Unit[][] unitNeighborhoodBoard;
	// lista osobnikow
	private List<Unit> unitList = new ArrayList<>();
	// szansa na zainfekowanie
	private int toInfect;
	// skok czasowy
	private int time = 1;

	public TheUnits(int width, int height, int rand, int sick,int infected, int immune, int toInfect, ThePoI thePoI) {
		this.toInfect = toInfect;

		Random generator = new Random();
		int exist;
		int sicked;
		Unit[][] unitBoard = new Unit[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// rand to prawdopodobieństwo zaistnienia osobnika
				exist = generator.nextInt(101);
				if (exist < (rand - 1)) {
					// dodawanie PoI dla osobnika
					List<PointOfInterest> PoI = new ArrayList<>();
					for(int k = 0; k<3;k++) {
						int a = generator.nextInt(thePoI.getPoIList().size());
						PoI.add(thePoI.getPoIList().get(a));
					}
					// tworzenie osobnika
					Unit unit;
					sicked = generator.nextInt(101);
					if (sicked <= (sick)) {
						int dos = generator.nextInt(7); // dos = day of sick
						unit = new Unit(j, i, dos+5, PoI);
					}
					else if (sicked<=(sick+infected)) {
						int doi = generator.nextInt(3); // doi = day of infected
						unit = new Unit(j, i, doi+2, PoI);
					}
					else if(sicked>=(100-immune))
						unit = new Unit(j, i, 13, PoI);
					else{
						unit = new Unit(j, i, 1, PoI);
					}
					unitList.add(unit);
					unitBoard[i][j] = unit;
				}
			}
		}
		this.unitNeighborhoodBoard = unitBoard;
	}

	public int getTime() {
		return time;
	}

	public void setTime(){
		time+=1;
	}

	public void setTime(int time){
		this.time = time;
	}

	public Unit[][] getUnitNeighborhoodBoard() {
		return unitNeighborhoodBoard;
	}

	public void setUnitNeighborhoodBoard(Unit[][] unitNeighborhoodBoard) {
		this.unitNeighborhoodBoard = unitNeighborhoodBoard;
	}

	public List<Unit> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<Unit> unitList) {
		this.unitList = unitList;
	}

	// aktualizacja planszy
	// poprzednia pozycja -> null
	public void updateUnitNeighborhoodBoard(int previousX, int previousY, Unit unit) {
		unitNeighborhoodBoard[previousY][previousX] = null;
		unitNeighborhoodBoard[unit.getPosition().getY()][unit.getPosition().getX()] = unit;
	}
	// sprawdzanie czy pozycje na () są wolne
	public List<Position> checkLeftMove(List<Position> unitAvailableMoves, int unitX, int unitY){
		if (unitX - 1 >= 0) {
			// ruch poziomo w lewo
			if (unitNeighborhoodBoard[unitY][unitX - 1] == null) {
				unitAvailableMoves.add(new Position(unitX - 1, unitY));
			}
			// ruch po skosie w dół i lewo
			if (unitY - 1 >= 0) {
				if (unitNeighborhoodBoard[unitY - 1][unitX - 1] == null) {
					unitAvailableMoves.add(new Position(unitX-1, unitY - 1));
				}
			}
			// ruch po skosie w górę i lewo
			if (unitY + 1 < unitNeighborhoodBoard.length) {
				if (unitNeighborhoodBoard[unitY + 1][unitX -1 ] == null) {
					unitAvailableMoves.add(new Position(unitX-1, unitY + 1));
				}
			}
		}
		return unitAvailableMoves;

	}
	public List<Position> checkRightMove(List<Position> unitAvailableMoves, int unitX, int unitY){
		if (unitX + 1 < unitNeighborhoodBoard[0].length) {
			// ruch poziomo w prawo
			if (unitNeighborhoodBoard[unitY][unitX + 1] == null) {
				unitAvailableMoves.add(new Position(unitX + 1, unitY));
			}
			// ruch po skosie w dół i lewo
			if (unitY - 1 >= 0) {
				if (unitNeighborhoodBoard[unitY - 1][unitX+1] == null) {
					unitAvailableMoves.add(new Position(unitX+1, unitY - 1));
				}
			}
			// ruch po skosie w górę i lewo
			if (unitY + 1 < unitNeighborhoodBoard.length) {
				if (unitNeighborhoodBoard[unitY + 1][unitX+1] == null) {
					unitAvailableMoves.add(new Position(unitX+1, unitY + 1));
				}
			}
		}
		return unitAvailableMoves;
	}
	public List<Position> checkUpMove(List<Position> unitAvailableMoves, int unitX, int unitY){
		if (unitY - 1 >= 0) {
			if (unitNeighborhoodBoard[unitY - 1][unitX] == null) {
				unitAvailableMoves.add(new Position(unitX, unitY - 1));
			}
			if (unitX + 1 < unitNeighborhoodBoard[0].length) {
				if (unitNeighborhoodBoard[unitY-1][unitX + 1] == null) {
					unitAvailableMoves.add(new Position(unitX + 1, unitY-1));
				}
			}
			if (unitX - 1 >= 0) {
				if (unitNeighborhoodBoard[unitY-1][unitX - 1] == null) {
					unitAvailableMoves.add(new Position(unitX - 1, unitY-1));
				}
			}
		}
		return unitAvailableMoves;
	}
	public List<Position> checkDownMove(List<Position> unitAvailableMoves, int unitX, int unitY){
		if (unitY + 1 < unitNeighborhoodBoard.length) {
			if (unitNeighborhoodBoard[unitY + 1][unitX] == null) {
				unitAvailableMoves.add(new Position(unitX, unitY + 1));
			}
			if (unitX + 1 < unitNeighborhoodBoard[0].length) {
				if (unitNeighborhoodBoard[unitY+1][unitX + 1] == null) {
					unitAvailableMoves.add(new Position(unitX + 1, unitY+1));
				}
			}
			if (unitX - 1 >= 0) {
				if (unitNeighborhoodBoard[unitY+1][unitX - 1] == null) {
					unitAvailableMoves.add(new Position(unitX - 1, unitY+1));
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
			checkUpMove(unitAvailableMoves,unitX,unitY);
		}

		// ruch dol
		else if (unitY < PoIY) {
			checkDownMove(unitAvailableMoves,unitX,unitY);
		}

		// ruch prawo
		if (unitX < PoIX) {
			checkRightMove(unitAvailableMoves,unitX,unitY);
		}

		// ruch lewo
		else if (unitX > PoIX) {
			checkLeftMove(unitAvailableMoves,unitX,unitY);
		}

		if(unitAvailableMoves.size()==0){
			unitAvailableMoves.add(new Position(unitX,unitY));
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
			updateUnitNeighborhoodBoard(previousX, previousY, unitList.get(i));
			unitList.get(i).changePoI();
			if(!unitList.get(i).isSick()){
				availableMoves = checkUnitAvailableMoves(unitList.get(i));
				// losujemy ruch z dostepnych opcji i ustawiamy nowe wspolrzedne
				option = generator.nextInt(availableMoves.size());
				previousX = unitList.get(i).getPosition().getX();
				previousY = unitList.get(i).getPosition().getY();
				unitList.get(i).getPosition().setX(availableMoves.get(option).getX());
				unitList.get(i).getPosition().setY(availableMoves.get(option).getY());
				// aktualizacja planszy po kazdym ruchu pojedynczej jednostki
				updateUnitNeighborhoodBoard(previousX, previousY, unitList.get(i));
				unitList.get(i).changePoI();
			}
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
				if (y >= 0 && y < unitNeighborhoodBoard.length && x >= 0 && x < unitNeighborhoodBoard[0].length) {
					// nie liczymy samej siebie
					if (!(x == col && y == row)) {
						// jezeli sasiad zarazony, zwiekszamy licznik
						if (unitNeighborhoodBoard[y][x] != null && unitNeighborhoodBoard[y][x].isInfected()) {
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
		Unit[][] nextMoveBoard = unitNeighborhoodBoard.clone();
		for (int i = 0; i < unitList.size(); i++) {
			// osobnik jest zdrowy
		    if(unitList.get(i).getSickLevel()==1) {
                x = unitList.get(i).getPosition().getX();
                y = unitList.get(i).getPosition().getY();
                nextMoveBoard[y][x].setPosition(new Position(x, y));
                infection = generator.nextInt(100);

                sickNeighbours = countNeighboursInfected(x, y);
                if (infection < (toInfect * (Math.pow(2, sickNeighbours) - 1) - 1)) {
                    nextMoveBoard[y][x].setSickLevel(2);
                }
            }
            // osobnik niezdrowy oraz minął dzień
            else if(getTime()%240==0){
		    	unitList.get(i).setSickLevel(unitList.get(i).getSickLevel() + 1);
				//unitList.get(i).nextTimeUnit();
				setTime(0);
			}
			setTime();
		}
		this.unitNeighborhoodBoard = nextMoveBoard.clone();
	}

	public int countExisting(){
		return unitList.size();
	}

	public int countInfected(){
		int counter = 0;
		for(int i=0;i<unitList.size();i++){
			if(unitList.get(i).isInfected()){
				counter ++;
			}
		}
		return counter;
	}
	public int countCarriers() {
		int counter = 0;
		for(int i = 0; i < unitList.size(); i++){
			if(unitList.get(i).isCarrier()) counter ++;
		}
		return counter;
	}

	public int countHealthy() {
		int counter = 0;
		for(int i = 0; i < unitList.size(); i++){
			if(unitList.get(i).isHealthy()) counter ++;
		}
		return counter;
	}

	public int countImmune() {
		int counter = 0;
		for(int i = 0; i < unitList.size(); i++){
			if(unitList.get(i).isImmune()) counter ++;
		}
		return counter;
	}

}
