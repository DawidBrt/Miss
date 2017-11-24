public class Unit {
    private Position position = new Position(0,0);
    private boolean sick;

    public Unit(int x, int y, boolean sick){
        this.position.setX(x);
        this.position.setY(y);
        this.sick = sick;
    }

    public Position getPosition() {
    	return this.position;
    }
    
    public void setPosition(Position position) {
    	this.position.setX(position.getX());
    	this.position.setY(position.getY());
    }

    public boolean isSick() {
        return sick;
    }

    public void setSick(boolean sick) {
        this.sick = sick;
    }
}
