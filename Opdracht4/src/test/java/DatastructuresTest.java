import Datastructures.ComplaintLocation;
import Datastructures.GenericPair;
import Datastructures.RawAdress;
import FileParser.CsvParser;
import FileParser.ParsedAdressesFile;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kraaijeveld on 21-6-2016.
 */
public class DatastructuresTest
{
    //Specifies how much floats are allowed to be off during asserts.
    private final double DELTA = 1e-15;

    @Test
    public void genericPairGettersTest()
    {
        GenericPair<Float, Float> genericPair = new GenericPair<>(0.0f, 1.0f);

        assertEquals(0.0f, (float) genericPair.getLeftValue(), DELTA);
        assertEquals(1.0f, (float) genericPair.getRightValue(), DELTA);
    }


    @Test
    public void complaintLocationGettersTest()
    {
        ComplaintLocation complaintLocation = new ComplaintLocation(0.0f, 1.0f);

        assertEquals(0.0f, complaintLocation.getLatitude(), DELTA);
        assertEquals(1.0f, complaintLocation.getLongitude(), DELTA);
    }


    @Test
    public void rawAdressEqualsTest()
    {
        RawAdress firstRawAdress = new RawAdress("test", "test", "test", "Stank");
        RawAdress secondRawAdress = new RawAdress("test", "test", "test", "Stank");
        RawAdress badRawAdress = new RawAdress("nottest", "nottest", "nottest", "Stank");

        assertTrue(firstRawAdress.equals(secondRawAdress));
        assertFalse(firstRawAdress.equals(badRawAdress));
        assertFalse(secondRawAdress.equals(badRawAdress));
    }
}