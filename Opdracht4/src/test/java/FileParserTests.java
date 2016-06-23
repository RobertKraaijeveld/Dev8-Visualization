/**
 * Created by Kraaijeveld on 21-6-2016.
 */

import static org.junit.Assert.assertTrue;

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
        RawAdress testAdress = new RawAdress("Van Ostadelaan", "3117XM", "SCHIEDAM");
        this.expectedAdressesList.add(testAdress);

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
        ArrayList<Integer> desiredIndexesInFile = new ArrayList<Integer>();
        desiredIndexesInFile.add(3);
        desiredIndexesInFile.add(4);
        desiredIndexesInFile.add(5);

        CsvParser parser = new CsvParser(desiredIndexesInFile, complaintTypeIndexInCsv);

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

        ParsedAdressesFile parsedAdressesFileFixture = new ParsedAdressesFile(this.expectedAdressesList);
        assertTrue(parsedAdressesFileFixture.getAdresses().equals(this.expectedAdressesList));

        tearDownTestData();
    }
}