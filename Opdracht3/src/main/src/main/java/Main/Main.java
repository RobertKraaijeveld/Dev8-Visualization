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

/**
 *
 * @author gover_000
 */

public class Main extends PApplet
{
    private static List<Vector3D> vectorsToBeDrawn;

    @Override
    public void settings()
    {
        noSmooth();
        noLoop();

        size(1000, 800);
    }

    @Override
    public void setup()
    {
    }

    @Override
    public void draw()
    {
        background(220);
        noFill();
        drawEllipses();
    }

    public void drawEllipses()
    {
        System.out.println("Starting to draw.");

        for(Vector3D vector : vectorsToBeDrawn)
        {
            int brightnessToUse = this.calculateEllipseBrightness(vector);

            ellipse(vector.getX(), vector.getY(), 1,1);
        }
        System.out.println("Done drawing.");
    }

    private int calculateEllipseBrightness(Vector3D vector)
    {
        return 5 + ((int) vector.getZ() * 10);
    }


    public static void main(String[] args)
    {
        try
        {
            Path csvEast = Paths.get("oost.csv");
            FileParser parser = new FileParser(csvEast);

            TimeMeasurer.startTimer();
            ParsedFile parsedFile = parser.createParsedFileInstance();
            TimeMeasurer.printElapsedTimeInSeconds();

            GenericPair<Vector3D, Vector3D> smallestXYVectors = parsedFile.getVectorsWithSmallestXYs();
            GenericPair<Vector3D, Vector3D> largestXYVectors = parsedFile.getVectorsWithLargestXYs();

            GenericPair<Integer, Integer> appletWidthHeightMaximums = new GenericPair<>(1000, 800);

            ValueConverter converter = new ValueConverter(parsedFile.getSourceVectors(), smallestXYVectors,
                                                          largestXYVectors, appletWidthHeightMaximums);

            vectorsToBeDrawn = converter.transformCoordinatesListToAppletPositions();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        PApplet.main(new String[]{Main.class.getName()});
    }
}
