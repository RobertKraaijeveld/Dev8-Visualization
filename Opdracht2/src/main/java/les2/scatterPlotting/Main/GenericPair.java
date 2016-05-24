package les2.scatterPlotting.Main;

/**
 * Created by Kraaijeveld on 7-5-2016. (Hurray for generics!)
 */
public class GenericPair<L, R>
{
    private final L leftValue;
    private final R rightValue;

    public GenericPair(L left, R right)
    {
        this.leftValue = left;
        this.rightValue = right;
    }

    public L getLeftValue() {
        return leftValue;
    }

    public R getRightValue() {
        return rightValue;
    }

}
