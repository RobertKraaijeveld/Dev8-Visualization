package les1.coordinateConverter.Converters;


import les1.coordinateConverter.CoordinateTypes.Coordinate;
import les1.coordinateConverter.CoordinateTypes.Decimal;
import les1.coordinateConverter.CoordinateTypes.DMS;
import les1.coordinateConverter.CoordinateTypes.RDH;
import les1.earthquakePlotting.GenericPair;

/**
 * Created by Kraaijeveld on 14-5-2016.
 */
public class RDHConverter extends Converter
{
    public Decimal convertToDecimal(Coordinate sourceCoordinate)
    {
        String result = null;

        //These are the coordinates for Amersfoort: The 'centre' of the RDH system.
        int referenceRDHx = 155000;
        int referenceRDHy = 463000;

        //These are the coordinates for Amersfoort in normal decimal/dms.
        double referenceGeoX = 52.15517;
        double referenceGeoY = 5.387206;

        Float GeoLat = (float) sourceCoordinate.getXYPair().getLeftValue();
        Float GeoLong = (float) sourceCoordinate.getXYPair().getRightValue();

        double dX = (double)(GeoLat - referenceRDHx) * (double)(Math.pow(10, -5));
        double dY = (double)(GeoLong - referenceRDHy) * (double)(Math.pow(10, -5));

        double sumN =
                (3235.65389 * dY) +
                        (-32.58297 * Math.pow(dX, 2)) +
                        (-0.2475 * Math.pow(dY, 2)) +
                        (-0.84978 * Math.pow(dX, 2) * dY) +
                        (-0.0655 * Math.pow(dY, 3)) +
                        (-0.01709 * Math.pow(dX, 2) * Math.pow(dY, 2)) +
                        (-0.00738 * dX) +
                        (0.0053 * Math.pow(dX, 4)) +
                        (-0.00039 * Math.pow(dX, 2) * Math.pow(dY, 3)) +
                        (0.00033 * Math.pow(dX, 4) * dY) +
                        (-0.00012 * dX * dY);
        double sumE =
                (5260.52916 * dX) +
                        (105.94684 * dX * dY) +
                        (2.45656 * dX * Math.pow(dY, 2)) +
                        (-0.81885 * Math.pow(dX, 3)) +
                        (0.05594 * dX * Math.pow(dY, 3)) +
                        (-0.05607 * Math.pow(dX, 3) * dY) +
                        (0.01199 * dY) +
                        (-0.00256 * Math.pow(dX, 3) * Math.pow(dY, 2)) +
                        (0.00128 * dX * Math.pow(dY, 4)) +
                        (0.00022 * Math.pow(dY, 2)) +
                        (-0.00022 * Math.pow(dX, 2)) +
                        (0.00026 * Math.pow(dX, 5));


        Float latitude = (float) (referenceGeoX + (sumN / 3600));
        Float longitude = (float) (referenceGeoY + (sumE / 3600));
        System.out.println("RDH to DECIMAL: " + latitude + "," + longitude);

        Decimal d = new Decimal(new GenericPair<>(latitude, longitude));
        return d;
    }

    public DMS convertToDMS(Coordinate sourceCoordinate)
    {
        Decimal decimalVersionOfRDH = this.convertToDecimal(sourceCoordinate);

        Float leftDMSValue = DecimalConverter.ConvertSingleDecimalToDMS(decimalVersionOfRDH.getXYPair().getLeftValue().toString());
        Float rightDMSValue = DecimalConverter.ConvertSingleDecimalToDMS(decimalVersionOfRDH.getXYPair().getRightValue().toString());

        GenericPair<Float,Float> dmsPair = new GenericPair<>(leftDMSValue, rightDMSValue);
        DMS returnDMS = new DMS(dmsPair);

        System.out.println("RDH to DMS: " + returnDMS.getXYPair().getLeftValue() + ","
        + returnDMS.getXYPair().getRightValue());

        return returnDMS;
    }

    public RDH convertToRDH(Coordinate sourceCoordinate)
    {
        System.out.println("Converted to itself");
        return (RDH) sourceCoordinate;
    }
}
