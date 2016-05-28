package les2.scatterPlotting.Plotting;

import les2.scatterPlotting.Main.GenericPair;
import les2.scatterPlotting.textReading.TextFile;
import java.util.ArrayList;
import les2.scatterPlotting.textReading.StudentDataFile;

import static processing.core.PApplet.map;

/**
 *
 * @author gover_000
 */
public class ValuesConverter
{
    private ScatterPlotMetaData metaData;
    private TextFile valuesFile;
    private StudentDataFile studentFile;

    public ValuesConverter(TextFile valuesFile,
                           ScatterPlotMetaData metaData)
    {
        this.valuesFile = valuesFile;
        this.metaData = metaData;
    }
    
    public ValuesConverter(StudentDataFile studentFile,
                           ScatterPlotMetaData metaData)
    {
        this.studentFile = studentFile;
        this.metaData = metaData;
    }

    public ArrayList<Point> convertValuesToPoints(ArrayList<GenericPair<Float, Float>> values)
    {
        ArrayList<Point> returnList = new ArrayList<>();

        //Ooeh, got a little fuuunctional there
        values.forEach((pair) -> {
                    Point p = new Point(pair.getLeftValue(),pair.getRightValue());
                    mapValueToPositionOnAxis(p);
                    returnList.add(p);
                });

        return returnList;
    }

    private void mapValueToPositionOnAxis(Point p)
    {
        Float plotHeight = this.metaData.getWidthHeightPair().getLeftValue();
        Float plotWidth = this.metaData.getWidthHeightPair().getRightValue();

        Float smallestXvalue;
        Float largestXvalue;
        Float smallestYvalue;
        Float largestYvalue;

        if(this.metaData.getGenericPairOfXYValuesIfSet() != null)
        {
            String typeOfX = this.metaData.getGenericPairOfXYValuesIfSet().getLeftValue();
            smallestXvalue = this.studentFile.getSmallestOrBiggestValue("X", typeOfX, "small");
            largestXvalue = this.studentFile.getSmallestOrBiggestValue("X", typeOfX, "big");

            String typeOfY = this.metaData.getGenericPairOfXYValuesIfSet().getRightValue();
            smallestYvalue = this.studentFile.getSmallestOrBiggestValue("Y", typeOfY, "small");
            largestYvalue = this.studentFile.getSmallestOrBiggestValue("Y", typeOfY, "big");
        }
        else
        {
            smallestXvalue =  this.valuesFile.getSmallestOrBiggestValue("X", "small");
            largestXvalue = this.valuesFile.getSmallestOrBiggestValue("X", "big");

            smallestYvalue = this.valuesFile.getSmallestOrBiggestValue("Y", "small");
            largestYvalue = this.valuesFile.getSmallestOrBiggestValue("Y", "big");
        }

        Float xPosition = map(p.getX(), smallestXvalue, largestXvalue, 0, plotWidth);
        Float yPosition = map(p.getY(), largestYvalue, smallestYvalue, 0, plotHeight);

        System.out.println("Converted " + p.getX() + ", " + p.getY() + "to: " + xPosition + "," + yPosition);

        p.setX(xPosition);
        p.setY(yPosition);
    }
}
