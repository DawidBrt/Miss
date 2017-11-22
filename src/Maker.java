import java.util.*;

public class Maker {
    public List<Unit> units = new ArrayList<>();

    public Maker(int x, int y, int rand, int sick){
        Random generator = new Random();
        int exist;
        int sicked;
        for(int i =0;i<x;i++){
            for(int j=0;j<y;j++){
                exist = generator.nextInt(100);
                if(exist<(rand-1)){
                    Unit unit;
                    sicked = generator.nextInt(100);
                    if(sicked > (sick-1)) {
                        unit = new Unit(i, j, false);
                    } else
                    {
                        unit = new Unit(i,j, true);
                    }
                    units.add(unit);
                }
            }
        }
    }

}
