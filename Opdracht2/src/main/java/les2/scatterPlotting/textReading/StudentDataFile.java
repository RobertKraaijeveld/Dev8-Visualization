package les2.scatterPlotting.textReading;

import les2.scatterPlotting.Main.GenericPair;

import java.util.ArrayList;

/**
 * Created by Kraaijeveld on 27-5-2016.
 */

//This is the class for the bigger, multiple value file.
public class StudentDataFile
{
    private ArrayList<GenericPair<String, Float>> studentDataValuesAndTheirTypes;

    public StudentDataFile(ArrayList<GenericPair<String, Float>> studentDataValuesAndTheirTypes)
    {
        this.studentDataValuesAndTheirTypes = studentDataValuesAndTheirTypes;
    }

    public Float getSmallestOrBiggestValue(String xOrY, String typeOfValue, String BigOrSmall)
    {
        ArrayList<Float> Values = new ArrayList<>();

        if(xOrY.equals("X"))
            Values = this.createXvaluesList(typeOfValue);
        else
            Values = this.createYvaluesList(typeOfValue);

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
    private ArrayList<Float> createXvaluesList(String typeOfValueToSearchFor)
    {
        ArrayList<Float> xValues = new ArrayList<>();
        this.studentDataValuesAndTheirTypes.forEach(
                (pair) -> {
                    if(pair.getLeftValue().equals(typeOfValueToSearchFor))
                    {
                        Float value = pair.getRightValue();
                        xValues.add(value);
                    }
                }
        );
        return xValues;
    }

    private ArrayList<Float> createYvaluesList(String typeOfValueToSearchFor)
    {
        ArrayList<Float> yValues = new ArrayList<>();
        this.studentDataValuesAndTheirTypes.forEach(
                (pair) -> {
                    if(pair.getLeftValue().equals(typeOfValueToSearchFor))
                    {
                        Float value = pair.getRightValue();
                        yValues.add(value);
                    }
                }
        );
        return yValues;
    }

}
