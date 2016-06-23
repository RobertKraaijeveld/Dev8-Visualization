package AdressConverting;

import Datastructures.ComplaintLocation;
import Datastructures.RawAdress;
import Utilities.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Kraaijeveld on 21-6-2016.
 */


//API key
public class AdressConverter
{
    private File outputFile;

    public AdressConverter(File outputFile)
    {
        this.outputFile = outputFile;
    }

    public ArrayList<ComplaintLocation> convertRawAdressesListToComplaintLocationList(ArrayList<RawAdress> rawAdressesList)
    {
        ArrayList<ComplaintLocation> convertedAdresses = new ArrayList<>();

        try
        {
            if (isCSVFileAlreadyPopulated() == false)
            {
                ApiCaller apiCaller = new ApiCaller();
                apiCaller.createResultCSVFile(this.outputFile);
                apiCaller.populateCsvFileWithApiResults(rawAdressesList, this.outputFile);
                convertedAdresses = getListOfCsvLines();
            } else
                convertedAdresses = getListOfCsvLines();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return convertedAdresses;
    }

    private boolean isCSVFileAlreadyPopulated()
    {
        if (this.outputFile.length() > 0)
            return true;
        else
            return false;
    }

    private ArrayList<ComplaintLocation> getListOfCsvLines() throws Exception
    {
        ArrayList<ComplaintLocation> rawComplaintLocationsInCsv = new ArrayList<>();
        String currentLine;
        String delimiter = ",";

        BufferedReader reader = new BufferedReader(new FileReader(this.outputFile));

        while ((currentLine = reader.readLine()) != null)
        {
            ArrayList<String> currentLineValues = Utilities.splitStringByDelimiter(currentLine, delimiter);
            ComplaintLocation currentComplaintLocation = constructComplaintLocation(currentLineValues);
            rawComplaintLocationsInCsv.add(currentComplaintLocation);
        }
        return rawComplaintLocationsInCsv;
    }

    private ComplaintLocation constructComplaintLocation(ArrayList<String> currentLineValues) throws Exception
    {
        float latitude = Float.parseFloat(currentLineValues.get(0));
        float longitude = Float.parseFloat(currentLineValues.get(1));

        return new ComplaintLocation(latitude, longitude);
    }
}
