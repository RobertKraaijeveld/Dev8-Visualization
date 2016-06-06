package Datastructures;

/**
 * Created by Kraaijeveld on 31-5-2016.
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
