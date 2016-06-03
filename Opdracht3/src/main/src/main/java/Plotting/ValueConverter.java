package Plotting;

import Datastructures.GenericPair;
import Datastructures.Vector3D;

import java.util.List;

import static processing.core.PApplet.map;

/**
 * Created by Kraaijeveld on 31-5-2016.
 */

public class ValueConverter
{
    private List<Vector3D> valuesList;
    private GenericPair<Vector3D, Vector3D> smallestXYs;
    private GenericPair<Vector3D, Vector3D> largestXYs;
    private GenericPair<Integer, Integer> appletWidthHeightMaximums;


    public ValueConverter(List<Vector3D> valuesList,
                          GenericPair<Vector3D,Vector3D> smallestXYs,
                          GenericPair<Vector3D,Vector3D> largestXYs,
                          GenericPair<Integer, Integer> appletWidthHeightMaximums)
    {
        this.valuesList = valuesList;

        this.smallestXYs = smallestXYs;
        this.largestXYs = largestXYs;
        this.appletWidthHeightMaximums = appletWidthHeightMaximums;
    }

    public List<Vector3D> getAppletPositions()
    {
        System.out.println("Started transforming to full map.");

        //I chose to transform an existing list instead of copying into a new one for performance reasons.
        //Not creating a new Vector here decreases readability, but increases performance.
        for(int i = 0; i < this.valuesList.size(); i++)
        {
            this.valuesList.set(i, convertCartesianVectorToAppletPosition(this.valuesList.get(i)));
        }
        System.out.println("Done transforming to full map.");
        return this.valuesList;
    }

    private Vector3D convertCartesianVectorToAppletPosition(Vector3D unconvertedVector)
    {
        float smallestX = this.smallestXYs.getLeftValue().getX();
        float smallestY = this.smallestXYs.getRightValue().getY();
        
        float largestX = this.largestXYs.getLeftValue().getX();
        float largestY = this.largestXYs.getRightValue().getY();

        float convertedX = map(unconvertedVector.getX(), largestX, smallestX,
                0.0f, this.appletWidthHeightMaximums.getLeftValue());

        //Y increases from south to north, so the largestY and smallestY variables are called inverted.
        float convertedY = map(unconvertedVector.getY(), largestY, smallestY,
                0.0f, this.appletWidthHeightMaximums.getRightValue());

        Vector3D returnVector = new Vector3D(convertedX, convertedY, unconvertedVector.getZ());
        return returnVector;
    }
}
