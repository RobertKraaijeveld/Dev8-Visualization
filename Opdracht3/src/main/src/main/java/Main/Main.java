package Main;

import Datastructures.GenericPair;
import Datastructures.Vector3D;
import FileParsing.FileParser;
import FileParsing.ParsedFile;
import Plotting.ValueConverter;
import Utilities.TimeMeasurer;
import processing.core.PApplet;

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
    private static Vector3D ZADKINE_STATUE_VECTOR = new Vector3D(92800f, 436955f, 4f);

    private static GenericPair<Integer, Integer> appletWidthHeightMaximums = new GenericPair<>(1000, 1000);
    private static GenericPair<Vector3D, Vector3D> smallestXYVectors;
    private static GenericPair<Vector3D, Vector3D> largestXYVectors;
    private static GenericPair<Vector3D, Vector3D> smallestAndLargestZVectors;

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

        drawLegend();
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
            float radiansToMoveBy = radians( -(mouseX - pmouseX));
            appletMetaData.setCameraAngle(appletMetaData.getCameraAngle() + (radiansToMoveBy / 2));
        }
    }



    @Override
    public void keyPressed()
    {
        if(key == 'z')
        {
            increaseZoomLevel();
        }
        else if(key == 'p')
        {
            pauseOrUnpause();
        }
        else if(key == 'r')
        {
            resetSimulation();
        }
    }

    private void increaseZoomLevel()
    {
        System.out.println("Zoomlevel: " + appletMetaData.getCurrentZoomLevelIndex());

        float[] possibleZooms = appletMetaData.getZoomLevels();
        int currentZoomLevelIndex = appletMetaData.getCurrentZoomLevelIndex();

        //There are 4 zoomlevels total, cycling beyond the last one will reset the zoomlevel to the first.
        if(currentZoomLevelIndex < 4)
        {
            appletMetaData.setCurrentScale(possibleZooms[currentZoomLevelIndex]);
            appletMetaData.setCurrentZoomLevelIndex(currentZoomLevelIndex += 1);
        }
        else
        {
            appletMetaData.setCurrentScale(possibleZooms[0]);
            appletMetaData.setCurrentZoomLevelIndex(0);
        }
    }

    private void pauseOrUnpause()
    {
        if(appletMetaData.isPaused())
            appletMetaData.setPaused(false);
        else
            appletMetaData.setPaused(true);
    }

    private void resetSimulation()
    {
        appletMetaData.setHoursPassed(0);
        appletMetaData.setCurrentWaterHeight(-4);
    }


    /**
     * Drawing
     **/

    private void drawLegend()
    {
        fill(255, 0, 0);

        hint(DISABLE_DEPTH_TEST);
        camera();
        noLights();

        text("Hold left mouse button and drag to rotate the map", 20, 30);

        text("Press Z to cycle through the zoom-levels.", 20, 50);
        text("Press P to pause/start the simulation", 20, 70);
        text("Press R to reset the simulation", 20, 90);

        text("Paused: " + appletMetaData.isPaused(), 20, 110);

        text("Water height: " + appletMetaData.getCurrentWaterHeight() + "m", 800, 30);
        text("Time passed: " + appletMetaData.getHoursPassed() + " hours", 800, 50);

        hint(ENABLE_DEPTH_TEST);
    }


    private void drawWaterBoxes()
    {
        float tallestVectorHeight = smallestAndLargestZVectors.getRightValue().getZ();
        float currentWaterHeight = appletMetaData.getCurrentWaterHeight();
        float elapsedHours = appletMetaData.getHoursPassed();

        if(appletMetaData.isPaused() == false)
        {
            //TODO Is this really half a meter?
            appletMetaData.setCurrentWaterHeight(currentWaterHeight + 0.15f);
            //TODO Do we really want to count hours?
            appletMetaData.setHoursPassed(elapsedHours + 1);
        }
        else if(appletMetaData.getCurrentWaterHeight() >= tallestVectorHeight)
        {
            appletMetaData.setPaused(true);
        }

        fill(0, 0, 255, 95);

        pushMatrix();

        //We do this to ensure that the water rises evenly across the whole map
        translate(0,0,0);

        float basicWaterBoxHeight = (currentWaterHeight + 10) * 2;
        float waterBoxZ = basicWaterBoxHeight * appletMetaData.getCurrentScale();

        box(1000 * appletMetaData.getCurrentScale(), 1000 * appletMetaData.getCurrentScale(), waterBoxZ);

        popMatrix();
    }


    private void drawBoxes()
    {
        System.out.println("Starting to draw.");
        noStroke();

        for(Vector3D vector : vectorsToBeDrawn)
        {
            float colorTint = this.calculateEllipseTint(vector);
            fill(colorTint);

            pushMatrix();

            //This is done to ensure the water surface beneath the map and the map itself are equal.
            //Remeber that X and Y are both still 2D.
            float xOffset = vector.getX() -500f;
            float yOffSet = 1000f - vector.getY() - 500f;

            translate((xOffset) * appletMetaData.getCurrentScale(),
                    (yOffSet) * appletMetaData.getCurrentScale(), 0);

            float baseBoxHeight = (vector.getZ() + 10) * 2;

            box(2 * appletMetaData.getCurrentScale() , 2 * appletMetaData.getCurrentScale(),
                baseBoxHeight * appletMetaData.getCurrentScale());

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

        System.out.println("Please specify how big you want the visualization to be in meters. Above 500, the FPS loss gets annoying.");
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
            //This angle ensures that the camera is focused on the zadkine statue.
            appletMetaData.setCameraAngle(-3.577925f);
            appletMetaData.setCurrentScale(2);

            Path csvEast = Paths.get("oost.csv");
            int desiredMapRadius = getUserMapSizeChoice();

            FileParser parser = new FileParser(csvEast, ZADKINE_STATUE_VECTOR, desiredMapRadius);

            TimeMeasurer.startTimer();
            ParsedFile parsedFile = parser.createParsedFileInstance();
            TimeMeasurer.printElapsedTimeInSeconds();

            smallestXYVectors = parsedFile.getVectorsWithSmallestXYs();
            largestXYVectors = parsedFile.getVectorsWithLargestXYs();
            smallestAndLargestZVectors = parsedFile.getSmallestAndLargestZvectors();

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