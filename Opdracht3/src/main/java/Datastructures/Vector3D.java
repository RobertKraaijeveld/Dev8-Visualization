package Datastructures;

/**
 *
 * @author gover_000
 */
public class Vector3D
{
    private float xValue;
    private float yValue;
    private float zValue;

    public Vector3D(){}

    public Vector3D(float xValue, float yValue, float zValue)
    {
        this.xValue = xValue;
        this.yValue = yValue;
        this.zValue = zValue;
    }

    public float getX() {
        return this.xValue;
    }

    public float getY() {
        return this.yValue;
    }
    
    public float getZ() {
        return this.zValue;
    }

    public void setxValue(float xValue) {
        this.xValue = xValue;
    }

    public void setyValue(float yValue) {
        this.yValue = yValue;
    }

    public void setzValue(float zValue) {
        this.zValue = zValue;
    }

}
