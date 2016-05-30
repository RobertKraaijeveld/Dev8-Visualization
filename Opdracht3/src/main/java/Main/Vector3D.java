package Main;

/**
 *
 * @author gover_000
 */
public class Vector3D<X, Y, Z> 
{
    private final X xValue;
    private final Y yValue;
    private final Z zValue;

    public Vector3D(X left, Y right, Z depth)
    {
        this.xValue = left;
        this.yValue = right;
        this.zValue = depth;
    }

    public X getX() {
        return xValue;
    }

    public Y getY() {
        return yValue;
    }
    
    public Z getZ() {
        return zValue;
    }
}
