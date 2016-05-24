package les2.scatterPlotting.Plotting;

/**
 *
 * @author gover_000
 */
public class Point
{
    private Float X;
    private Float Y;
    
    public Point(Float X, Float Y)
    {
        this.X = X;
        this.Y = Y;
    }

    public Float getX() { return this.X; }
    public Float getY() { return this.Y; }

    public void setX(Float X) { this.X = X; }
    public void setY(Float Y) { this.Y = Y; }
}
