package Main;

import Datastructures.GenericPair;
import Datastructures.Vector3D;
import FileParsing.FileParser;
import FileParsing.ParsedFile;
import Plotting.BasicMapPlot;
import Plotting.ValueConverter;
import Utilities.TimeMeasurer;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author gover_000
 */

public class Main
{
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

            GenericPair<Integer, Integer> appletWidthHeight = new GenericPair<>(1000, 800);

            ValueConverter converter = new ValueConverter(parsedFile.getSourceVectors(), smallestXYVectors,
                                                          largestXYVectors, appletWidthHeight);

            BasicMapPlot basicPlot = new BasicMapPlot(converter.transformCoordinatesListToAppletPositions(),
                                                      appletWidthHeight);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
        
}
