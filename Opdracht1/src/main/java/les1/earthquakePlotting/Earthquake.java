package les1.earthquakePlotting;

/**
 * Created by Kraaijeveld on 27-4-2016.
 */
public class Earthquake
{
    private GenericPair<Float, Float> convertedCoordinatePair;
    private GenericPair<Float, Float> latLongPair;
    private float richterScale;
    private float depth;

    public Earthquake(float latitude, float longitude, float richterScale, float depth) {
        this.latLongPair = new GenericPair<Float, Float>(latitude, longitude);
        this.richterScale = richterScale;
        this.depth = depth;
    }

    public GenericPair<Float, Float> getlatLongPair() { return this.latLongPair; }

    public GenericPair<Float, Float> getConvertedCoordinatePair() { return convertedCoordinatePair; }

    public float getRichterScale() { return this.richterScale; }

    public float getDepth() { return depth; }

    public void setConvertedCoordinatePair(GenericPair<Float, Float> convertedCoordinatePair) { this.convertedCoordinatePair = convertedCoordinatePair; }

    public void setRichterScale(float richterScale) {
        this.richterScale = richterScale;
    }

    public void setDepth(float depth) { this.depth = depth; }

}
