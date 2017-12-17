import java.util.Random;

public class PointOfInterest {
    private String location;
    private int width;
    private int height;
    private Position position = new Position(0,0);

    public PointOfInterest(int x, int y, int width, int height, String location){
        this.width = width;
        this.height = height;
        this.position.setX(x);
        this.position.setY(y);
        this.location = location;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() { return height; }

    public Position getPosition() {
        return position;
    }

    public Position poiPosition(){
        Random generator = new Random();
        int x = generator.nextInt(getWidth());
        int y = generator.nextInt(getHeight());
        return new Position(x+getPosition().getX(),y+getPosition().getY());
    }
}
