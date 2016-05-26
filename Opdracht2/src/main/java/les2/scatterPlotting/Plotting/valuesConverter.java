package les2.scatterPlotting.Plotting;

import les2.scatterPlotting.Main.GenericPair;
import les2.scatterPlotting.textReading.TextFile;
import java.util.ArrayList;

import static processing.core.PApplet.map;

/**
 *
 * @author gover_000
 */
public class valuesConverter
{
    private ScatterPlotMetaData metaData;
    private TextFile valuesFile;

    public valuesConverter(TextFile valuesFile,
                           ScatterPlotMetaData metaData)
    {
        this.valuesFile = valuesFile;
        this.metaData = metaData;
    }

    public ArrayList<Point> convertValuesToPoints()
    {
        ArrayList<Point> returnList = new ArrayList<>();

        //Ooeh, got a little fuuunctional there
        this.valuesFile.getLines()
                .forEach((pair) -> {
                    Point p = new Point(pair.getLeftValue(),pair.getRightValue());
                    mapValueToPositionOnAxis(p);
                    returnList.add(p);
                });

        return returnList;
    }

    public void mapValueToPositionOnAxis(Point p)
    {
        Float plotHeight = this.metaData.getWidthHeightPair().getLeftValue();
        Float plotWidth = this.metaData.getWidthHeightPair().getRightValue();

        Float xPosition = map(p.getX(), valuesFile.getSmallestOrBiggestValue("X", "small"),
                              valuesFile.getSmallestOrBiggestValue("X", "big"),
                              0, plotWidth);

        Float yPosition = map(p.getY(), valuesFile.getSmallestOrBiggestValue("Y", "small"),
                              valuesFile.getSmallestOrBiggestValue("Y", "big"),
                              0, plotHeight);

        System.out.println("Converted " + p.getX() + ", " + p.getY() + "to: " + xPosition + "," + yPosition);

        p.setX(xPosition);
        p.setY(yPosition);
    }
}
