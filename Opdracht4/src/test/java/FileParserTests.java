/**
 * Created by Kraaijeveld on 21-6-2016.
 */

import static org.junit.Assert.assertTrue;

import Datastructures.GenericPair;
import Datastructures.RawAdress;
import FileParser.CsvParser;
import FileParser.ParsedAdressesFile;
import org.junit.Test;

import java.util.ArrayList;

public class FileParserTests
{
    private ArrayList<RawAdress> expectedAdressesList = new ArrayList<>();
    private ParsedAdressesFile expectedParsedAdressesFile;

    private void setupTestData()
    {
        RawAdress firstTestAdress = new RawAdress("Saenredamplein", "3043RL", "ROTTERDAM", "Stank");
        RawAdress secondTestAdress = new RawAdress("Zuidhoek", "3082PP", "ROTTERDAM", "Stank");

        this.expectedAdressesList.add(firstTestAdress);
        this.expectedAdressesList.add(secondTestAdress);

        ParsedAdressesFile parsedAdressesFileFixture = new ParsedAdressesFile(this.expectedAdressesList);
    }

    private void tearDownTestData()
    {
        this.expectedAdressesList.clear();
        this.expectedParsedAdressesFile = null;
    }

    @Test
    public void csvParserShouldReturnCorrectParsedAdressesFile()
    {
        setupTestData();

        int complaintTypeIndexInCsv = 6;
        int cityIndexInCsv = 5;
        GenericPair<Integer, Integer> complaintAndCityIndexInCsvPair = new GenericPair<>(complaintTypeIndexInCsv, cityIndexInCsv);

        ArrayList<Integer> desiredIndexesInFile = new ArrayList<>();
        desiredIndexesInFile.add(3);
        desiredIndexesInFile.add(4);
        desiredIndexesInFile.add(5);

        CsvParser parser = new CsvParser(desiredIndexesInFile);
        parser.setComplaintAndCityLineValueIndexes(complaintAndCityIndexInCsvPair);

        try
        {
            ParsedAdressesFile actualAdressFile = parser.parseGivenFile("test.csv");
            ArrayList<RawAdress> actualAdresses = actualAdressFile.getAdresses();

            assertTrue(this.expectedAdressesList.equals(actualAdresses));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        tearDownTestData();
    }

    @Test
    public void parsedAdressesFileGetterTest()
    {
        setupTestData();

        this.expectedParsedAdressesFile = new ParsedAdressesFile(this.expectedAdressesList);
        assertTrue(this.expectedParsedAdressesFile.getAdresses().equals(this.expectedAdressesList));

        tearDownTestData();
    }
}