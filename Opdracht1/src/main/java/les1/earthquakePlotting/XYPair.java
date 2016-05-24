package les1.earthquakePlotting;

/**
 * Created by Kraaijeveld on 9-5-2016.
 */
public class XYPair
{
    private float xValue;
    private float yValue;

    public XYPair(float xValue, float yValue)
    {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public float getxValue() {
        return xValue;
    }

    public float getyValue() {
        return yValue;
    }

    public void setxValue(float xValue) {
        this.xValue = xValue;
    }

    public void setyValue(float yValue) {
        this.yValue = yValue;
    }
}
