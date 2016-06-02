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
        drawBoxes();
    }

    //CHANGE THIS, AND UNDERSTAND IT
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
        noStroke();

        for(Vector3D vector : vectorsToBeDrawn)
        {
            float colorTint = this.calculateEllipseTint(vector);
            fill(colorTint, colorTint, colorTint);

            //ellipse(vector.getX(), vector.getY(), 0.5f, 0.5f);

            pushMatrix();

            //clean this magic number mess
            translate((vector.getX() - 500.0f) * appletMetaData.getCurrentScale(),
                    (1000f - vector.getY() - 500f) * appletMetaData.getCurrentScale(), 0);

            box(appletMetaData.getCurrentScale() * 2, appletMetaData.getCurrentScale()* 2,
                (vector.getZ() + 2) * 2 * appletMetaData.getCurrentScale());

            popMatrix();

        }
        System.out.println("Done drawing.");
    }

    private float calculateEllipseTint(Vector3D vector)
    {
        float tintMultiplicant = 3f;
        return (float) (tintMultiplicant * vector.getZ()) + 15;
    }


    public static void main(String[] args)
    {
        try
        {
            appletMetaData.setCameraAngle(0);
            appletMetaData.setCurrentScale(2);

            Path csvEast = Paths.get("oost.csv");
            FileParser parser = new FileParser(csvEast);

            TimeMeasurer.startTimer();
            ParsedFile parsedFile = parser.createParsedFileInstance();
            TimeMeasurer.printElapsedTimeInSeconds();

            GenericPair<Vector3D, Vector3D> smallestXYVectors = parsedFile.getVectorsWithSmallestXYs();
            GenericPair<Vector3D, Vector3D> largestXYVectors = parsedFile.getVectorsWithLargestXYs();

            ValueConverter converter = new ValueConverter(parsedFile.getSourceVectors(), smallestXYVectors,
                                                          largestXYVectors, appletWidthHeightMaximums);

            vectorsToBeDrawn = converter.getAppletPositionsForFullMap();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        PApplet.main(new String[]{Main.class.getName()});
    }
}
