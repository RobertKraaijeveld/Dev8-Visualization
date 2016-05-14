package les1.coordinateConverter;

import les1.coordinateConverter.CoordinateTypes.Coordinate;
import les1.coordinateConverter.CoordinateTypes.Decimal;
import les1.coordinateConverter.CoordinateTypes.DMS;
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


            if(kindOfCoordinate.equals("Geo"))
            {
                GenericPair<Float, Float> genericPairOfGivenXYFloats = splitCoordinateString(input, ',');

                if(DMS.checkIfGivenCoordinateIsOfThisType(genericPairOfGivenXYFloats))
                {
                    DMS geoCoordinate = new DMS(genericPairOfGivenXYFloats);
                    this.givenCoordinates = geoCoordinate;
                }
                else
                {
                    System.out.println("Please input a real Geo coordinate.");
                    scanAndValidateInputOfGivenCoordinate(kindOfCoordinate);
                }
            }
            else if(kindOfCoordinate.equals("RDH"))
            {
                GenericPair<Float, Float> genericPairOfGivenXYFloats = splitCoordinateString(input, ',');

                if(RDH.checkIfGivenCoordinateIsOfThisType(genericPairOfGivenXYFloats))
                {
                    RDH rdhCoordinate = new RDH(genericPairOfGivenXYFloats);
                    this.givenCoordinates = rdhCoordinate;
                }
                else
                {
                    System.out.println("Please input a real RDH coordinate.");
                    scanAndValidateInputOfGivenCoordinate(kindOfCoordinate);
                }
            }
            else if(kindOfCoordinate.equals("Decimal"))
            {
                GenericPair<Float, Float> givenDecimalXYPair = splitCoordinateString(input, ',');

                if(Decimal.checkIfGivenCoordinateIsOfThisType(input))
                {
                    Decimal decimalCoordinate = new Decimal(givenDecimalXYPair);
                    this.givenCoordinates = decimalCoordinate;
                }
                else
                {
                    System.out.println("Please input a real Decimal coordinate.");
                    scanAndValidateInputOfGivenCoordinate(kindOfCoordinate);
                }
            }
            else
            {
                System.out.println("Please input a valid coordinate.");
                scanAndValidateInputOfGivenCoordinate(kindOfCoordinate);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            scanAndValidateInputOfGivenCoordinate(kindOfCoordinate);
        }
    }

    public Coordinate getGivenCoordinates() { return this.givenCoordinates; }

    public String getResultCoordinateType() {
        return resultCoordinateType;
    }

    public String getSourceCoordinateType() {
        return sourceCoordinateType;
    }

    private GenericPair<Float, Float> splitCoordinateString(String coordinateString, char delimiterToSplitBy)
    {
        Float x = Float.parseFloat(coordinateString.substring(0, coordinateString.indexOf(delimiterToSplitBy)));
        Float y = Float.parseFloat(coordinateString.substring(coordinateString.indexOf(delimiterToSplitBy) + 1));
        System.out.println(x + " and " + y);
        return new GenericPair<>(x, y);
    }
}
