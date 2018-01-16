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

    private int width;
    private int height;
    private BufferedImage image = null;

    public ImageReader(String path) {
        try {
            this.image = ImageIO.read(new File(path));
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

        while (y < height) {
            while (x < width) {
                if (bwImage[x][y] == 0) {
                    if (checkCornerOfNeighbourhood(bwImage, x, y)) {
                        int iy = 0;
                        int ix = 0;
                        iy = checkTillBottom(bwImage, x, y);
                        ix = checkTillRight(bwImage, x, y);

                        // Teraz mamy 3 punkty tworzące prostokąt PoI

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
        return pointOfInterests;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }
}
