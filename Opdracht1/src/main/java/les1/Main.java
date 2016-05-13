package les1;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Main extends PApplet
{
    private static PImage icelandImage;
    private static ArrayList<Earthquake> earthquakeList = new ArrayList<Earthquake>();

    public void settings() {
        size(640, 480);
        noSmooth();
    }

    public void setup()
    {
        stroke(0);
        noLoop();
        icelandImage = loadImage("MapOfIceland.png");
    }

    public void draw()
    {
        background(1);
        image(icelandImage, 0, 0, 640, 480);

        drawEarthquakeDots();
        drawLegend();
    }

    public void drawLegend()
    {
        fill(153);
        rect(0,0, 180, 120);
        fill(0);
        text("LEGEND", 5,10);
        text("Source: https://apis.is", 5, 25);
        text("Richter scale:", 5, 45);
        text("The larger the circle,", 5, 55);
        text("The larger the richter scale.", 5, 65);

        text("Depth:",5,85);
        text("Color ranges from black to red;", 5, 95);
        text("The redder, the deeper.", 5,105);
    }

    public void drawTestDot()
    {
        Earthquake eTest = new Earthquake(64.0f, -14.0f, 1, 10);
        ArrayList<Earthquake> eTestList = new ArrayList<Earthquake>();
        eTestList.add(eTest);
        eTestList = CoordinateToAxisConverter.calculateXYValuesForEarthquakesInList(eTestList);

        for(Earthquake eT : eTestList)
        {
            System.out.println("TEST Lat: " + eT.getlatLongPair().getLeftValue() + " long: " + eT.getlatLongPair().getRightValue() + " depth: " + eT.getDepth());
            float ellipseSizeRelativeToRichterScale = Utilities.convertRichterScaleToElipseSize(eT.getRichterScale());
            ellipse(eT.getConvertedCoordinatePair().getxValue(), eT.getConvertedCoordinatePair().getyValue(), ellipseSizeRelativeToRichterScale,ellipseSizeRelativeToRichterScale);

            text("(Calibration test)", 526.66f, 343.24f);
            fill(255, 13, 0);
            noStroke();
        }
    }

    public void drawEarthquakeDots()
    {
        earthquakeList = CoordinateToAxisConverter.calculateXYValuesForEarthquakesInList(earthquakeList);

        for(Earthquake e : earthquakeList)
        {
            System.out.println("Lat: " + e.getlatLongPair().getLeftValue() + " long: " + e.getlatLongPair().getRightValue() + " depth: " + e.getDepth());

            float ellipseSizeRelativeToRichterScale = Utilities.convertRichterScaleToElipseSize(e.getRichterScale());
            ellipse(e.getConvertedCoordinatePair().getxValue(), e.getConvertedCoordinatePair().getyValue(), ellipseSizeRelativeToRichterScale, ellipseSizeRelativeToRichterScale);
            fill(Utilities.convertDepthToColor(e.getDepth()), 13, 0);
            noStroke();
        }
        drawTestDot();
    }

    public static void main(String[] args)
    {
        earthquakeList = CoordinateToAxisConverter.getEarthquakeArrayList();
        PApplet.main(new String[] { Main.class.getName() });
    }
}
