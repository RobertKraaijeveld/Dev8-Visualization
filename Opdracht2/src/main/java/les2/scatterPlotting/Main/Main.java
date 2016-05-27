package les2.scatterPlotting.Main;

import java.io.File;

import les2.scatterPlotting.Plotting.*;
import les2.scatterPlotting.textReading.StudentDataFile;
import les2.scatterPlotting.textReading.TextFile;
import processing.core.PApplet;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import les2.scatterPlotting.textReading.TextReader;

public class Main extends PApplet
{
    private static TextReader READER;
    private static List<ScatterPlot> Plots = new ArrayList<>();
    private static final Float APPLET_WIDTH = 640.0f;
    private static final Float APPLET_HEIGHT = 470.0f;

    public void settings()
    {
        noSmooth();
        size(APPLET_WIDTH.intValue(), APPLET_HEIGHT.intValue());
    }

    public void setup() {}

    public void draw()
    {
        TextReader READER = getReaderToUse();
        try
        {
            drawPoints();
            drawAxises();
            drawAxisValues(READER);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void drawPoints()
    {
        for(ScatterPlot scatterPlot : Plots)
        {
            for (Point p : scatterPlot.getPointPositions())
            {
                //Changing the point values lightly to ensure a smooth fit in the applet window
                ellipse(p.getX() + 20, p.getY() + 20, 5, 5);
            }
        }
    }

    private void drawAxises()
    {
        for(ScatterPlot scatterPlot : Plots)
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
    private void drawAxisValues(TextReader READER) throws Exception
    {
        //TODO: TEXTFILE INSTANCE USAGE
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

    private static String getUsersAssignmentChoice()
    {
        System.out.println("Type '1' for assignment 1, type '2' for assignment 2.");

        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();

        if (answer == null || answer.equals(""))
            return getUsersAssignmentChoice();
        else
            return answer;
    }

    public static void main(String[] args)
    {
        if (getUsersAssignmentChoice().equals("1"))
        {
            try
            {
                File FILE = new File("scatterplot.txt");
                TextReader READER = new TextReader(FILE);

                //TODO: TEXTFILE INSTANCE USGAE
                TextFile textFile = READER.createTextFileInstance();

                GenericPair<Float, Float> widthHeightPair = new GenericPair<>(300.0f, 400.0f);
                ScatterPlotMetaData meta = new ScatterPlotMetaData(widthHeightPair);

                valuesConverter valuesConverter = new valuesConverter(textFile, meta);

                //Start somewhere, add multiplicants. Maybe that wont even be necessary if using translate() ?
                Point xAxisPointOne = new Point(10.0f, 330.f);
                Point xAxisPointTwo = new Point(420.0f, 330.f);
                GenericPair<Point, Point> xAxisPoints = new GenericPair<>(xAxisPointOne, xAxisPointTwo);

                Point yAxisPointOne = new Point(10.0f, 20.0f);
                Point yAxisPointTwo = new Point(10.0f, 330.f);
                GenericPair<Point, Point> yAxisPoints = new GenericPair<>(yAxisPointOne, yAxisPointTwo);

                Axises axises = new Axises(xAxisPoints, yAxisPoints);

                ScatterPlot plot = new ScatterPlot(valuesConverter.convertValuesToPoints(), axises);
                Plots.add(plot);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            PApplet.main(new String[]{Main.class.getName()});
        }
        else if (getUsersAssignmentChoice().equals("2"))
        {
            try
            {
                File studentCijfersTextFile = new File("studentcijfers.txt");
                TextReader textReader = new TextReader(studentCijfersTextFile);
                setTextReaderToUse(textReader);

                textReader.createStudentDataFileInstance();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Assignment not recognized.");
            getUsersAssignmentChoice();
        }
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
