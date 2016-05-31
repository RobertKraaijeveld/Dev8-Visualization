package Plotting;

import Datastructures.GenericPair;
import Datastructures.Vector3D;

import java.util.ArrayList;
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
    private GenericPair<Integer, Integer> appletWidthHeight;

    public ValueConverter(List<Vector3D> valuesList,
                          GenericPair<Vector3D,Vector3D> smallestXYs,
                          GenericPair<Vector3D,Vector3D> largestXYs,
                          GenericPair<Integer, Integer> appletWidthHeight)
    {
        this.valuesList = valuesList;
        this.smallestXYs = smallestXYs;
        this.largestXYs = largestXYs;
        this.appletWidthHeight = appletWidthHeight;
    }

    public List<Vector3D> transformCoordinatesListToAppletPositions()
    {
        //I chose to transform an existing list instead of copying into a new one for performance reasons.
        //Not creating a new Vector here decreases readability, but increases performance.
        for(int i = 0; i < this.valuesList.size(); i++)
        {
            this.valuesList.set(i, convertCartesianVectorToAppletPosition(this.valuesList.get(i)));
        }
        return this.valuesList;
    }

    private Vector3D convertCartesianVectorToAppletPosition(Vector3D unconvertedVector)
    {
        float smallestX = this.smallestXYs.getLeftValue().getX();
        float smallestY = this.smallestXYs.getRightValue().getY();

        float largestX = this.largestXYs.getLeftValue().getX();
        float largestY = this.largestXYs.getRightValue().getY();

        float convertedX = map(unconvertedVector.getX(), smallestX, largestX,
                               this.appletWidthHeight.getLeftValue(),
                               this.appletWidthHeight.getRightValue());

        float convertedY = map(unconvertedVector.getY(), smallestY, largestY,
                               this.appletWidthHeight.getLeftValue(),
                               this.appletWidthHeight.getRightValue());

        Vector3D returnVector = new Vector3D();
        returnVector.setxValue(convertedX);
        returnVector.setyValue(convertedY);
        returnVector.setzValue(unconvertedVector.getZ());

        return returnVector;
    }
}
