package les2.scatterPlotting.Plotting;

import les2.scatterPlotting.Main.GenericPair;

/**
 * Created by Kraaijeveld on 26-5-2016.
 */
public class Axises
{
    private GenericPair<Point, Point> xAxisPoints;
    private GenericPair<Point, Point> yAxisPoints;

    public Axises(GenericPair<Point, Point> xAxisPoints, GenericPair<Point, Point> yAxisPoints)
    {
        this.xAxisPoints = xAxisPoints;
        this.yAxisPoints = yAxisPoints;
    }

    public GenericPair<Point, Point> getxAxisPoints() {
        return this.xAxisPoints;
    }

    public GenericPair<Point, Point> getyAxisPoints() {
        return this.yAxisPoints;
    }
}
