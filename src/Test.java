public class Test {
    public static void main(String[] args) {
        int size = 6;
        int x = 200;
        int y = 100;
        int exist = 30;
        int sick = 10;
        Maker m = new Maker(x,y,exist,sick);
        Drawer drawer = new Drawer(m,x,y,size);
        MyFrame myFrame = new MyFrame(drawer);
    }
}