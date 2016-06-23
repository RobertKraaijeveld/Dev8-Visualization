package FileParser;

import Datastructures.RawAdress;
import Utilities.Utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kraaijeveld on 21-6-2016.
 */
public class CsvParser
{
    //This allows the programmer to specify which CSV columns contain his desired values.
    //IE: 0,1,2 would result in the first 3 CSV values being taken.
    private ArrayList<Integer> indexesOfDesiredLineValues;
    private int locationOfComplaintTypeLineValue;

    public CsvParser(ArrayList<Integer> indexesOfDesiredLineValues, int locationOfComplaintTypeLineValue)
    {
        this.indexesOfDesiredLineValues = indexesOfDesiredLineValues;
        this.locationOfComplaintTypeLineValue = locationOfComplaintTypeLineValue;
    }

    public ParsedAdressesFile parseGivenFile(String filePath) throws Exception
    {
        ArrayList<RawAdress> rawAdressesInCsv = new ArrayList<>();
        String currentLine;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        //skip first line
        reader.readLine();

        while((currentLine = reader.readLine()) != null)
        {
            ArrayList<String> currentLineValues = Utilities.splitStringByDelimiter(currentLine, ",");

            if (isLineOfRightComplaintType(currentLineValues)) {
                RawAdress rawAdress = this.constructRawAdress(currentLineValues);
                rawAdressesInCsv.add(rawAdress);
            }
        }
        ParsedAdressesFile returnFile = new ParsedAdressesFile(rawAdressesInCsv);
        return returnFile;
    }

    private boolean isLineOfRightComplaintType(ArrayList<String> currentLineValues) {
        if (currentLineValues.get(locationOfComplaintTypeLineValue).equals("Stank"))
            return true;
        else
            return false;
    }

    private RawAdress constructRawAdress(ArrayList<String> unfilteredLine)
    {
        ArrayList<String> filteredLine = new ArrayList<>();

        for(int i = 0; i < unfilteredLine.size(); i++)
        {
            if(this.indexesOfDesiredLineValues.contains(i)) {
                String valueToInsert = unfilteredLine.get(i);
                filteredLine.add(valueToInsert);
            }
        }
        return new RawAdress(filteredLine.get(0), filteredLine.get(1), filteredLine.get(2));
    }
}
