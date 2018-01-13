package objects;

import java.util.ArrayList;
import java.util.List;

public class ThePoI {
    private ArrayList<PointOfInterest> pointOfInterest = new ArrayList<>();

    public ThePoI(){
        // TODO: usinąć hardcodowane PoI - zostawiam dla testów
        pointOfInterest.add( new PointOfInterest(0,0,20,30));
        pointOfInterest.add( new PointOfInterest(0,80,20,20));
        pointOfInterest.add( new PointOfInterest(150,60,30,30));
        pointOfInterest.add( new PointOfInterest(80,48,40,30));
        pointOfInterest.add( new PointOfInterest(150,0,40,20));
    }

    public void fromPoIList(ArrayList<PointOfInterest> list){
        // Do usunięcia wraz z hardcodowaną wersją
        this.pointOfInterest.clear();
        this.pointOfInterest.addAll(list);
    }

    public List<PointOfInterest> getPointOfInterest() {
        return pointOfInterest;
    }
}
