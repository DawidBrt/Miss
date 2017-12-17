import java.util.ArrayList;
import java.util.List;

public class ThePoI {
    private List<PointOfInterest> pointOfInterest = new ArrayList<>();

    public ThePoI(){
        pointOfInterest.add( new PointOfInterest(1,64,35));
        pointOfInterest.add( new PointOfInterest(50,10,25));
        pointOfInterest.add( new PointOfInterest(150,20,40));
        pointOfInterest.add( new PointOfInterest(70,60,30));
        pointOfInterest.add( new PointOfInterest(1,1,27));
    }

    public List<PointOfInterest> getPoIList() {
        return pointOfInterest;
    }
}
