package les2.scatterPlotting.textReading;

import les2.scatterPlotting.Main.GenericPair;

import java.util.ArrayList;

/**
 * Created by Kraaijeveld on 27-5-2016.
 */
public class StudentDataFile
{
    private ArrayList<GenericPair<String, Float>> studentDataValuesAndTheirTypes;

    public StudentDataFile(ArrayList<GenericPair<String, Float>> studentDataValuesAndTheirTypes)
    {
        this.studentDataValuesAndTheirTypes = studentDataValuesAndTheirTypes;
    }

    public Float getSmallestOrBiggestValue(String xOrY, String BigOrSmall)
    {
        ArrayList<Float> Values = new ArrayList<>();

        if(xOrY.equals("X"))
            Values = this.createXvaluesList();
        else
            Values = this.createYvaluesList();

        /*
        TRIED THIS: DIDMT WORK
        IntStream.range(0,xValues.size()).boxed()
                .min(comparingInt(xValues::get))
                .orElse(-1);
        */

        Float BiggestOrSmallestNumberYet = Values.get(0);

        for(Float value : Values)
        {
            if((BigOrSmall.equals("small") && value < BiggestOrSmallestNumberYet)
                    || (BigOrSmall.equals("big") && value > BiggestOrSmallestNumberYet))
                BiggestOrSmallestNumberYet = value;

        }
        return BiggestOrSmallestNumberYet;
    }

    //horrid duplication ahead, hold your noses
    private ArrayList<Float> createXvaluesList()
    {
        ArrayList<Float> xValues = new ArrayList<>();
        this.XYfloats.forEach(
                (item) -> {
                    Float d = item.getLeftValue();
                    xValues.add(d);
                }
        );
        return xValues;
    }

    private ArrayList<Float> createYvaluesList()
    {
        ArrayList<Float> yValues = new ArrayList<>();
        this.XYfloats.forEach(
                (item) -> {
                    Float d = item.getRightValue();
                    yValues.add(d);
                }
        );
        return yValues;
    }

}
