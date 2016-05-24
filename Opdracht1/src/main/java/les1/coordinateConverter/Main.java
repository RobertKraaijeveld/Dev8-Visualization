package les1.coordinateConverter;

import les1.coordinateConverter.CoordinateTypes.Coordinate;

/**
 * Created by Kraaijeveld on 13-5-2016.
 */
public class Main
{
    public static void main(String[] args)
    {
        InputScanner scanner = new InputScanner();

        System.out.println("");
        System.out.println("Please specify a coordinate source type.");
        scanner.setSourceCoordinateType(scanner.validateGivenCoordinateType());

        System.out.println("Please specify a coordinate result type.");
        scanner.setResultCoordinateType(scanner.validateGivenCoordinateType());

        scanner.scanAndValidateInputOfGivenCoordinate(scanner.getSourceCoordinateType());
        Coordinate givenCoordinate = scanner.getGivenCoordinates();
        String resultType = scanner.getResultCoordinateType();

        givenCoordinateHandler handler = new givenCoordinateHandler();
        handler.doAppropriateConversion(givenCoordinate, resultType);

        main(args);
    }

}
