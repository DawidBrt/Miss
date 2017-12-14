import java.util.Random;

public class PointOfInterest {
    private int size;
    private Position position = new Position(0,0);

    public PointOfInterest(int x, int y, int size){
        this.size = size;
        this.position.setX(x);
        this.position.setY(y);
    }

    public int getSize() {
        return size;
    }

    public Position getPosition() {
        return position;
    }

    public Position poiPosition(){
        Random generator = new Random();
        int x = generator.nextInt(getSize());
        int y = generator.nextInt(getSize());
        return new Position(x+getPosition().getX(),y+getPosition().getY());
    }
}
