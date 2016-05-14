package les1.coordinateConverter.CoordinateTypes;

import les1.earthquakePlotting.GenericPair;

import les1.coordinateConverter.Converters.RDHConverter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kraaijeveld on 13-5-2016.
 */
public class RDH extends Coordinate
{
    private GenericPair<Float, Float> xyGenericPair;
    private RDHConverter rdhConverter = new RDHConverter();

    public RDH(GenericPair<Float, Float> xyPair)
    {
        Float leftFloat = xyPair.getLeftValue();
        Float rightFloat = xyPair.getRightValue();
        this.xyGenericPair = new GenericPair<Float, Float>(leftFloat, rightFloat);
    }

    public GenericPair<Float, Float> getXYPair()
    {
        return this.xyGenericPair;
    }

    public RDHConverter getConverter()
    {
        return this.rdhConverter;
    }

    public static boolean checkIfGivenCoordinateIsOfThisType(GenericPair<Float, Float> xyFloats)
    {
        String leftValue = xyFloats.getLeftValue().toString();
        String rightValue = xyFloats.getRightValue().toString();

        //Regex pattern for decimal number, done twice
        Pattern p = Pattern.compile("[0-9]+(\\.[0-9][0-9]?)?");
        Matcher m1 =  p.matcher(leftValue);
        boolean latBoolean = m1.matches();

        Matcher m2 = p.matcher(rightValue);
        boolean longBoolean = m2.matches();

        if(latBoolean == true && longBoolean == true)
            return true;
        else
            return false;

    }
}
