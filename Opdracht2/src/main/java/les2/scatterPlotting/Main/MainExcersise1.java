package les2.scatterPlotting.Main;

import java.io.File;

import les2.scatterPlotting.Plotting.*;
import les2.scatterPlotting.textReading.StudentDataFile;
import les2.scatterPlotting.textReading.TextFile;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import les2.scatterPlotting.textReading.TextReader;

/**
 * Created by Kraaijeveld on 28-5-2016.
 */
public class MainExcersise1 extends PApplet
{
        private static File FILE = new File("scatterplot.txt");
        private static TextReader READER = new TextReader(FILE);
        private static List<ScatterPlot> Plots = new ArrayList<>();
        private static final Float APPLET_WIDTH = 1080.0f;
        private static final Float APPLET_HEIGHT = 900.0f;

        public void settings() {
            noSmooth();
            noLoop();
            size(APPLET_WIDTH.intValue(), APPLET_HEIGHT.intValue());
        }

        public void setup() {
        }

        public void draw() {
            background(220);
            fill(50,100);
            try
            {
                drawPoints();
                drawAxises();
                drawAxisValues();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            drawLegend();
        }
        
        private void drawLegend() 
        {
        fill(220);
        rect(450,0, 220, 120);
        fill(0);
        text("LEGEND", 455,10);
        
        text("Size of point are based on CAT value:", 455, 45);
        text("CAT 1: ", 455, 60);
        ellipse(495, 55, 4.5f, 4.5f);
        text("CAT 2: ", 455, 75);
        ellipse(500, 70, 9f, 9f);
        text("CAT 3: ", 455, 90);
        ellipse(510, 85, 13.5f, 13.5f);
        text("CAT 4: ", 455, 105);
        ellipse(520, 100, 18f, 18f);
        }

        private void drawPoints() throws Exception
        {
            ArrayList<Integer> catValues = READER.createTextFileInstance().getCatValues();

            for (ScatterPlot scatterPlot : Plots)
            {
                for (Point p : scatterPlot.getPointPositions())
                {
                    //Changing the point values lightly to ensure a smooth fit in the applet window
                    int currentPointIndex = scatterPlot.getPointPositions().indexOf(p);
                    Float size = catValues.get(currentPointIndex) * 4.5f;


                    ellipse(p.getX() + 20, p.getY() + 20, size, size);
                }
            }
        }


        private void drawAxises()
        {
            for (ScatterPlot scatterPlot : Plots)
            {
                Axises thisPlotsAxises = scatterPlot.getAxises();

                //X-axis
                line(thisPlotsAxises.getxAxisPoints().getLeftValue().getX(), thisPlotsAxises.getxAxisPoints().getLeftValue().getY(),
                        thisPlotsAxises.getxAxisPoints().getRightValue().getX(), thisPlotsAxises.getxAxisPoints().getRightValue().getY());

                //Y-axis
                line(thisPlotsAxises.getyAxisPoints().getLeftValue().getX(), thisPlotsAxises.getyAxisPoints().getLeftValue().getY(),
                        thisPlotsAxises.getyAxisPoints().getRightValue().getX(), thisPlotsAxises.getyAxisPoints().getRightValue().getY());
            }
        }

        //This method contains so many magic numbers,
        //I'm considering renaming it to 'Hogwarts'
        private void drawAxisValues() throws Exception
        {
            //X-AXIS
            TextFile unmodifiedTextFile = READER.createTextFileInstance();

            Float XaxisBeginValue = unmodifiedTextFile.getSmallestOrBiggestValue("X", "small");
            Float XaxisEndValue = unmodifiedTextFile.getSmallestOrBiggestValue("X", "big");

            text(XaxisBeginValue.intValue(), 10.0f, 350.0f);

            //Magic number 425 ensures good length of line
            Float middleOfXaxisPosition = (XaxisEndValue + 350) / 2;
            Float middleXpointValue = XaxisEndValue / 2;
            text(middleXpointValue.intValue(), middleOfXaxisPosition, 350.0f);

            Float endOfXaxisPosition = XaxisEndValue + 350;
            text(XaxisEndValue.intValue(), endOfXaxisPosition, 350.0f);


            //Y-AXIS
            Float YaxisTopValue = unmodifiedTextFile.getSmallestOrBiggestValue("Y", "big");
            Float YaxisBottomValue = unmodifiedTextFile.getSmallestOrBiggestValue("Y", "small");

            //IMPORTANT: We want the biggest Y values on top, so the lower positions get the higher values.
            text(YaxisTopValue.intValue(), 10.0f, 15.0f);

            Float middleOfYaxisPosition = 330.f / 2;
            Float middleYpointValue = YaxisTopValue / 2;
            text(middleYpointValue.intValue(), 20.0f, middleOfYaxisPosition);

            Float endOfYAxisPosition = 330.f;
            text(YaxisBottomValue.intValue(), 20.0f, endOfYAxisPosition - 10);

        }

        public static void main(String[] args)
        {
            try
            {
                TextFile textFile = READER.createTextFileInstance();

                GenericPair<Float, Float> widthHeightPair = new GenericPair<>(300.0f, 400.0f);
                ScatterPlotMetaData meta = new ScatterPlotMetaData(null, widthHeightPair);

                ArrayList<GenericPair<Float, Float>> XYPairs = textFile.getLines();
                ValuesConverter ValuesConverter = new ValuesConverter(textFile, meta);
                ArrayList<Point> pointsToBeDrawn = ValuesConverter.convertValuesToPoints(XYPairs);

                Point xAxisPointOne = new Point(10.0f, 330.f);
                Point xAxisPointTwo = new Point(420.0f, 330.f);
                GenericPair<Point, Point> xAxisPoints = new GenericPair<>(xAxisPointOne, xAxisPointTwo);

                Point yAxisPointOne = new Point(10.0f, 20.0f);
                Point yAxisPointTwo = new Point(10.0f, 330.f);
                GenericPair<Point, Point> yAxisPoints = new GenericPair<>(yAxisPointOne, yAxisPointTwo);

                Axises axises = new Axises(xAxisPoints, yAxisPoints);

                ScatterPlot plot = new ScatterPlot(pointsToBeDrawn, axises);
                Plots.add(plot);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            PApplet.main(new String[]{MainExcersise1.class.getName()});
        }

}
