package les1.coordinateConverter.CoordinateTypes;

import les1.earthquakePlotting.GenericPair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kraaijeveld on 13-5-2016.
 */
public class RDH implements Coordinate
{
    private GenericPair<Float, Float> xyGenericPair;

    public RDH(GenericPair<String, String> xyPair)
    {
        Float leftFloat = Float.parseFloat(xyPair.getLeftValue());
        Float rightFloat = Float.parseFloat(xyPair.getRightValue());
        this.xyGenericPair = new GenericPair<Float, Float>(leftFloat, rightFloat);
    }

    public GenericPair<Float, Float> getXYPair()
    {
        return this.xyGenericPair;
    }

    public static boolean checkIfGivenCoordinateIsOfThisType(GenericPair<String, String> xyStrings)
    {
        //Regex pattern for decimal number, done twice
        Pattern p = Pattern.compile("^[0-9]");
        Matcher m1 =  p.matcher(xyStrings.getLeftValue());
        boolean latBoolean = m1.matches();

        Matcher m2 = p.matcher(xyStrings.getRightValue());
        boolean longBoolean = m2.matches();

        if(latBoolean == true && longBoolean == true)
            return true;
        else
            return false;

    }
}
