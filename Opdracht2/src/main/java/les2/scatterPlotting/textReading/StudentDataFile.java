package les2.scatterPlotting.textReading;

import les2.scatterPlotting.Main.GenericPair;

import java.util.ArrayList;
import les2.scatterPlotting.Plotting.Point;

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
    
    public ArrayList<GenericPair<Float, Float>> createXYPairsOfGivenTypes(String xType, String yType)
    {
        ArrayList<Float> xTypeMatchesList = new ArrayList<>();
        ArrayList<Float> yTypeMatchesList = new ArrayList<>();
        ArrayList<GenericPair<Float, Float>> resultList = new ArrayList<>();
        
        for(GenericPair<String, Float> pair : this.studentDataValuesAndTheirTypes)
        {
            if(pair.getLeftValue().equals(xType))
                xTypeMatchesList.add(pair.getRightValue());
            else if(pair.getLeftValue().equals(yType))
                yTypeMatchesList.add(pair.getRightValue());
        }
        
        for(int i = 0; i < xTypeMatchesList.size(); i++)
        {
            GenericPair<Float, Float> pairToBeAdded;
            pairToBeAdded = new GenericPair<>(xTypeMatchesList.get(i), yTypeMatchesList.get(i));
            resultList.add(pairToBeAdded);
        }
        return resultList;
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
               || (BigOrSmall.equals("big") && value > BiggestOrSmallestNumberYet)){
                BiggestOrSmallestNumberYet = value;
            }
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
