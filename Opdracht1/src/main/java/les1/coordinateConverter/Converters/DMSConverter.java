package les1.coordinateConverter.Converters;


import les1.coordinateConverter.CoordinateTypes.Coordinate;
import les1.coordinateConverter.CoordinateTypes.Decimal;
import les1.coordinateConverter.CoordinateTypes.DMS;
import les1.coordinateConverter.CoordinateTypes.RDH;
import les1.earthquakePlotting.GenericPair;

/**
 * Created by Kraaijeveld on 14-5-2016.
 */
public class DMSConverter extends Converter
{
    public RDH convertToRDH(Coordinate sourceCoordinate)
    {
        //These are the coordinates for Amersfoort: The 'centre' of the RDH system.
        int referenceRDHx = 155000;
        int referenceRDHy = 463000;

        //These are the coordinates for Amersfoort in normal decimal/dms.
        double referenceDMSX = 52.15517;
        double referenceDMSY = 5.387206;

        Float DMSLat = (float) sourceCoordinate.getXYPair().getLeftValue();
        Float DMSLong = (float) sourceCoordinate.getXYPair().getRightValue();

        double[][] Rpq = new double[4][5];

        Rpq[0][1] = 190094.945;
        Rpq[1][1] = -11832.228;
        Rpq[2][1] = -114.221;
        Rpq[0][3] = -32.391;
        Rpq[1][0] = -0.705;
        Rpq[3][1] = -2.340;
        Rpq[0][2] = -0.008;
        Rpq[1][3] = -0.608;
        Rpq[2][3] = 0.148;

        double[][] Spq = new double[4][5];
        Spq[0][1] = 0.433;
        Spq[0][2] = 3638.893;
        Spq[0][4] = 0.092;
        Spq[1][0] = 309056.544;
        Spq[2][0] = 73.077;
        Spq[1][2] = -157.984;
        Spq[3][0] = 59.788;
        Spq[2][2] = -6.439;
        Spq[1][1] = -0.032;
        Spq[1][4] = -0.054;

        double d_latitude = 0.36 * (DMSLat - referenceDMSX);
        double d_longitude = 0.36 * (DMSLong - referenceDMSY);

        double calc_latt = 0;
        double calc_long = 0;

        for (int p = 0; p < 4; p++)
        {
            for (int q = 0; q < 5; q++)
            {
                calc_latt += Rpq[p][q] * Math.pow(d_latitude, p) * Math.pow(d_longitude, q);
                calc_long += Spq[p][q] * Math.pow(d_latitude, p) * Math.pow(d_longitude, q);
            }
        }

        Float rdhXcoordinate = (float) (referenceRDHx + calc_latt);
        Float rdhYcoordinate = (float) (referenceRDHy + calc_long);
        System.out.println("DMS to RDH: " + rdhXcoordinate + "," + rdhYcoordinate);

        GenericPair<Float, Float> rdhPair = new GenericPair<>(rdhXcoordinate, rdhYcoordinate);
        return new RDH(rdhPair);
    }

    public Decimal convertToDecimal(Coordinate sourceCoordinate)
    {
        Float leftHandValue = this.ConvertSingleDMSToDecimal(Math.round((float) sourceCoordinate.getXYPair().getLeftValue()));
        Float rightHandValue = this.ConvertSingleDMSToDecimal(Math.round((float) sourceCoordinate.getXYPair().getRightValue()));

        Decimal returnDecimal = new Decimal(new GenericPair<>(leftHandValue, rightHandValue));

        System.out.println("DMS to DECIMAL: " + leftHandValue + "," + rightHandValue);
        return returnDecimal;
    }

    public DMS convertToDMS(Coordinate sourceCoordinate)
    {
        System.out.println("Converted to itself");
        return (DMS) sourceCoordinate;
    }

    private Float ConvertSingleDMSToDecimal(Integer degreeToConvert)
    {
        String wholeDegrees = degreeToConvert.toString().substring(0, 2);
        System.out.println(wholeDegrees);

        String minutesAndSecondsString = degreeToConvert.toString().substring(2);
        System.out.println(minutesAndSecondsString);

        String minutes = minutesAndSecondsString.substring(0,2);
        System.out.println(minutes);

        String seconds = minutesAndSecondsString.substring(2);
        System.out.println(seconds);

        Float totalNumberOfSeconds = Float.parseFloat(minutes) * 60 + Float.parseFloat(seconds);
        Float fractionalDegrees = totalNumberOfSeconds / 3600.0f;

        Float finalResult = Float.parseFloat(wholeDegrees) + fractionalDegrees;
        return finalResult;
    }
}
