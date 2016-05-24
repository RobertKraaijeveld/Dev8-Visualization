package les2.scatterPlotting.Main;

import java.io.File;

import les2.scatterPlotting.Plotting.Point;
import les2.scatterPlotting.Plotting.ScatterPlot;
import les2.scatterPlotting.Plotting.ScatterPlotMetaData;
import les2.scatterPlotting.Plotting.valuesConverter;
import les2.scatterPlotting.textReading.TextFile;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

import les2.scatterPlotting.textReading.TextReader;

public class Main extends PApplet
{
    private static ScatterPlot MainScatterPlot;
    private static File FILE = new File("scatterplot.txt");
    private static TextReader READER = new TextReader(FILE);
    private static final Float APPLET_WIDTH = 640.0f;
    private static final Float APPLET_HEIGHT = 470.0f;

    public void settings()
    {
        noSmooth();
        size(APPLET_WIDTH.intValue(), APPLET_HEIGHT.intValue());
    }

    public void setup()
    {

    }

    public void draw()
    {
        try
        {
            drawPoints();
            drawAxises();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void drawPoints()
    {
        for(Point p : MainScatterPlot.getPointPositions())
        {
            //Chaing the point values lightly to ensure a smooth fit in the applet window
            ellipse(p.getX() + 20, p.getY() + 20, 5, 5);
        }
    }

    //This method is basically CSS, feast yer' eyes
    private void drawAxises() throws Exception
    {
        //X-AXIS: FINE
        TextFile unmodifiedTextFile = READER.createTextFileInstance();

        Float XaxisBeginValue = unmodifiedTextFile.getSmallestOrBiggestValue("X", "small");
        Float XaxisEndValue = unmodifiedTextFile.getSmallestOrBiggestValue("X", "big");

        Float beginOfXaxisPosition = XaxisBeginValue;
        line(beginOfXaxisPosition, 330.0f, XaxisEndValue + 350, 330.0f);

        text(XaxisBeginValue.intValue(), beginOfXaxisPosition, 350.0f);

        //Magic number 425 ensures good length of line
        Float middleOfXaxisPosition = (XaxisEndValue + 350) / 2;
        Float middleXpointValue = XaxisEndValue / 2;
        text(middleXpointValue.intValue(), middleOfXaxisPosition, 350.0f);

        Float endOfXaxisPosition = XaxisEndValue + 350;
        text(XaxisEndValue.intValue(), endOfXaxisPosition, 350.0f);


        //Y-AXIS

        Float YaxisTopValue = unmodifiedTextFile.getSmallestOrBiggestValue("Y", "big");
        Float YaxisBottomValue = unmodifiedTextFile.getSmallestOrBiggestValue("Y", "small");
        //248

        Float YAxisLength = 330.0f;

        line(10.0f, 20.0f, 10.0f, YAxisLength);

        //IMPORTANT: We want the biggest Y values on top, so the lower positions get the higher values.
        text(YaxisTopValue.intValue(), 10.0f,  15.0f);

        Float middleOfYaxisPosition = YAxisLength / 2;
        Float middleYpointValue = YaxisTopValue / 2;
        text(middleYpointValue.intValue(), 20.0f, middleOfYaxisPosition);

        Float endOfYAxisPosition = YAxisLength;
        text(YaxisBottomValue.intValue(), 20.0f, endOfYAxisPosition - 10);

    }


    public static void main(String[] args)
    {
        try 
        {
            TextFile textFile = READER.createTextFileInstance();

            GenericPair<Float, Float> widthHeightPair = new GenericPair<>(300.0f, 400.0f);
            ScatterPlotMetaData meta = new ScatterPlotMetaData(widthHeightPair);

            valuesConverter valuesConverter = new valuesConverter(textFile, meta);

            //Plot doesnt do much right now, but will come in handy later when drawing the matrix.
            ScatterPlot plot = new ScatterPlot(valuesConverter.convertValuesToPoints());
            MainScatterPlot = plot;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
        PApplet.main(new String[] { Main.class.getName() });
    }
}
