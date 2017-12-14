import java.util.ArrayList;
import java.util.List;

public class Unit {
    // pozycja osobnika
    private Position position = new Position(0,0);

    // lista PoI osobnika (może być różna dla różnych osobników)
    private List<Position> pointsOfInterest = new ArrayList<>();

    // 1 - zdrowy, 2-4 - zakaza(bez objawow), 5-12 - chory,13 - "niesmiertelny" przynajmniej na grype
    private int sick;


    public Unit(int x, int y, int sick, List<PointOfInterest> pointOfInterests){
        this.position.setX(x);
        this.position.setY(y);
        if(sick<1 || sick>13){
            sick = 1;
        }
        this.sick = sick;
        for(int i=0;i<pointOfInterests.size();i++){
            pointsOfInterest.add(pointOfInterests.get(i).poiPosition());
        }
    }

    public Position getPosition() {
    	return position;
    }
    
    public void setPosition(Position position) {
    	this.position.setX(position.getX());
    	this.position.setY(position.getY());
    }

    public int getSickLevel() {
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
    public boolean isHealthy(){
        return (sick == 1);
    }
    public boolean isCarrier(){
        return (sick >= 2 && sick <=4);
    }
    public boolean isSick(){
        return (sick >= 5 && sick <=12);
    }
    public void nextTimeUnit(){
        if(sick>1 && sick<13)
            sick+=1;
    }

    public Position getPoI(){
        return pointsOfInterest.get(0);
    }
}
