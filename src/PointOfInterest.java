import java.util.Random;

public class PointOfInterest extends Position {
    private int width;
    private int height;

    public PointOfInterest(int x, int y, int width, int height){
    	super(x, y);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
    	return height; 
    }

    public Position poiPosition(){
        Random generator = new Random();
        int x = generator.nextInt(getWidth());
        int y = generator.nextInt(getHeight());
        return new Position(x+super.getX(),y+super.getY());
    }
}
