import AdressConverting.AdressConverter;
import Datastructures.ComplaintLocation;
import Datastructures.RawAdress;

import FileParser.CsvParser;
import org.junit.Test;

import java.util.ArrayList;
import java.lang.reflect.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kraaijeveld on 21-6-2016.
 */

public class AdressConvertingTests
{
    private ArrayList<RawAdress> inputAdresses = new ArrayList<>();
    private ComplaintLocation expectedComplaintLocation = new ComplaintLocation(51.8694218f, 4.5084365f);

    public void setupTestData() {
        RawAdress inputLocation = new RawAdress("Maeterlinckweg", "3084KD", "ROTTERDAM");
        inputAdresses.add(inputLocation);
    }

    @Test
    public void adressConvertionShouldReturnComplaintLocation()
    {
        setupTestData();

        AdressConverter converter = new AdressConverter();
        ArrayList<ComplaintLocation> actualComplaintLocationList = converter.convertRawAdressesListToComplaintLocationList(this.inputAdresses);

        assertTrue(actualComplaintLocationList.get(0).equals(expectedComplaintLocation));
    }

    @Test
    public void adressComplaintTypeShouldBeCorrectlyVerified() {
        ArrayList<Integer> desiredIndexesInFile = new ArrayList<Integer>();
        desiredIndexesInFile.add(3);
        desiredIndexesInFile.add(4);
        desiredIndexesInFile.add(5);

        CsvParser parser = new CsvParser(desiredIndexesInFile, 6);

        ArrayList<String> badCsvLineParts = new ArrayList<>();
        badCsvLineParts.add("telefonisch");
        badCsvLineParts.add("2/11/2011 1:19");
        badCsvLineParts.add("1");

        badCsvLineParts.add("Langemeetstraat");
        badCsvLineParts.add("3223BL");
        badCsvLineParts.add("HELLEVOETSLUIS");

        badCsvLineParts.add("Lawaai");
        badCsvLineParts.add("Apparatuur");
        badCsvLineParts.add("Motor / Pomp");
        badCsvLineParts.add("N");

        //Explicit clone/cast since simple assignment means the .set() line will be applied to both lists
        ArrayList<String> goodCsvLineParts = (ArrayList<String>) badCsvLineParts.clone();
        goodCsvLineParts.set(6, "Stank");


        //All the stuff until the asserts is done to make the method we're testing temporarily publicy available.
        Class[] argumentType = new Class[1];
        argumentType[0] = ArrayList.class;

        try {
            Method publicVersionOfisLineOfRightComplaintType = CsvParser.class.getDeclaredMethod("isLineOfRightComplaintType", argumentType);
            publicVersionOfisLineOfRightComplaintType.setAccessible(true);

            boolean badCsvLinePartsResult = (boolean) publicVersionOfisLineOfRightComplaintType.invoke(parser, badCsvLineParts);
            boolean goodCsvLinePartsResult = (boolean) publicVersionOfisLineOfRightComplaintType.invoke(parser, goodCsvLineParts);

            assertFalse(badCsvLinePartsResult);
            assertTrue(goodCsvLinePartsResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
