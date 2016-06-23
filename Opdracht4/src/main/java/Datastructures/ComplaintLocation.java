package Datastructures;

/**
 * Created by Kraaijeveld on 21-6-2016.
 */

//I should have coded this to an interface. Food for thought.
public class ComplaintLocation
{
    private float latitude;
    private float longitude;

    public ComplaintLocation(float latitude, float longitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
