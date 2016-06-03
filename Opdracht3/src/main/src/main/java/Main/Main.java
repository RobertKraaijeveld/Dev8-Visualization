package Main;

import Datastructures.GenericPair;
import Datastructures.Vector3D;
import FileParsing.FileParser;
import FileParsing.ParsedFile;
import Plotting.ValueConverter;
import Utilities.TimeMeasurer;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author gover_000
 */

public class Main extends PApplet
{
    private static AppletMetaData appletMetaData = new AppletMetaData();
    private static GenericPair<Integer, Integer> appletWidthHeightMaximums = new GenericPair<>(1000, 1000);
    private static List<Vector3D> vectorsToBeDrawn;

    @Override
    public void settings()
    {
        size(appletWidthHeightMaximums.getLeftValue(), appletWidthHeightMaximums.getRightValue(), P3D);
    }

    @Override
    public void setup()
    {
    }

    @Override
    public void draw()
    {
        clear();
        lights();

        translate(width / 2, height / 2);
        rotateX(1f);
        rotateZ(appletMetaData.getCameraAngle());

        background(220);

        pushMatrix();
        drawBoxes();
        popMatrix();
    }


    //TODO CHANGE THIS, AND UNDERSTAND IT
    @Override
    public void mouseDragged()
    {
        if (mouseButton == LEFT)
        {
            System.out.println("LEFT");
            appletMetaData.setCameraAngle(appletMetaData.getCameraAngle() + (radians(-(mouseX- pmouseX)) / 2 ));
        }
    }

    //TODO  CHANGE THIS, AND UNDERSTAND IT
    @Override
    public void mouseWheel(MouseEvent event)
    {
        float currentScale = appletMetaData.getCurrentScale();

        float amountOfScroll = event.getCount();
        System.out.println(amountOfScroll);

        appletMetaData.setCurrentScale( (float) (currentScale - (amountOfScroll /5.0)) );

        if (currentScale < 1)
            appletMetaData.setCurrentScale(1);
        else if (currentScale > 3)
            appletMetaData.setCurrentScale(3);
    }

    //CHANGE THIS, AND UNDERSTAND IT
    private void drawBoxes()
    {
        System.out.println("Starting to draw.");
        System.out.println("VECTORSIZE = " + vectorsToBeDrawn.size());
        noStroke();

        for(Vector3D vector : vectorsToBeDrawn)
        {
            float colorTint = this.calculateEllipseTint(vector);
            fill(colorTint);

            pushMatrix();
            //clean this magic number mess
            translate((vector.getX() - 500.0f) * appletMetaData.getCurrentScale(),
                    (1000f - vector.getY() - 500f) * appletMetaData.getCurrentScale(), 0);

            box(2 * appletMetaData.getCurrentScale() , 2 * appletMetaData.getCurrentScale(),
                    (float) (vector.getZ() + 10) * 2 * appletMetaData.getCurrentScale());
            popMatrix();

        }
        System.out.println("Done drawing.");
    }

    private float calculateEllipseTint(Vector3D vector)
    {
        float tintMultiplicant = 3f;
        return (float) (tintMultiplicant * vector.getZ()) + 15;
    }

    /**
    * Main() and
    **/


    private int getUserMapSizeChoice()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome! Please specify how big you want the visualization to be in meters.");
        System.out.println("Note that the center of the visualization will be the 'Destroyed City' statue in Rotterdam.");

        //500 is the default value
        int result = 500;

        try
        {
            String currentLine = scanner.next();

            if(currentLine != null && !currentLine.equals("")) {
                result = Integer.parseInt(currentLine);
            }
            else {
                System.out.println("Please fill in a valid numeric value.");
            }
        }
        catch(Exception e)
        {
            System.out.println("Please fill in a valid numeric value..");
        }
        return result;
    }


    public static void main(String[] args)
    {
        try
        {
            appletMetaData.setCameraAngle(0);
            appletMetaData.setCurrentScale(2);

            Path csvEast = Paths.get("oost.csv");
            int desiredMapRadius = 1000;

            FileParser parser = new FileParser(csvEast, desiredMapRadius);

            TimeMeasurer.startTimer();
            ParsedFile parsedFile = parser.createParsedFileInstance();
            TimeMeasurer.printElapsedTimeInSeconds();

            GenericPair<Vector3D, Vector3D> smallestXYVectors = parsedFile.getVectorsWithSmallestXYs();
            GenericPair<Vector3D, Vector3D> largestXYVectors = parsedFile.getVectorsWithLargestXYs();

            ValueConverter converter = new ValueConverter(parsedFile.getSourceVectors(), smallestXYVectors,
                    largestXYVectors, appletWidthHeightMaximums);

            vectorsToBeDrawn = converter.getAppletPositions();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        PApplet.main(new String[]{Main.class.getName()});
    }
}