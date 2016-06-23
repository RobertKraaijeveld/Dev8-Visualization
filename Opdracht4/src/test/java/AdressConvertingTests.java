import AdressConverting.AdressConverter;
import AdressConverting.ApiCaller;
import Datastructures.ComplaintLocation;
import Datastructures.GenericPair;
import Datastructures.RawAdress;

import FileParser.CsvParser;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.reflect.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kraaijeveld on 21-6-2016.
 */

public class AdressConvertingTests
{
    private File testOutputFile;
    private ArrayList<RawAdress> inputAdresses = new ArrayList<>();
    private ComplaintLocation expectedComplaintLocation = new ComplaintLocation(51.939003f, 4.422611f);

    public void setupTestData() throws IOException
    {
        FileWriter testOutputFileWriter = new FileWriter("adressConverterTestOutput.csv", true);
        testOutputFileWriter.flush();
        testOutputFileWriter.close();
        this.testOutputFile = new File("adressConverterTestOutput.csv");

        RawAdress inputLocation = new RawAdress("Saenredamplein", "3043RL", "ROTTERDAM", "Stank");
        RawAdress extraInputLocation = new RawAdress("Zuidhoek", "3117XM", "ROTTERDAM", "Stank");

        inputAdresses.add(inputLocation);
        inputAdresses.add(extraInputLocation);
    }

    @Test
    public void adressConvertionShouldReturnComplaintLocation()
    {
        try
        {
            setupTestData();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        AdressConverter converter = new AdressConverter(this.testOutputFile);
        ArrayList<ComplaintLocation> actualComplaintLocationList = converter.convertRawAdressesListToComplaintLocationList(this.inputAdresses);

        assertTrue(this.expectedComplaintLocation.equals(actualComplaintLocationList.get(0)));
    }

    @Test
    public void adressComplaintTypeShouldBeCorrectlyVerified()
    {
        ArrayList<Integer> desiredIndexesInFile = new ArrayList<Integer>();
        desiredIndexesInFile.add(3);
        desiredIndexesInFile.add(4);
        desiredIndexesInFile.add(5);

        int complaintTypeIndexInCsv = 6;
        int cityIndexInCsv = 5;
        GenericPair<Integer, Integer> complaintAndCityIndexInCsvPair = new GenericPair<>(complaintTypeIndexInCsv, cityIndexInCsv);

        ApiCaller apiCaller = new ApiCaller();

        RawAdress badAdress = new RawAdress("Langemeetstraat", "3223BL", "ROTTERDAM", "Lawaai");
        RawAdress goodAdress = new RawAdress("Langemeetstraat", "3223BL", "ROTTERDAM", "Stank");

        //All the stuff until the asserts is done to make the method we're testing temporarily publicy available.
        Class[] argumentType = new Class[1];
        argumentType[0] = RawAdress.class;

        try
        {
            Method publicVersionOfisRawAdressOfRightType = ApiCaller.class.getDeclaredMethod("isRawAdressOfRightType", argumentType);
            publicVersionOfisRawAdressOfRightType.setAccessible(true);

            boolean badCsvLinePartsResult = (boolean) publicVersionOfisRawAdressOfRightType.invoke(apiCaller, badAdress);
            boolean goodCsvLinePartsResult = (boolean) publicVersionOfisRawAdressOfRightType.invoke(apiCaller, goodAdress);

            assertFalse(badCsvLinePartsResult);
            assertTrue(goodCsvLinePartsResult);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
