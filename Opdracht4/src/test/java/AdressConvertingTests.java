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
}
