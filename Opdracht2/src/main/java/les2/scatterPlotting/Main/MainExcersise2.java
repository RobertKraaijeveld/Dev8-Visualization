package les2.scatterPlotting.Main;

import java.io.File;

import les2.scatterPlotting.Plotting.*;
import les2.scatterPlotting.textReading.StudentDataFile;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import les2.scatterPlotting.textReading.TextReader;

public class MainExcersise2 extends PApplet
{
    private static TextReader READER;
    private static List<ScatterPlot> Plots = new ArrayList<>();
    private static final Float APPLET_WIDTH = 1080.0f;
    private static final Float APPLET_HEIGHT = 900.0f;

    public void settings()
    {
        noSmooth();
        noLoop();
        size(APPLET_WIDTH.intValue(), APPLET_HEIGHT.intValue());
    }

    public void setup() {
    }

    public void draw()
    {
        try
        {
            scale(0.5f);
            drawInformativeBoxForMatrix();
            drawPoints();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Ugly hard-code ahead, might wanna hold your breath
    private void drawPoints()
    {
        int plotCounter = 0;

        for (ScatterPlot scatterPlot : Plots)
        {
            switch (plotCounter)
            {
                case (0):
                    translate(340.0f, 50.0f);
                    plotCounter++;
                    break;

                case (1):
                    translate(200.0f, 0.0f);
                    plotCounter++;
                    break;

                case (2):
                    translate(250.0f, 0.0f);
                    plotCounter++;
                    break;

                case (3):
                    translate(-750.0f, 450.0f);
                    plotCounter++;
                    break;

                case (4):
                    translate(500.0f, -200.0f);
                    plotCounter++;
                    break;

                case (5):
                    translate(300.0f, 0.0f);
                    plotCounter++;
                    break;

                case (6):
                    translate(-1085.0f, 600.0f);
                    plotCounter++;
                    break;

                case (7):
                    translate(440.0f, -180.0f);
                    plotCounter++;
                    break;

                case (8):
                    translate(600.0f, 180.0f);
                    plotCounter++;
                    break;

                case (9):
                    translate(-1080.0f, 350.0f);
                    plotCounter++;
                    break;

                case (10):
                    translate(450.0f, -150.0f);
                    plotCounter++;
                    break;

                case (11):
                    translate(370.0f, 200.0f);
                    plotCounter++;
                    break;
            }


            for (Point p : scatterPlot.getPointPositions())
            {
                //Changing the point values lightly to ensure a smooth fit in the applet window
                ellipse(p.getX() + 20, p.getY() + 20, 5, 5);
            }
        }
    }

    private void drawInformativeBoxForMatrix()
    {
        String[] boxTitles = new String[]{"ANA", "DEV", "PRJ", "SKL"};
        textSize(50);
        int basePosition = 200;

        text(boxTitles[0], 200, 200);
        text(boxTitles[1], 550, 650);
        text(boxTitles[2], 975, 1050);
        text(boxTitles[3], 1300, 1400);
    }


    public static void main(String[] args)
    {
        try
        {
            File studentCijfersTextFile = new File("studentcijfers.txt");
            TextReader textReader = new TextReader(studentCijfersTextFile);
            setTextReaderToUse(textReader);

            GenericPair<Float, Float> widthHeightPair = new GenericPair<>(300.0f, 400.0f);
            GenericPair<String, String> valueTypes = new GenericPair<>("DEV", "ANA");
            ScatterPlotMetaData meta = new ScatterPlotMetaData(valueTypes, widthHeightPair);

            StudentDataFile studentData = textReader.createStudentDataFileInstance();


            ArrayList<GenericPair<Float, Float>> AnaDevXYPairs =
                    studentData.createXYPairsOfGivenTypes("ANA", "DEV");

            ArrayList<GenericPair<Float, Float>> AnaPrjXYPairs =
                    studentData.createXYPairsOfGivenTypes("ANA", "PRJ");

            ArrayList<GenericPair<Float, Float>> AnaSklXYPairs =
                    studentData.createXYPairsOfGivenTypes("ANA", "SKL");


            ArrayList<GenericPair<Float, Float>> DevAnaXYPairs =
                    studentData.createXYPairsOfGivenTypes("DEV", "ANA");

            ArrayList<GenericPair<Float, Float>> DevPrjXYPairs =
                    studentData.createXYPairsOfGivenTypes("DEV", "PRJ");

            ArrayList<GenericPair<Float, Float>> DevSklXYPairs =
                    studentData.createXYPairsOfGivenTypes("DEV", "SKL");


            ArrayList<GenericPair<Float, Float>> PrjAnaXYPairs =
                    studentData.createXYPairsOfGivenTypes("PRJ", "ANA");

            ArrayList<GenericPair<Float, Float>> PrjDevXYPairs =
                    studentData.createXYPairsOfGivenTypes("PRJ", "DEV");

            ArrayList<GenericPair<Float, Float>> PrjSklXYPairs =
                    studentData.createXYPairsOfGivenTypes("PRJ", "SKL");


            ArrayList<GenericPair<Float, Float>> SklAnaXYPairs =
                    studentData.createXYPairsOfGivenTypes("SKL", "ANA");

            ArrayList<GenericPair<Float, Float>> SklDevXYPairs =
                    studentData.createXYPairsOfGivenTypes("SKL", "DEV");

            ArrayList<GenericPair<Float, Float>> SklPrjXYPairs =
                    studentData.createXYPairsOfGivenTypes("SKL", "PRJ");


            ValuesConverter converter = new ValuesConverter(studentData, meta);
            ArrayList<Point> devAnaPoints = converter.convertValuesToPoints(DevAnaXYPairs);
            ArrayList<Point> prjAnaPoints = converter.convertValuesToPoints(PrjAnaXYPairs);
            ArrayList<Point> sklAnaPoints = converter.convertValuesToPoints(SklAnaXYPairs);

            ArrayList<Point> anaDevPoints = converter.convertValuesToPoints(DevAnaXYPairs);
            ArrayList<Point> prjDevPoints = converter.convertValuesToPoints(PrjDevXYPairs);
            ArrayList<Point> sklDevPoints = converter.convertValuesToPoints(SklDevXYPairs);

            ArrayList<Point> anaPrjPoints = converter.convertValuesToPoints(PrjAnaXYPairs);
            ArrayList<Point> devPrjPoints = converter.convertValuesToPoints(PrjDevXYPairs);
            ArrayList<Point> sklPrjPoints = converter.convertValuesToPoints(SklPrjXYPairs);

            ArrayList<Point> anaSklPoints = converter.convertValuesToPoints(SklAnaXYPairs);
            ArrayList<Point> devSklPoints = converter.convertValuesToPoints(SklDevXYPairs);
            ArrayList<Point> prjSklPoints = converter.convertValuesToPoints(SklPrjXYPairs);


            Point xAxisPointOne = new Point(10.0f, 330.f);
            Point xAxisPointTwo = new Point(420.0f, 330.f);
            GenericPair<Point, Point> xAxisPoints = new GenericPair<>(xAxisPointOne, xAxisPointTwo);

            Point yAxisPointOne = new Point(10.0f, 20.0f);
            Point yAxisPointTwo = new Point(10.0f, 330.f);
            GenericPair<Point, Point> yAxisPoints = new GenericPair<>(yAxisPointOne, yAxisPointTwo);

            Axises axises = new Axises(xAxisPoints, yAxisPoints);


            ScatterPlot devAnaPlot = new ScatterPlot(devAnaPoints, axises);
            ScatterPlot prjAnaPlot = new ScatterPlot(prjAnaPoints, axises);
            ScatterPlot sklAnaPlot = new ScatterPlot(sklAnaPoints, axises);

            ScatterPlot anaDevPlot = new ScatterPlot(anaDevPoints, axises);
            ScatterPlot prjDevPlot = new ScatterPlot(prjDevPoints, axises);
            ScatterPlot sklDevPlot = new ScatterPlot(sklDevPoints, axises);

            ScatterPlot anaPrjPlot = new ScatterPlot(anaPrjPoints, axises);
            ScatterPlot devPrjPlot = new ScatterPlot(devPrjPoints, axises);
            ScatterPlot sklPrjPlot = new ScatterPlot(sklPrjPoints, axises);

            ScatterPlot anaSklPlot = new ScatterPlot(anaSklPoints, axises);
            ScatterPlot devSklPlot = new ScatterPlot(devSklPoints, axises);
            ScatterPlot prjSklPlot = new ScatterPlot(prjSklPoints, axises);

            Plots.add(devAnaPlot);
            Plots.add(prjAnaPlot);
            Plots.add(sklAnaPlot);


            Plots.add(anaDevPlot);
            Plots.add(prjDevPlot);
            Plots.add(sklDevPlot);


            Plots.add(anaPrjPlot);
            Plots.add(devPrjPlot);
            Plots.add(sklPrjPlot);

            Plots.add(anaSklPlot);
            Plots.add(devSklPlot);
            Plots.add(prjSklPlot);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        PApplet.main(new String[]{MainExcersise2.class.getName()});
    }

    /*
    * Methods to make sure a uniform textReader (and included file) is used when running an assignment.
     */

    private static void setTextReaderToUse(TextReader reader)
    {
        READER = reader;
    }

    private static TextReader getReaderToUse()
    {
        return READER;
    }
}
