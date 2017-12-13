import java.util.ArrayList;
import java.util.List;

public class Unit {
    private Position position = new Position(0,0);
    private List<Position> pointsOfInterest = new ArrayList<>();
    private int sick;
    // 1 - zdrowy, 2-4 - zakaza(bez objawow), 5-12 - chory,13 - "niesmiertelny" przynajmniej na grype


    public Unit(int x, int y, int sick, List<PointOfInterest> pointOfInterests){
        this.position.setX(x);
        this.position.setY(y);
        if(sick<1 || sick>13){
            sick = 1;
        }
        this.sick = sick;
        for(int i=0;i<pointOfInterests.size();i++){
            pointsOfInterest.add(pointOfInterests.get(i).poi());
        }
    }

    public Position getPosition() {
    	return position;
    }
    
    public void setPosition(Position position) {
    	this.position.setX(position.getX());
    	this.position.setY(position.getY());
    }

    public int getSick() {
        return sick;
    }

    public void setSick(int sick) {
        if(sick<1 || sick>13){
            sick = 1;
        }
        this.sick = sick;
    }

    public boolean isInfected(){
        if(sick>=2 && sick <13){
            return true;
        }
        else{
            return false;
        }
    }
    public void nextDay(){
        if(sick>1 && sick<13)
            sick+=1;
    }

    public Position getPoI(){
        return pointsOfInterest.get(0);
    }
}
