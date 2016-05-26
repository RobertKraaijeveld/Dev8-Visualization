package les2.scatterPlotting.Plotting;

import les2.scatterPlotting.Main.GenericPair;

import java.util.ArrayList;

/**
 *
 * @author gover_000
 */

public class ScatterPlot
{
    private ArrayList<Point> pointPositions = new ArrayList<>();
    private Axises axises;

    public ScatterPlot(ArrayList<Point> pointPositions, Axises axises)
    {
        this.pointPositions = pointPositions;
        this.axises = axises;
    }

    public ArrayList<Point> getPointPositions() { return this.pointPositions; }

    public  Axises getAxises() { return this.axises; }

}

