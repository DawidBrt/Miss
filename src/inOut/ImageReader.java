package inOut;

import objects.PointOfInterest;
import objects.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class ImageReader {

    // TODO(1): obiekt wczytujący obraz png (monochromatyczny)
    // TODO: zwrócenie znalezionych PoI (czarne obszary)
    private int width;
    private int height;
    private BufferedImage image = null;

    public ImageReader(String path) {
        try {
            this.image = ImageIO.read(new File(path));
            //System.out.println(image);
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Position checkIfTopLeftCorner(int x, int y, Color myColor) {
        Position corner = null;

        if(!myColor.equals(new Color(image.getRGB(x-1,y)))) {
            if(!myColor.equals(new Color(image.getRGB(x,y-1)))) {
                corner = new Position(x,y);
            }
        }
       return corner;
    }
    private Position checkIfTopRightCorner(int x, int y, Color myColor) {
        Position corner = null;

        if(!myColor.equals(new Color(image.getRGB(x+1,y)))) {
            if(!myColor.equals(new Color(image.getRGB(x,y-1)))) {
                corner = new Position(x,y);
            }
        }
        return corner;
    }
    private Position checkIfBotLeftCorner(int x, int y, Color myColor) {
        Position corner = null;

        if(!myColor.equals(new Color(image.getRGB(x-1,y)))) {
            if(!myColor.equals(new Color(image.getRGB(x,y+1)))) {
                corner = new Position(x,y);
            }
        }
        return corner;
    }
    private Position checkIfBotRightCorner(int x, int y, Color myColor) {
        Position corner = null;

        if(!myColor.equals(new Color(image.getRGB(x+1,y)))) {
            if(!myColor.equals(new Color(image.getRGB(x,y+1)))) {
                corner = new Position(x,y);
            }
        }
        return corner;
    }
    public int checkTillBottom(int[][] tab, int x, int y) {
        int iy = y;
        while (tab[x][iy] == 0) {
            iy++;
        }
        return iy-1;
    }
    public int checkTillRight(int[][] tab, int x, int y) {
        int ix = x;
        while (tab[ix][y] == 0) {
            ix++;
        }
        return ix-1;
    }

    // Zalozenie wstepne : current(obecny piksel jest czarny
    // Zalozenia wstepne : PoI nie są wielkosci 1x1px
    public boolean checkCornerOfNeighbourhood(int[][] tab, int x, int y) {
        int neighSum = tab[x-1][y] + tab[x+1][y] + tab[x][y-1] + tab[x][y+1];
        if (neighSum == 2) return true;
        else return false;
    }

    public List<PointOfInterest> getPoIsFromImage() {

        int[][] bwImage = new int[width][height];
        List<PointOfInterest> pointOfInterests = new ArrayList<>();

        byte BLACK = (byte) 0;
        byte WHITE = (byte) 255;

        if (this.image == null || this.width == 0 || this.height == 0) {
            return null;
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                if (color.equals(new Color(BLACK))) {
                    bwImage[x][y] = 0;
                } else if (color.equals(new Color(WHITE))){
                    bwImage[x][y] = 1;
                }
            }
        }

        int y = 1;
        int x = 1;
        //int y = 49;
        //int x = 47;

        while (y < height) {
            while (x < width) {
                if (bwImage[x][y] == 0) {
                    if (checkCornerOfNeighbourhood(bwImage, x, y)) {
                        int iy = 0;
                        int ix = 0;
                        iy = checkTillBottom(bwImage, x, y);
                        ix = checkTillRight(bwImage, x, y);

                        // Teraz mamy 3 punkty tworzące prostokąt PoI
                        //System.out.println("(x1,y)=(" + x + "," + y + "), (x2,y2)+(" + x + "," + iy + "), (x3,y3)+(" + ix + "," + y + ")");

                        pointOfInterests.add(new PointOfInterest(x, y, abs(x - ix), abs(y - iy)));
                        if (ix == x || iy == y) {
                            pointOfInterests.remove(pointOfInterests.size()-1);
                        }
                        x = ix;
                    }
                }
                x++;
            }
            x=1;
            y++;
        }
        //System.out.println(pointList.size());

        //Zostawiam ku pamieci, kiedys sie posprzata...
       /* for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x,y));
                if (color.equals(new Color(BLACK))) {
                    result[x][y] = 0;

                    if (checkIfBotLeftCorner(x,y,color) != null)
                        positionList.add(checkIfBotLeftCorner(x,y,color));
                    if (checkIfTopLeftCorner(x,y,color) != null)
                        positionList.add(checkIfTopLeftCorner(x,y,color));
                    if (checkIfBotRightCorner(x,y,color) != null)
                        positionList.add(checkIfBotRightCorner(x,y,color));
                    if (checkIfTopRightCorner(x,y,color) != null)
                        positionList.add(checkIfTopRightCorner(x,y,color));

                }
                if (color.equals(new Color(WHITE))) {
                    result[x][y] = 1;
                }
                line = line + result[x][y];
                //System.out.print(result[x][y]);
            }
            imgBinData.getLogLine(line);
            line = "";
            //System.out.println();
        }
        imgBinData.makeLogFile();

        //Collections.sort(positionList);
        ArrayList<objects.PointOfInterest> poiList = new ArrayList<>();

        //W zensie nie wkryto wielkokrotności 4 jako ilości rogów PoI
        if (positionList.size() % 4 != 0) {
            //System.out.println("Incorrect image PoI locations");
            return null;
        } else {
            int[] p0 = new int[2];
            int[] p3 = new int[2];


            for (int i = 0; i < positionList.size(); i++) {
                System.out.println(i +" - x: " + positionList.get(i).getX() +" y:"+ positionList.get(i).getY());
            }
            for (int i = 0; i < positionList.size() / 4; i++) {

                p0[0] = positionList.get(0 + i*4).getX();
                p0[1] = positionList.get(0 + i*4).getY();

                p3[0] = positionList.get(3 + i*4).getX();
                p3[1] = positionList.get(3 + i*4).getY();



                objects.PointOfInterest poi = new objects.PointOfInterest( p0[0], p0[1], abs(p0[0]-p3[0]) ,abs(p0[1]-p3[1]) );
                poiList.add(poi);
            }
        }*/
        return pointOfInterests;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }
}
