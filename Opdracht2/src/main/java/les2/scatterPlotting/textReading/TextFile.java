package les2.scatterPlotting.textReading;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import les2.scatterPlotting.Main.GenericPair;

import javax.xml.soap.Text;

import static java.util.Comparator.comparingInt;


/**
 *
 * @author gover_000
 */

//This is the class for the 'normal' X-Y textfile.
public class TextFile
{
    private ArrayList<GenericPair<Float,Float>> XYfloats = new ArrayList<>();
    private ArrayList<Integer> catValues = new ArrayList<>();
    
    public TextFile(ArrayList<GenericPair<Float,Float>> lines, ArrayList<Integer> catValues)
    {
        this.catValues = catValues;
        this.XYfloats = lines;
    }

    public ArrayList<GenericPair<Float,Float>> getLines()
    {
        return this.XYfloats;
    }

    public ArrayList<Integer> getCatValues() { return this.catValues; }

    /*
    * GOTTA CLEAN THIS MESS UP
     */

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

