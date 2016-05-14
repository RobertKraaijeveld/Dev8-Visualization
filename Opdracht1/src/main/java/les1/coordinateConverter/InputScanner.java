package les1.coordinateConverter;

import les1.coordinateConverter.CoordinateTypes.Coordinate;
import les1.coordinateConverter.CoordinateTypes.Decimal;
import les1.coordinateConverter.CoordinateTypes.Geographic;
import les1.coordinateConverter.CoordinateTypes.RDH;
import les1.earthquakePlotting.GenericPair;

import java.util.Scanner;

/**
 * Created by Kraaijeveld on 13-5-2016.
 */
public class InputScanner
{
    private Scanner scanner = new Scanner(System.in);
    private Coordinate givenCoordinates;
    private String sourceCoordinateType;
    private String resultCoordinateType;


    public void setResultCoordinateType(String resultCoordinateType) {
        this.resultCoordinateType = resultCoordinateType;
    }

    public void setSourceCoordinateType(String sourceCoordinateType) {
        this.sourceCoordinateType = sourceCoordinateType;
    }

    public String validateGivenCoordinateType()
    {
        String coordinateType = scanner.next();

        if((coordinateType.equals("RDH"))
        || (coordinateType.equals("Geo"))
        || (coordinateType.equals("Decimal")))
        {
            return coordinateType;
        }
        else
        {
            System.out.println("Please specify a valid coordinate type.");
            return validateGivenCoordinateType();
        }
    }

    public void scanAndValidateInputOfGivenCoordinate(String kindOfCoordinate)
    {
        try
        {
            System.out.println("Please input a valid " + kindOfCoordinate + " coordinate.");
            String input = this.scanner.next();
            GenericPair<String, String> genericPairOfGivenXYStrings = splitCoordinateString(input, ',');

            if((kindOfCoordinate.equals("Geo")) && (Geographic.checkIfGivenCoordinateIsOfThisType(genericPairOfGivenXYStrings)))
            {
                Geographic geoCoordinate = new Geographic(genericPairOfGivenXYStrings);
                this.givenCoordinates = geoCoordinate;
            }
            else if((kindOfCoordinate.equals("RDH")) && (RDH.checkIfGivenCoordinateIsOfThisType(genericPairOfGivenXYStrings)))
            {
                RDH rdhCoordinate = new RDH(genericPairOfGivenXYStrings);
                this.givenCoordinates = rdhCoordinate;
            }
            else if((kindOfCoordinate.equals("Decimal")) && (Decimal.checkIfGivenCoordinateIsOfThisType(input)))
            {
                GenericPair<String, String> givenDecimalXYPair = splitCoordinateString(input, '.');
                Decimal decimalCoordinate = new Decimal(givenDecimalXYPair);
                this.givenCoordinates = decimalCoordinate;
            }
            else
                scanAndValidateInputOfGivenCoordinate(kindOfCoordinate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            scanAndValidateInputOfGivenCoordinate(kindOfCoordinate);
        }
    }

    public String getResultCoordinateType() {
        return resultCoordinateType;
    }

    public String getSourceCoordinateType() {
        return sourceCoordinateType;
    }

    private GenericPair<String, String> splitCoordinateString(String coordinateString, char delimiterToSplitBy)
    {
        String x = coordinateString.substring(0, coordinateString.indexOf(delimiterToSplitBy));
        String y = coordinateString.substring(coordinateString.indexOf(delimiterToSplitBy) + 1);
        System.out.println(x + "," + y);
        return new GenericPair<>(x, y);
    }
}
