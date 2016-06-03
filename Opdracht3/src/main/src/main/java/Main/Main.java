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
    private static GenericPair<Vector3D, Vector3D> smallestXYVectors;
    private static GenericPair<Vector3D, Vector3D> largestXYVectors;
    private static Vector3D largestZVector;

    private static List<Vector3D> vectorsToBeDrawn;


    @Override
    public void settings()
    {
        size(appletWidthHeightMaximums.getLeftValue(), appletWidthHeightMaximums.getRightValue(), P3D);
    }

    @Override
    public void setup(){}

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
        drawWaterBoxes();

        popMatrix();
    }


    /**
     * Controls
     **/


    @Override
    public void mouseDragged()
    {
        if (mouseButton == LEFT)
        {
            //pMouseX == previous frame mouse position
            //what does - do
            float radiansToMoveBy = radians( -(mouseX - pmouseX));
            appletMetaData.setCameraAngle(appletMetaData.getCameraAngle() + (radiansToMoveBy / 2));
        }
    }


    @Override
    public void mouseWheel(MouseEvent event)
    {
        float currentScale = appletMetaData.getCurrentScale();
        float maximumLevelOfZoom = 0.5f;
        float minimumLevelOfZoom = 3.5f;

        float amountOfScroll = event.getCount();

        System.out.println(amountOfScroll);

        //Diving by 5 in order to make the scroll-steps smaller
        float newScroll = currentScale - (amountOfScroll / 5);
        appletMetaData.setCurrentScale(newScroll);


        if (currentScale < minimumLevelOfZoom) {
            appletMetaData.setCurrentScale(minimumLevelOfZoom);
        }
        else if (currentScale > maximumLevelOfZoom) {
            appletMetaData.setCurrentScale(maximumLevelOfZoom);
        }
    }

    /**
     * Drawing
     **/

    /*
    public void drawWaterHeight() {
        if (!pauseStatus && waterHeight <= maxValueZ) {
            waterHeight+=0.2f;
            timePassed+=0.4f;
        }
        fill(0, 0 , 255, 96);
        pushMatrix();
        translate(0, 0, 0);
        box(1000 * scale, 1000 * scale, (float) (waterHeight + 10) * 2 * scale);
        popMatrix();
    }
    */

    private void drawWaterBoxes()
    {
        float tallestVectorHeight = largestZVector.getZ();
        float currentWaterHeight = appletMetaData.getCurrentWaterHeight();
        float elapsedHours = appletMetaData.getHoursPassed();

        if(appletMetaData.isPaused() == false)
        {
            //Is this really half a meter?
            appletMetaData.setCurrentWaterHeight(currentWaterHeight + 0.15f);
            //Do we really want to count hours?
            appletMetaData.setHoursPassed(elapsedHours + 1);
        }
        else if(appletMetaData.getCurrentWaterHeight() < tallestVectorHeight)
        {
            //TODO
        }

        fill(0, 0, 255, 95);

        pushMatrix();
        //We do this to ensure that the water rises evenly across the whole map
        translate(0,0,0);

        //TODO what are these magic numbers?
        float waterBoxZ = (currentWaterHeight + 10) * (2 * appletMetaData.getCurrentScale());
        box(1000 * appletMetaData.getCurrentScale(), 1000 * appletMetaData.getCurrentScale(), waterBoxZ);

        popMatrix();
    }

    //TODO CHANGE THIS, AND UNDERSTAND IT
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
        return tintMultiplicant * vector.getZ() + 15;
    }


    /**
    * Main() and getting users choice
    **/


    private static int getUserMapSizeChoice()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please specify how big you want the visualization to be in meters.");
        System.out.println("Note that the center of the visualization will be the 'Destroyed City' statue in Rotterdam.");

        //500m radius is the default value
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
            getUserMapSizeChoice();
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
            int desiredMapRadius = getUserMapSizeChoice();

            FileParser parser = new FileParser(csvEast, desiredMapRadius);

            TimeMeasurer.startTimer();
            ParsedFile parsedFile = parser.createParsedFileInstance();
            TimeMeasurer.printElapsedTimeInSeconds();

            smallestXYVectors = parsedFile.getVectorsWithSmallestXYs();
            largestXYVectors = parsedFile.getVectorsWithLargestXYs();
            largestZVector = parsedFile.getVectorsWithLargestZ();

            ValueConverter converter = new ValueConverter(parsedFile.getSourceVectors(),
                                            smallestXYVectors, largestXYVectors, appletWidthHeightMaximums);

            vectorsToBeDrawn = converter.getAppletPositions();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        PApplet.main(new String[]{Main.class.getName()});
    }
}