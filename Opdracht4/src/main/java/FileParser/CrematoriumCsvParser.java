package FileParser;

import Datastructures.RawAdress;

import java.util.ArrayList;

/**
 * Created by Kraaijeveld on 23-6-2016.
 */

public class CrematoriumCsvParser extends CsvParser
{
    private ArrayList<Integer> indexesOfDesiredLineValues;

    public CrematoriumCsvParser(ArrayList<Integer> indexesOfDesiredLineValues)
    {
        //using super() we call the constructor for our parent, CSVparser. This is necessary to satisfy our contract.f
        super(indexesOfDesiredLineValues);
        this.indexesOfDesiredLineValues = indexesOfDesiredLineValues;
    }

    @Override
    protected RawAdress constructRawAdress(ArrayList<String> unfilteredLine)
    {
        ArrayList<String> filteredLine = new ArrayList<>();

        for (int i = 0; i < unfilteredLine.size(); i++)
        {
            if (this.indexesOfDesiredLineValues.contains(i))
            {
                String valueToInsert = unfilteredLine.get(i);
                filteredLine.add(valueToInsert);
            }
        }
        //We set the complaint type manually in order to satisfy the ApiCaller, which only calls the Api if the complaint is of type "Stank".
        return new RawAdress(filteredLine.get(0), filteredLine.get(1), filteredLine.get(2), "Stank");
    }
}
