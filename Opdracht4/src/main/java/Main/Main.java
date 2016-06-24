package Main;

import AdressConverting.AdressConverter;
import Datastructures.ComplaintLocation;
import Datastructures.GenericPair;
import Datastructures.RawAdress;
import FileParser.CrematoriumCsvParser;
import FileParser.CsvParser;
import FileParser.ParsedAdressesFile;
import MapDrawing.LatLongConverter;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.ArrayList;


/**
 * @author gover_000
 */

public class Main extends PApplet
{
    private static GenericPair<Integer, Integer> appletWidthHeightMaximums = new GenericPair<>(1280, 920);
    private static GenericPair<Float, Float> minMaxLongCoordinates = new GenericPair<>(4.256f, 4.712f);
    private static GenericPair<Float, Float> minMaxLatCoordinates = new GenericPair<>(51.837f, 51.998f);

    private static PImage rotterdamImage;

    private static ArrayList<GenericPair<Float, Float>> crematoriumPointsToDraw;
    private static ArrayList<GenericPair<Float, Float>> complaintPointsToDraw;

    public void settings()
    {
        size(appletWidthHeightMaximums.getLeftValue(), appletWidthHeightMaximums.getRightValue());
        noSmooth();
    }

    public void setup()
    {
        stroke(0);
        noLoop();
        rotterdamImage = loadImage("rotterdam.png");
    }

    public void draw()
    {
        background(1);
        image(rotterdamImage, 0, 0, appletWidthHeightMaximums.getLeftValue(), appletWidthHeightMaximums.getRightValue());

        for (GenericPair<Float, Float> crematoriumPointToBeDrawn : crematoriumPointsToDraw)
        {
            fill(255, 0, 0);
            ellipse(crematoriumPointToBeDrawn.getLeftValue(), crematoriumPointToBeDrawn.getRightValue(), 10, 10);
        }

        for (GenericPair<Float, Float> complaintPointToBeDrawn : complaintPointsToDraw)
        {
            fill(255, 127);
            ellipse(complaintPointToBeDrawn.getLeftValue(), complaintPointToBeDrawn.getRightValue(), 10, 10);
        }
    }

    private static void parseComplaintCsvFile()
    {
        try
        {
            File outputFile = new File("outputFile.csv");

            int complaintTypeIndexInCsv = 6;
            int cityIndexInCsv = 5;
            GenericPair<Integer, Integer> complaintAndCityIndexInCsvPair = new GenericPair<>(complaintTypeIndexInCsv, cityIndexInCsv);

            ArrayList<Integer> desiredIndexesInFile = new ArrayList<>();
            desiredIndexesInFile.add(3);
            desiredIndexesInFile.add(4);
            desiredIndexesInFile.add(5);

            CsvParser parser = new CsvParser(desiredIndexesInFile);
            parser.setComplaintAndCityLineValueIndexes(complaintAndCityIndexInCsvPair);

            ParsedAdressesFile parsedAdressesFile = parser.parseGivenFile("klachten.csv");
            ArrayList<RawAdress> parsedAdresses = parsedAdressesFile.getAdresses();

            AdressConverter adressConverter = new AdressConverter(outputFile);
            ArrayList<ComplaintLocation> complaintLatLongList = adressConverter.convertRawAdressesListToComplaintLocationList(parsedAdresses);

            System.out.println("Total amount of stench complaints within rotterdam: " + complaintLatLongList.size());

            LatLongConverter latLongConverter = new LatLongConverter(minMaxLongCoordinates, minMaxLatCoordinates, appletWidthHeightMaximums);
            complaintPointsToDraw = latLongConverter.convertGivenCoordinateListToXYValues(complaintLatLongList);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void parseCrematoriumsCsvFile()
    {
        try
        {
            File crematoriumsOutputFile = new File("crematoriumsOutputFile.csv");

            ArrayList<Integer> desiredIndexesInCrematoriumsFile = new ArrayList<>();
            desiredIndexesInCrematoriumsFile.add(0);
            desiredIndexesInCrematoriumsFile.add(1);
            desiredIndexesInCrematoriumsFile.add(2);

            CrematoriumCsvParser crematoriumCsvParser = new CrematoriumCsvParser(desiredIndexesInCrematoriumsFile);

            ParsedAdressesFile parsedCrematoriumsAdressesFile = crematoriumCsvParser.parseGivenFile("crematoriums.csv");
            ArrayList<RawAdress> parsedCrematoriumsAdresses = parsedCrematoriumsAdressesFile.getAdresses();

            AdressConverter crematoriumsAdressConverter = new AdressConverter(crematoriumsOutputFile);
            ArrayList<ComplaintLocation> crematoriumsLatLongList = crematoriumsAdressConverter.convertRawAdressesListToComplaintLocationList(parsedCrematoriumsAdresses);

            System.out.println("Total amount of crematoriums within rotterdam: " + crematoriumsLatLongList.size());

            LatLongConverter latLongConverter = new LatLongConverter(minMaxLongCoordinates, minMaxLatCoordinates, appletWidthHeightMaximums);
            crematoriumPointsToDraw = latLongConverter.convertGivenCoordinateListToXYValues(crematoriumsLatLongList);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        parseComplaintCsvFile();
        parseCrematoriumsCsvFile();

        PApplet.main(new String[]{Main.class.getName()});
    }
}