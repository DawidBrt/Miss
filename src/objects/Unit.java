package objects;

import java.util.ArrayList;
import java.util.List;

public class Unit {
    // pozycja osobnika
    private Position position = new Position(0,0);

    // lista PoI osobnika (moze byc rozna dla roznych osobnikow)
    private List<Position> pointsOfInterest = new ArrayList<>();

    // 1 - zdrowy, 2-4 - zakaza(bez objawow), 5-12 - chory,13 - "niesmiertelny" przynajmniej na grype
    private int sick;

    private int counter; //liczy ile czasu w danym miejscu spedzil


    public Unit(int x, int y, int sick, List<PointOfInterest> pointOfInterests){
        this.position.setX(x);
        this.position.setY(y);
        if(sick<1 || sick>13){
            sick = 1;
        }
        this.sick = sick;
        for(int i=0;i<pointOfInterests.size();i++){
            pointsOfInterest.add(pointOfInterests.get(i).poiPositionRandomReset());
        }
        counter = 0;
    }

    public Position getPosition() {
    	return position;
    }
    
    public void setPosition(Position position) {
    	this.position.setX(position.getX());
    	this.position.setY(position.getY());
    }

    public int getCounter(){
        return counter;
    }

    public void setCounter(){
        counter++;
    }

    public int getSickLevel() {
        return sick;
    }

    public void setSickLevel(int sick) {
        if(sick > 13){
            sick = 13;
        }
        else if(sick < 1){
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

    public boolean isImmune() {
        return (sick == 13);
    }

 /*   public void nextTimeUnit(){
        if(sick>1 && sick<13)
            sick+=1;
    }*/

    public Position getPoI(){
        return pointsOfInterest.get(0);
    }

    public void changePoI(){
        if(getPosition().getX()==pointsOfInterest.get(0).getX()
                && getPosition().getY()==pointsOfInterest.get(0).getY()){
            if(getCounter()<70){
                setCounter();
            }
            else {
                Position tmp = pointsOfInterest.get(0);
                pointsOfInterest.remove(0);
                pointsOfInterest.add(tmp);
            }
        }
    }
}
