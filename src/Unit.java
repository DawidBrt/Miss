public class Unit {
    private int x;
    private int y;
    private boolean sick;

    public Unit(int x, int y, boolean sick){
        this.x = x;
        this.y = y;
        this.sick = sick;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSick() {
        return sick;
    }

    public void setSick(boolean sick) {
        this.sick = sick;
    }
}
