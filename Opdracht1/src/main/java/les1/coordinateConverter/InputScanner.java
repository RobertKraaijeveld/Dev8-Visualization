package les1.coordinateConverter;

import les1.earthquakePlotting.XYPair;

import java.util.Scanner;

/**
 * Created by Kraaijeveld on 13-5-2016.
 */
public class InputScanner
{
    private Scanner scanner = new Scanner(System.in);
    private XYPair givenCoordinates;
    private String sourceCoordinateType;
    private String resultCoordinateType;


    public void setResultCoordinateType(String resultCoordinateType) {
        this.resultCoordinateType = resultCoordinateType;
    }

    public void setSourceCoordinateType(String sourceCoordinateType) {
        this.sourceCoordinateType = sourceCoordinateType;
    }

    public String validateGivenCoordinate()
    {
        String coordinateType = scanner.next();

        if((coordinateType.equals("RDH"))
        || (coordinateType.equals("Geo"))
        || (coordinateType.equals("UTM")))
        {
            return coordinateType;
        }
        else
        {
            System.out.println("Please specify a valid coordinate type.");
            return validateGivenCoordinate();
        }
    }

    public void scanAndValidateInputOfGivenTypeOfCoordinate(String kindOfCoordinate)
    {
        try
        {
            //distinghuish between types
            System.out.println("Please input a valid " + kindOfCoordinate + " coordinate.");
            String input = scanner.next();
            String latString = input.substring(0, input.indexOf(","));
            String longString = input.substring(input.indexOf(","));
            System.out.println(latString + "," + longString);

            Float latFloat = Float.parseFloat(latString);
            Float longFloat = Float.parseFloat(longString);
            this.givenCoordinates = new XYPair(latFloat, longFloat);
        }
        catch (Exception e)
        {
            System.out.println("Please fill in a valid coordinate.");
        }
    }

    public String getResultCoordinateType() {
        return resultCoordinateType;
    }

    public String getSourceCoordinateType() {
        return sourceCoordinateType;
    }
}
