package simulation;

import GUI.*;
import inOut.*;
import objects.PointOfInterest;
import objects.TheUnits;

import java.util.List;

public class Simulation {
    private int size = 3; // wielkosc "piksela"+1
    private int width = 300; // szerokosc okna
    private int height = 200; // wysokosc okna
    private int exist = 5; // % jednostek na mapie
    private int sick = 5; // % chorych z zyjacych jednostek
    private int infected = 2; // % infekujacy jeszcze nie chorych
    private int immune = 5; // % odpornych na chorobe
    private int toInfect = 2; // szansa na zainfekowanie w %

    private int[] initialParameters = {exist, sick, infected, immune, toInfect};
    private boolean simulate = false;

    private String imgPath; //sciezka obrazka
    private String logPath; //sciezka pliku do zapisu przebiegu symulacji

    // logger
    private Log logger;

    // obsługa obrazu
    private ImageReader image;

    private List<PointOfInterest> pointOfInterests;
    private TheUnits theUnits;

    // obiekty do rysowania
    // mapa
    private Map map;

    // panel GUI
    private MenuPanel menu = new MenuPanel(width, height, this, initialParameters);

    //ramki
    private MyFrame myFrame;
    private MyFrame myFrame2;

    // separator dla wejść loga
    private String separator = " | ";

    public Simulation(String logPath, String imgPath){
        this.imgPath = imgPath;
        this.logPath = logPath;
        this.logger = new Log(this.logPath);
        this.image = new ImageReader(this.imgPath);
        pointOfInterests = image.getPoIsFromImage();
        theUnits = new TheUnits(image.getWidth(), image.getHeight(), initialParameters, pointOfInterests);
        map = new Map(theUnits, image.getWidth(), image.getHeight(), size, pointOfInterests);
        myFrame = new MyFrame(map);
        myFrame2 = new MyFrame(menu);
    }

    public void startNewSimulation() {
        this.simulate = false;
        this.theUnits = new TheUnits(image.getWidth(), image.getHeight(), initialParameters, image.getPoIsFromImage());
        this.map = new Map(theUnits, width, height, size, image.getPoIsFromImage());
        this.logger = new Log(logPath);
        myFrame.updateFrame(map);
    }

    public void resumeSimulation() {
        this.simulate = true;
    }

    public void stopSimulation() {
        this.simulate = false;
    }

    public void setParameters(int[] parameters) {
        this.initialParameters = parameters;
    }

    // petla symulacji
    public void simualtion() {
        // while (unitsC > immuneC) {
        while (true) {
            System.out.print("");
            if (simulate) {
                //zapisanie informacji do pliku raz "dziennie"
                if(theUnits.getTimeInDayCounter() == 1){
                    System.out.println("Day: " + theUnits.getDayCounter());
                    logger.getLogLine("" + theUnits.getDayCounter() + separator + theUnits.countHealthy() + separator + theUnits.countCarriers() + separator
                        + (theUnits.countInfected() - theUnits.countCarriers()) + separator + theUnits.countImmune());
                    logger.makeLogFile();
                }
                //spowolnienie wyswietlania symulacji
/*
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                //ruch jednostek
                theUnits.makeMove();
                map.repaint();
                /*try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                //infekowanie jednostek
                theUnits.infect();
                map.repaint();

            }
        }
    }
}