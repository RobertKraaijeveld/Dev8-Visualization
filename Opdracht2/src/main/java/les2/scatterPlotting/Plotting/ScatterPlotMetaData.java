package les2.scatterPlotting.Plotting;

import les2.scatterPlotting.Main.GenericPair;

/**
 * Created by Kraaijeveld on 24-5-2016.
 */
public class ScatterPlotMetaData
{
    private Float xAxisUpperBoundary;
    private Float yAxisUpperBoundary;
    private GenericPair<Float, Float> heightWidthPair;

    public ScatterPlotMetaData(//Float xAxisUpperBoundary,
                               //Float yAxisUpperBoundary,
                               GenericPair<Float, Float> widthHeightPair)
    {
        //this.xAxisUpperBoundary = xAxisUpperBoundary;
        //this.yAxisUpperBoundary = yAxisUpperBoundary;
        this.heightWidthPair = widthHeightPair;
    }

    public Float getxAxisUpperBoundary() { return this.xAxisUpperBoundary; }

    public Float getyAxisUpperBoundary()
    {
        return this.yAxisUpperBoundary;
    }

    public GenericPair<Float, Float> getWidthHeightPair()
    {
        return this.heightWidthPair;
    }
}
