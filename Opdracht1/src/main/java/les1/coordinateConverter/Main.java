package les1.coordinateConverter;

/**
 * Created by Kraaijeveld on 13-5-2016.
 */
public class Main
{
    public static void main(String[] args)
    {
        InputScanner scanner = new InputScanner();

        System.out.println("Please specify a coordinate source type.");
        scanner.setSourceCoordinateType(scanner.validateGivenCoordinate());
        System.out.println("Please specify a coordinate result type.");
        scanner.setResultCoordinateType(scanner.validateGivenCoordinate());

        //make this name better
        scanner.scanAndValidateInputOfGivenTypeOfCoordinate(scanner.getSourceCoordinateType());
    }

}
