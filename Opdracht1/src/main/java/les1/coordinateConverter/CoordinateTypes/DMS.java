package les1.coordinateConverter.CoordinateTypes;

import les1.coordinateConverter.Converters.DMSConverter;
import les1.earthquakePlotting.GenericPair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kraaijeveld on 13-5-2016.
 */
public class DMS extends Coordinate
{
    private GenericPair<Float, Float> xyGenericPair;
    private DMSConverter DMSConverter = new DMSConverter();


    public DMS(GenericPair<Float, Float> xyPair)
    {
        Float leftFloat = xyPair.getLeftValue();
        Float rightFloat = xyPair.getRightValue();
        this.xyGenericPair = new GenericPair<Float, Float>(leftFloat, rightFloat);
    }
    
    public GenericPair<Float, Float> getXYPair()
    {
        return this.xyGenericPair;
    }

    public DMSConverter getConverter() { return this.DMSConverter; }

    public static boolean checkIfGivenCoordinateIsOfThisType(GenericPair<Float, Float> xyFloats)
    {
        //Regex pattern for decimal number
        String firstCoordinate = xyFloats.getLeftValue().toString();
        String secondCoordinate = xyFloats.getRightValue().toString();

        Pattern p = Pattern.compile("\\d{6}\\.\\d");
        Matcher m1 =  p.matcher(firstCoordinate);
        Matcher m2 = p.matcher(secondCoordinate);

        if(m1.matches() && m2.matches())
            return true;
        else
            return false;
    }
}
