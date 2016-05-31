package Plotting;

import Datastructures.GenericPair;
import Datastructures.Vector3D;
import processing.core.PApplet;

import java.util.List;

/**
 * Created by Kraaijeveld on 31-5-2016.
 */

public class BasicMapPlot extends PApplet
{
    private List<Vector3D> vectorsToBeDrawn;
    private final GenericPair<Integer, Integer> appletWidthHeight;

    public BasicMapPlot(List<Vector3D> vectorsToBeDrawn,
                        GenericPair<Integer, Integer> appletWidthHeight)
    {
        this.vectorsToBeDrawn = vectorsToBeDrawn;
        this.appletWidthHeight = appletWidthHeight;
        PApplet.main(new String[]{BasicMapPlot.class.getName()});
    }

    public void settings()
    {
        noSmooth();
        noLoop();
        size(appletWidthHeight.getLeftValue(), appletWidthHeight.getRightValue());
    }

    public void setup()
    {
    }

    public void draw()
    {
        background(220);
        fill(50, 100);

        drawEllipses();
    }

    public void drawEllipses()
    {
        for(Vector3D vector : this.vectorsToBeDrawn)
        {
            int colorToUse = this.calculateEllipseColor(vector);
            fill(colorToUse);
            ellipse(vector.getX(), vector.getY(), 1,1);
        }
    }

    private int calculateEllipseColor(Vector3D vector)
    {
        return 100 + ((int) vector.getZ() * 2);
    }


}
