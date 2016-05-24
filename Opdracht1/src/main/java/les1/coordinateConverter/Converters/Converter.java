package les1.coordinateConverter.Converters;

import les1.coordinateConverter.CoordinateTypes.Coordinate;

/**
 * Created by Kraaijeveld on 14-5-2016.
 */
public abstract class Converter
{
    public abstract Coordinate convertToDMS(Coordinate c);
    public abstract Coordinate convertToRDH(Coordinate c);
    public abstract Coordinate convertToDecimal(Coordinate c);
}
