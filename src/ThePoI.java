import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThePoI {
    private List<PointOfInterest> pointOfInterest = new ArrayList<>();

    public ThePoI(){
        pointOfInterest.add( new PointOfInterest(0,65,35));
        pointOfInterest.add( new PointOfInterest(50,10,25));
        pointOfInterest.add( new PointOfInterest(150,20,40));
        pointOfInterest.add( new PointOfInterest(70,60,30));
        pointOfInterest.add( new PointOfInterest(0,0,27));
    }

    public List<PointOfInterest> getPoIList() {
        return pointOfInterest;
    }
}
