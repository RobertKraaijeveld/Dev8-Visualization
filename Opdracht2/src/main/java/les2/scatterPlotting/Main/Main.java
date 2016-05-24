package les2.scatterPlotting.Main;

import java.io.File;

import les2.scatterPlotting.Plotting.ScatterPlot;
import les2.scatterPlotting.Plotting.ScatterPlotMetaData;
import les2.scatterPlotting.Plotting.valuesConverter;
import les2.scatterPlotting.textReading.TextFile;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import les2.scatterPlotting.textReading.TextReader;

public class Main extends PApplet
{
  
    public void settings() {
        
    }

    public void setup()
    {
        
    }

    public void draw()
    {
        
    }

    public void drawLegend()
    {
        
    }

    public static void main(String[] args)
    {
        try 
        {
            File file = new File("scatterplot.txt");
            TextReader textReader = new TextReader(file);

            TextFile textFile = textReader.createTextFileInstance();

            GenericPair<Float, Float> widthHeightPair = new GenericPair<>(600.0f, 600.0f);
            ScatterPlotMetaData meta = new ScatterPlotMetaData(widthHeightPair);
            valuesConverter valuesConverter = new valuesConverter(textFile, meta);

            ScatterPlot plot = new ScatterPlot(valuesConverter.convertValuesToPoints());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
        PApplet.main(new String[] { Main.class.getName() });
    }
}
