package MapDrawing;

import Datastructures.ComplaintLocation;
import Datastructures.GenericPair;

import java.util.ArrayList;

import static processing.core.PApplet.map;

/**
 * Created by Kraaijeveld on 24-6-2016.
 */
public class LatLongConverter
{
    private GenericPair<Float, Float> minMaxLongitudeCoordinates;
    private GenericPair<Float, Float> minMaxLatitudeCoordinates;
    private GenericPair<Integer, Integer> screenXYMaxValues;


    public LatLongConverter(GenericPair<Float, Float> minMaxLongitudeCoordinates, GenericPair<Float, Float> minMaxLatitudeCoordinates,
                            GenericPair<Integer, Integer> screenXYMaxValues)
    {
        this.minMaxLongitudeCoordinates = minMaxLongitudeCoordinates;
        this.minMaxLatitudeCoordinates = minMaxLatitudeCoordinates;
        this.screenXYMaxValues = screenXYMaxValues;
    }

    public ArrayList<GenericPair<Float, Float>> convertGivenCoordinateListToXYValues(ArrayList<ComplaintLocation> latLongs)
    {
        ArrayList<GenericPair<Float, Float>> returnList = new ArrayList<>();

        for (ComplaintLocation latLongLocation : latLongs)
        {
            Float Xvalue = map(latLongLocation.getLongitude(), minMaxLongitudeCoordinates.getLeftValue(),
                    minMaxLongitudeCoordinates.getRightValue(), 0f, screenXYMaxValues.getLeftValue());

            Float Yvalue = map(latLongLocation.getLatitude(), minMaxLatitudeCoordinates.getRightValue(),
                    minMaxLatitudeCoordinates.getLeftValue(), 0f, screenXYMaxValues.getRightValue());

            returnList.add(new GenericPair<>(Xvalue, Yvalue));
        }
        return returnList;
    }
}
