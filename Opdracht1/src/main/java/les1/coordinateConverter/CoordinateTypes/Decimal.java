package les1.coordinateConverter.CoordinateTypes;

import les1.coordinateConverter.Converters.DecimalConverter;
import les1.earthquakePlotting.GenericPair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kraaijeveld on 13-5-2016.
 */
public class Decimal extends Coordinate
{
    private GenericPair<Float, Float> xyGenericPair;
    private DecimalConverter decimalConverter = new DecimalConverter();

    public Decimal(GenericPair<Float, Float> xyPair)
    {
        Float leftFloat = xyPair.getLeftValue();
        Float rightFloat =  xyPair.getRightValue();
        this.xyGenericPair = new GenericPair<Float, Float>(leftFloat, rightFloat);
    }

    public GenericPair<Float, Float> getXYPair()
    {
        return this.xyGenericPair;
    }

    public DecimalConverter getConverter() { return this.decimalConverter; }

    public static boolean checkIfGivenCoordinateIsOfThisType(String givenCoordinateString)
    {
        //Regex pattern for decimal number
        String firstDecimal = givenCoordinateString.substring(0, givenCoordinateString.indexOf(','));
        String secondDecimal = givenCoordinateString.substring(givenCoordinateString.indexOf(',') + 1);

        Pattern p = Pattern.compile("^[0-9]\\d*(\\.\\d+)?$");
        Matcher m1 =  p.matcher(firstDecimal);
        Matcher m2 = p.matcher(secondDecimal);

        if(m1.matches() && m2.matches())
            return true;
        else
            return false;
    }
}
