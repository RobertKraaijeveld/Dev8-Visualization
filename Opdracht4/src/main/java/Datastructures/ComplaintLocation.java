package Datastructures;

/**
 * Created by Kraaijeveld on 21-6-2016.
 */

//I should have coded this to an interface. Food for thought.
public class ComplaintLocation
{
    private String severity;
    private float latitude;
    private float longitude;

    public ComplaintLocation(String severity, float latitude, float longitude)
    {
        this.severity = severity;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getSeverity() {
        return severity;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
