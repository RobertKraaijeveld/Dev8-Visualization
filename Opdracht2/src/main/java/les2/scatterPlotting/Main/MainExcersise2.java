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
        background(220);
        fill(50);

        try
        {
            scale(0.5f);
            drawLegend();
            drawAxises();
            drawInformativeBoxForMatrix();
            drawPoints();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Ugly hard-code ahead, might wanna hold your breath
    private void drawAxises() throws Exception
    {
        //X axises (Running above the plots)
        textSize(22.0f);
        Float lowestAnaValue = READER.createStudentDataFileInstance().getSmallestOrBiggestValue("X", "ANA", "small");
        Float highestAnaValue = READER.createStudentDataFileInstance().getSmallestOrBiggestValue("X", "ANA", "big");
        text(lowestAnaValue.toString(), 20.0f, 20.0f);
        text(highestAnaValue.toString(), 370.0f, 20.0f);
        line(20.0f, 20.0f, 380.0f, 20.0f);

        Float lowestDevValue = READER.createStudentDataFileInstance().getSmallestOrBiggestValue("X", "DEV", "small");
        Float highestDevValue = READER.createStudentDataFileInstance().getSmallestOrBiggestValue("X", "DEV", "big");
        text(lowestDevValue.toString(), 390.0f, 40.0f);
        text(highestDevValue.toString(), 790.0f, 20.0f);
        line(390.0f, 20.0f, 790.0f, 20.0f);

        Float lowestPrjValue = READER.createStudentDataFileInstance().getSmallestOrBiggestValue("X", "PRJ", "small");
        Float highestPrjValue = READER.createStudentDataFileInstance().getSmallestOrBiggestValue("X", "PRJ", "big");
        text(lowestPrjValue.toString(), 850.0f, 20.0f);
        text(highestPrjValue.toString(), 1300.0f, 20.0f);
        line(850.0f,20.0f,1300.0f,20.0f);

        Float lowestSklValue = READER.createStudentDataFileInstance().getSmallestOrBiggestValue("X", "SKL", "small");
        Float highestSklValue = READER.createStudentDataFileInstance().getSmallestOrBiggestValue("X", "SKL", "big");
        text(lowestSklValue.toString(), 1330.0f, 40.0f);
        text(highestSklValue.toString(), 1390.0f, 20.0f);
        line(1330.0f, 20.0f, 1400.0f, 20.0f);


        //Y-Axis (Lowest and highest values are no different, of course)
        text(highestAnaValue.toString(), 30.0f, 60.0f);
        text(lowestAnaValue.toString(), 30.0f,  360.0f);
        line(20.0f, 60.0f, 20.0f, 370.0f);

        text(highestDevValue.toString(),  40.0f, 425.0f);
        text(lowestDevValue.toString(),  30.0f, 770.0f);
        line(20.0f, 435.0f, 20.0f, 780.0f);

        text(highestPrjValue.toString() , 20.0f, 820.0f);
        text(lowestPrjValue.toString(), 20.0f, 1160.0f);
        line(20.0f,830.0f, 20.0f,1175.0f);

        text(highestSklValue.toString(), 40.0f, 1210.0f);
        text(lowestSklValue.toString(), 20.0f, 1310.0f);
        line(20.0f, 1230.0f, 20.0f, 1285.0f);
    }

    private void drawInformativeBoxForMatrix()
    {
        String[] boxTitles = new String[]{"ANA", "DEV", "PRJ", "SKL"};
        textSize(50);
        int basePosition = 200;

        text(boxTitles[0], 200, 200);
        text(boxTitles[1], 550, 550);
        text(boxTitles[2], 975, 975);
        text(boxTitles[3], 1350, 1250);
    }

    private void drawPoints()
    {
        int plotCounter = 0;

        for (ScatterPlot scatterPlot : Plots)
        {
            switch (plotCounter)
            {
                case (0):
                        translate(360.0f, 50.0f);
                        plotCounter++;
                        break;

                case (1):
                        translate(200.0f, 0.0f);
                        plotCounter++;
                        break;

                case (2):
                        translate(280.0f, 0.0f);
                        plotCounter++;
                        break;

                case (3):
                        translate(-1050.0f, 200.0f);
                        plotCounter++;
                        break;

                case (4):
                        translate(750.0f, 0.0f);
                        plotCounter++;
                        break;

                case (5):
                        translate(330.0f, 0.0f);
                        plotCounter++;
                        break;

                case (6):
                        translate(-1070.0f, 600.0f);
                        plotCounter++;
                        break;

                case (7):
                        translate(630.0f, 0.0f);
                        plotCounter++;
                        break;

                case (8):
                        translate(430.0f, 0.0f);
                        plotCounter++;
                        break;

                case (9):
                        scale(0.97f);
                        translate(-1020.0f, 300.0f);
                        plotCounter++;
                        break;

                case (10):
                        scale(0.97f);
                        translate(650.0f, 0.0f);
                        plotCounter++;
                        break;

                case (11):
                        scale(0.97f);
                        translate(180.0f, 0.0f);
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

    private void drawLegend()
    {
        textSize(30);
        text("This scattermatrix contains the marks for the following courses at the Rotterdam CMI:", 100.0f, 1400.0f);
        text("- ANA Analysis: Courses on UML, UX and testing.", 100.0f, 1450.0f);
        text("- DEV Development: Development of applications, algorithms. Also includes statistics courses.", 100.0f, 1500.0f);
        text("- PRJ The project: A course consisting of a large practical programming assignment.", 100.0f, 1550.0f);
        text("- SKL Skills: Courses on business skills, english language, formal writing etc.", 100.0f, 1600.0f);
        text("Marks are given according to the dutch system, ranging between 1 (lowest) and 10 (highest).", 100.0f, 1700.0f);
        text("(Some courses, like skills, include only subranges however.)" , 100.0f, 1750.0f);
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


            ArrayList<GenericPair<Float, Float>> DevAnaXYPairs =
                    studentData.createXYPairsOfGivenTypes("DEV", "ANA");

            ArrayList<GenericPair<Float, Float>> PrjAnaXYPairs =
                    studentData.createXYPairsOfGivenTypes("PRJ", "ANA");

            ArrayList<GenericPair<Float, Float>> SklAnaXYPairs =
                    studentData.createXYPairsOfGivenTypes("SKL", "ANA");


            ArrayList<GenericPair<Float, Float>> AnaDevXYPairs =
                    studentData.createXYPairsOfGivenTypes("ANA", "DEV");

            ArrayList<GenericPair<Float, Float>> PrjDevXYPairs =
                    studentData.createXYPairsOfGivenTypes("PRJ", "DEV");

            ArrayList<GenericPair<Float, Float>> SklDevXYPairs =
                    studentData.createXYPairsOfGivenTypes("SKL", "DEV");


            ArrayList<GenericPair<Float, Float>> AnaPrjXYPairs =
                    studentData.createXYPairsOfGivenTypes("ANA", "PRJ");

            ArrayList<GenericPair<Float, Float>> DevPrjXYPairs =
                    studentData.createXYPairsOfGivenTypes("DEV", "PRJ");

            ArrayList<GenericPair<Float, Float>> SklPrjXYPairs =
                    studentData.createXYPairsOfGivenTypes("SKL", "PRJ");


            ArrayList<GenericPair<Float, Float>> AnaSklXYPairs =
                    studentData.createXYPairsOfGivenTypes("ANA", "SKL");

            ArrayList<GenericPair<Float, Float>> DevSklXYPairs =
                    studentData.createXYPairsOfGivenTypes("DEV", "SKL");

            ArrayList<GenericPair<Float, Float>> PrjSklXYPairs =
                    studentData.createXYPairsOfGivenTypes("PRJ", "SKL");


            ValuesConverter converter = new ValuesConverter(studentData, meta);
            ArrayList<Point> devAnaPoints = converter.convertValuesToPoints(DevAnaXYPairs);
            ArrayList<Point> prjAnaPoints = converter.convertValuesToPoints(PrjAnaXYPairs);
            ArrayList<Point> sklAnaPoints = converter.convertValuesToPoints(SklAnaXYPairs);

            ArrayList<Point> anaDevPoints = converter.convertValuesToPoints(AnaDevXYPairs);
            ArrayList<Point> prjDevPoints = converter.convertValuesToPoints(PrjDevXYPairs);
            ArrayList<Point> sklDevPoints = converter.convertValuesToPoints(SklDevXYPairs);

            ArrayList<Point> anaPrjPoints = converter.convertValuesToPoints(AnaPrjXYPairs);
            ArrayList<Point> devPrjPoints = converter.convertValuesToPoints(DevPrjXYPairs);
            ArrayList<Point> sklPrjPoints = converter.convertValuesToPoints(SklPrjXYPairs);

            ArrayList<Point> anaSklPoints = converter.convertValuesToPoints(AnaSklXYPairs);
            ArrayList<Point> devSklPoints = converter.convertValuesToPoints(DevSklXYPairs);
            ArrayList<Point> prjSklPoints = converter.convertValuesToPoints(PrjSklXYPairs);

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
