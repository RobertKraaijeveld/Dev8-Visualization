package les1.coordinateConverter.Converters;


import les1.coordinateConverter.CoordinateTypes.Coordinate;
import les1.coordinateConverter.CoordinateTypes.Decimal;
import les1.coordinateConverter.CoordinateTypes.DMS;
import les1.coordinateConverter.CoordinateTypes.RDH;
import les1.earthquakePlotting.GenericPair;

/**
 * Created by Kraaijeveld on 14-5-2016.
 */

public class DecimalConverter extends Converter
{
    public DMS convertToDMS(Coordinate sourceCoordinate)
    {
        Float leftHandValue = DecimalConverter.ConvertSingleDecimalToDMS(sourceCoordinate.getXYPair().getLeftValue().toString());
        Float rightHandValue = DecimalConverter.ConvertSingleDecimalToDMS(sourceCoordinate.getXYPair().getRightValue().toString());

        DMS returnDMS = new DMS(new GenericPair<>(leftHandValue, rightHandValue));

        System.out.println("DECIMAL to DMS: " + leftHandValue + "," + rightHandValue);
        return returnDMS;
    }

    public RDH convertToRDH(Coordinate sourceCoordinate)
    {
        DMS DMSVersionOfDecimal = this.convertToDMS(sourceCoordinate);

        DMSConverter DMSConverter = new DMSConverter();
        RDH returnRDH = DMSConverter.convertToRDH(DMSVersionOfDecimal);

        return returnRDH;
    }

    public Decimal convertToDecimal(Coordinate sourceCoordinate)
    {
        System.out.println("Converted to itself");
        return (Decimal) sourceCoordinate;
    }

    public static Float ConvertSingleDecimalToDMS(String decimalStringToConvert)
    {
        Double decimalDoubleToConvert = Double.parseDouble(decimalStringToConvert);

        Double degreesFraction = decimalDoubleToConvert - (decimalDoubleToConvert % 1);
        Double decimalCoordinate = decimalDoubleToConvert - degreesFraction;

        Double minutes = (decimalCoordinate * 60) - ((decimalCoordinate * 60) % 1);
        Double seconds = ((decimalCoordinate * 60) % 1) * 60;

        Integer degreesFractionInt = Double.valueOf(degreesFraction).intValue();
        Integer minutesInt = Double.valueOf(minutes).intValue();
        Integer secondsInt = Double.valueOf(seconds).intValue();

        //We use strings and convert to ints to make sure NumberFormatExceptions dont happen.
        //When we have a sane result, we can convert it back to float and use it.
        String resultString = degreesFractionInt.toString() + minutesInt.toString() + secondsInt.toString();
        return Float.parseFloat(resultString);
    }
}
