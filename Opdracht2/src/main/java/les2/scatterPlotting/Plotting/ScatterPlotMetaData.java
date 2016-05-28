package les2.scatterPlotting.Plotting;

import les2.scatterPlotting.Main.GenericPair;

/**
 * Created by Kraaijeveld on 24-5-2016.
 */
public class ScatterPlotMetaData
{
    private GenericPair<Float, Float> heightWidthPair;
    private GenericPair<String, String> typeOfXYvalues;

    public ScatterPlotMetaData(GenericPair<String, String> typeOfXYvalues,
                               GenericPair<Float, Float> widthHeightPair)
    {
        this.typeOfXYvalues = typeOfXYvalues;
        this.heightWidthPair = widthHeightPair;
    }

    public GenericPair<Float, Float> getWidthHeightPair()
    {
        return this.heightWidthPair;
    }

    public GenericPair<String, String> getGenericPairOfXYValuesIfSet()
    {
        //Bit dirty, but we need a way to let the valueConverter know with what type of plot its going to be dealing.
        if(this.typeOfXYvalues != null)
            return this.typeOfXYvalues;
        else
            return null;
    }
}
