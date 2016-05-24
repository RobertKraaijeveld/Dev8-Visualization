package les1.coordinateConverter;

import les1.coordinateConverter.Converters.Converter;
import les1.coordinateConverter.CoordinateTypes.Coordinate;
import les1.coordinateConverter.CoordinateTypes.Decimal;
import les1.coordinateConverter.CoordinateTypes.DMS;
import les1.coordinateConverter.CoordinateTypes.RDH;

/**
 * Created by Kraaijeveld on 14-5-2016.
 */

public class givenCoordinateHandler
{
    public Coordinate doAppropriateConversion(Coordinate givenCoordinate, String resultCoordinateType)
    {
        Converter c = givenCoordinate.getConverter();

        //I say sonny, this be some damn nice polymorphism
        if(resultCoordinateType.equals("DMS"))
        {
            DMS resultDMS = (DMS) c.convertToDMS(givenCoordinate);
            System.out.println("Converted to dms, result: " + resultDMS.getXYPair().getLeftValue() + ","
            + resultDMS.getXYPair().getRightValue());
            return resultDMS;
        }
        else if (resultCoordinateType.equals("Decimal"))
        {
            Decimal resultDecimal = (Decimal) c.convertToDecimal(givenCoordinate);
            return resultDecimal;
        }
        else
        {
            RDH resultRDH = (RDH) c.convertToRDH(givenCoordinate);
            return resultRDH;
        }
    }
}
