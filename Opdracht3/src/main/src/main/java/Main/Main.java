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

        translate(appletWidthHeightMaximums.getLeftValue() / 2, appletWidthHeightMaximums.getRightValue() / 2);

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
        else if(key == 's')
        {
            saveScreenShot();
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

    private void saveScreenShot()
    {
        saveFrame("FloodingVisualization-##.jpg");
        System.out.println("Saved screenshot succesfully!");
    }


    /**
     * Drawing
     **/

    private void drawLegend()
    {
        fill(255, 0, 0);

        //This function disables the Z-axis until ENABLE_DEPTH_TEST is called, so we can draw the legend in 2D.
        hint(DISABLE_DEPTH_TEST);
        camera();
        noLights();

        text("Hold left mouse button and drag to rotate the map", 20, 30);

        text("Press Z to cycle through the zoom-levels.", 20, 50);
        text("Press P to pause/start the simulation", 20, 70);
        text("Press R to reset the simulation", 20, 90);
        text("Press S to save a screenshot of the simulation", 20, 110);

        text("Paused: " + appletMetaData.isPaused(), 20, 110);

        text("Water height: " + appletMetaData.getCurrentWaterHeight() + "m", 800, 30);
        text("Time passed: " + appletMetaData.getHoursPassed() + " hours", 800, 50);

        hint(ENABLE_DEPTH_TEST);
    }


    private void drawWaterBoxes()
    {
        if(appletMetaData.isPaused() == false)
        {
            increaseWaterHeightValue();
        }

        float currentWaterHeight = appletMetaData.getCurrentWaterHeight();

        //Fill these boxes with the color blue.
        fill(0, 0, 255, 95);


        //Ensure that the water rises evenly across the whole map
        pushMatrix();
        translate(0, 0, 0);

        float basicWaterBoxHeight = (currentWaterHeight + 10) * 2;
        float waterBoxZ = basicWaterBoxHeight * appletMetaData.getCurrentScale();

        box(1000 * appletMetaData.getCurrentScale(), 1000 * appletMetaData.getCurrentScale(), waterBoxZ);
        popMatrix();
    }

    private void increaseWaterHeightValue()
    {
        float currentWaterHeight = appletMetaData.getCurrentWaterHeight();
        float waterHeightIncrease = appletMetaData.getWaterHeightIncreasePerHour();
        float elapsedHours = appletMetaData.getHoursPassed();

        appletMetaData.setCurrentWaterHeight(currentWaterHeight + waterHeightIncrease);
        appletMetaData.setHoursPassed(elapsedHours + 1);


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

    //TODO: Make these methods cleaner
    private static int askUserForMapSizeInput()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please specify how big you want the visualization to be in meters. Above 500, the FPS loss gets annoying.");
        System.out.println("Note that the center of the visualization will be the 'Destroyed City' statue in Rotterdam.");

        //500m radius is the default setting
        Integer radiusInput = 500;

        try
        {
            String currentLine = scanner.next();

            if(currentLine != null && !currentLine.equals(""))
            {
                radiusInput = Integer.parseInt(currentLine);
            }
            else
            {
                System.out.println("Please fill in a valid numeric value.");
            }
        }
        catch(NumberFormatException e)
        {
            askUserForMapSizeInput();
        }

        if(radiusInput > 0)
            return radiusInput;
        else
            return askUserForMapSizeInput();
    }

    private static float askUserForWaterRiseInput()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please specify by how much meters you want the water to rise per hour.");

        //500m radius is the default setting
        Float waterRiseInput = 0.15f;

        try
        {
            String currentLine = scanner.next();

            if(currentLine != null && !currentLine.equals(""))
            {
                waterRiseInput = Float.parseFloat(currentLine);
            }
            else
            {
                System.out.println("Please fill in a valid numeric value.");
            }
        }
        catch(NumberFormatException e)
        {
            askUserForMapSizeInput();
        }

        if(waterRiseInput > 0)
            return waterRiseInput;
        else
            return askUserForMapSizeInput();
    }


    public static void main(String[] args)
    {
        try
        {
            Path csvEast = Paths.get("oost.csv");
            int desiredMapRadius = askUserForMapSizeInput();
            float desiredWaterRise = askUserForWaterRiseInput();

            //This angle ensures that the camera is focused on the zadkine statue.
            appletMetaData.setCameraAngle(-3.577925f);
            appletMetaData.setCurrentScale(2);
            appletMetaData.setWaterHeightIncreasePerHour(desiredWaterRise);

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