package les1.coordinateConverter.CoordinateTypes;

import les1.earthquakePlotting.GenericPair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kraaijeveld on 13-5-2016.
 */
public class Decimal implements Coordinate
{
    private GenericPair<Float, Float> xyGenericPair;

    public Decimal(GenericPair<String, String> xyPair)
    {
        Float leftFloat = Float.parseFloat(xyPair.getLeftValue());
        Float rightFloat = Float.parseFloat(xyPair.getRightValue());
        this.xyGenericPair = new GenericPair<Float, Float>(leftFloat, rightFloat);
    }

    public GenericPair<Float, Float> getXYPair()
    {
        return this.xyGenericPair;
    }

    public static boolean checkIfGivenCoordinateIsOfThisType(String givenCoordinateString)
    {
        //Regex pattern for decimal number
        Pattern p = Pattern.compile("^[0-9]\\d*(\\.\\d+)?$");
        Matcher m =  p.matcher(givenCoordinateString);
        return m.matches();
    }
}
