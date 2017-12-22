import javafx.geometry.Pos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

    public ThePoI getPoIsFromImage() {

        int[][] result = new int[height][width];
        ArrayList<Position> positionList = new ArrayList<>();

        byte BLACK = (byte) 0;
        byte WHITE = (byte) 255;

        if (this.image == null || this.width == 0 || this.height == 0) {
            return null;
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                if (color.equals(new Color(BLACK))) {
                    result[y][x] = 0;

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
                    result[y][x] = 1;
                }
            }
        }
        Collections.sort(positionList);
        ArrayList<PointOfInterest> poiList = new ArrayList<>();

        if (positionList.size() % 4 != 0) {
            System.out.println("Incorrect image PoI locations");
            return null;
        } else {
            int[] p0 = new int[2];
            int[] p3 = new int[2];

            for (int i = 0; i < positionList.size() / 4; i++) {

                p0[0] = positionList.get(0 + i*4).getX();
                p0[1] = positionList.get(0 + i*4).getY();

                p3[0] = positionList.get(3 + i*4).getX();
                p3[1] = positionList.get(3 + i*4).getY();

                PointOfInterest poi = new PointOfInterest( p0[0], p0[1], abs(p0[0]-p3[0]) ,abs(p0[1]-p3[1]) );
                poiList.add(poi);
            }
        }
        ThePoI thePoI = new ThePoI();
        thePoI.fromPoIList(poiList);
        return thePoI;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }
}
