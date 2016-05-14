package les1.coordinateConverter.CoordinateTypes;

import les1.coordinateConverter.Converters.Converter;
import les1.earthquakePlotting.GenericPair;

/**
 * Created by Kraaijeveld on 14-5-2016.
 */
public abstract class Coordinate
{
    Converter c;
    public abstract Converter getConverter();
    public abstract GenericPair getXYPair();
}
