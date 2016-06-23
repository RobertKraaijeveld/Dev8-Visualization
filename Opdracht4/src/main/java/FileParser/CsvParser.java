package FileParser;

import Datastructures.GenericPair;
import Datastructures.RawAdress;
import Utilities.Utilities;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Kraaijeveld on 21-6-2016.
 */
public class CsvParser
{
    /*
    This allows the programmer to specify which CSV columns contain his desired values.
    IE: 0,1,2 would result in the first 3 CSV values being taken.
    */

    private ArrayList<Integer> indexesOfDesiredLineValues;
    GenericPair<Integer, Integer> complaintAndCityLineValueIndexes;

    public CsvParser(ArrayList<Integer> indexesOfDesiredLineValues, GenericPair<Integer, Integer> complaintAndCityLineValueIndexes)
    {
        this.indexesOfDesiredLineValues = indexesOfDesiredLineValues;
        this.complaintAndCityLineValueIndexes = complaintAndCityLineValueIndexes;
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
            RawAdress rawAdress = this.constructRawAdress(currentLineValues);
            rawAdressesInCsv.add(rawAdress);
        }
        ParsedAdressesFile returnFile = new ParsedAdressesFile(rawAdressesInCsv);
        return returnFile;
    }

    private RawAdress constructRawAdress(ArrayList<String> unfilteredLine)
    {
        ArrayList<String> filteredLine = new ArrayList<>();

        for(int i = 0; i < unfilteredLine.size(); i++)
        {
            if (this.indexesOfDesiredLineValues.contains(i) || this.complaintAndCityLineValueIndexes.getLeftValue() == i)
            {
                String valueToInsert = unfilteredLine.get(i);
                filteredLine.add(valueToInsert);
            }
        }
        return new RawAdress(filteredLine.get(0), filteredLine.get(1), filteredLine.get(2), filteredLine.get(3));
    }
}
