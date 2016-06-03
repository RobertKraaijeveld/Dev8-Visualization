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

    private Vector3D ZADKINE_STATUE_LOCATION = new Vector3D(92798.3067017f, 436965.057678f,4.212f);


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

    public List<Vector3D> getAppletPositionsForFullMap()
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

    public List<Vector3D> getAppletPositionsAroundStatueAreaRadius(int radius)
    {
        System.out.println("Started transforming to statue area.");

        for (int i = 0; i < this.valuesList.size(); i++)
        {
            Vector3D currentVector = this.valuesList.get(i);

            if (this.doesVectorLieWithinRadius(currentVector, radius))
            {
                this.valuesList.set(i, convertCartesianVectorToAppletPosition(currentVector));
            }
        }
        System.out.println("Done transforming to statue area.");
        return this.valuesList;
    }


    //TODO: The smallestXY values might change depending on wether the user wants the full map or
    private Vector3D convertCartesianVectorToAppletPosition(Vector3D unconvertedVector)
    {
        float smallestX = this.smallestXYs.getLeftValue().getX();
        float smallestY = this.smallestXYs.getRightValue().getY();
        
        float largestX = this.largestXYs.getLeftValue().getX();
        float largestY = this.largestXYs.getRightValue().getY();

        float convertedX = map(unconvertedVector.getX(), smallestX, largestX,
                0.0f, this.appletWidthHeightMaximums.getLeftValue());

        //Y increases from south to north, so the largestY and smallestY variables are called inverted.
        float convertedY = map(unconvertedVector.getY(), largestY, smallestY,
                0.0f, this.appletWidthHeightMaximums.getRightValue());

        Vector3D returnVector = new Vector3D(convertedX, convertedY, unconvertedVector.getZ());
        return returnVector;
    }

    private boolean doesVectorLieWithinRadius(Vector3D vector, int radius)
    {
        //TODO: Check if these make sense
        float maximumPositiveYDistanceFromStatue = this.ZADKINE_STATUE_LOCATION.getY() + radius;
        float maximumNegativeYDistanceFromStatue = this.ZADKINE_STATUE_LOCATION.getY() - radius;

        float maximumPositiveXDistanceFromStatue = this.ZADKINE_STATUE_LOCATION.getX() + radius;
        float maximumNegativeXDistanceFromStatue = this.ZADKINE_STATUE_LOCATION.getX() - radius;

        if(vector.getY() <= maximumPositiveYDistanceFromStatue
        && vector.getY() >= maximumNegativeYDistanceFromStatue
        && vector.getX() <= maximumPositiveXDistanceFromStatue
        && vector.getX() >= maximumNegativeXDistanceFromStatue)
        {
            System.out.println("Vector " + vector.getX() + "," + vector.getY() + " is near the statue.");
            return true;
        }
        else
            return false;
    }


}
