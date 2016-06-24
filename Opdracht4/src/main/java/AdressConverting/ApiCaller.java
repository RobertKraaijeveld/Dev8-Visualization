package AdressConverting;

import Datastructures.ComplaintLocation;
import Datastructures.RawAdress;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Kraaijeveld on 22-6-2016.
 */
public class ApiCaller
{
    public void populateCsvFileWithApiResults(ArrayList<RawAdress> rawAdressesList, File csvFileToPopulate) throws Exception
    {
        for (RawAdress rawAdress : rawAdressesList)
        {
            if (rawAdress.isRawAdressOfRightType() == true)
            {
                //TODO MAKE ALGO MORE EFFICIENT
                ComplaintLocation thisAdressesComplaintLocation = this.getLatLongFromGoogleAPI(rawAdress);
                this.addLineToCsv(thisAdressesComplaintLocation, csvFileToPopulate);
            }
        }
    }

    public void createResultCSVFile(File csvFileToCreate) throws IOException
    {

        csvFileToCreate.createNewFile();
    }

    private ComplaintLocation getLatLongFromGoogleAPI(RawAdress rawAdress)
    {
        //This is dirty
        ComplaintLocation returnLocation = null;

        try
        {
            String apiResponse = this.getUrlReturnString(this.constructApiURL(rawAdress));

            JSONObject jsonObject = this.getJSONObject(apiResponse);

            JSONArray resultArray = jsonObject.getJSONArray("results");

            JSONObject resultObject = (JSONObject) resultArray.get(0);
            JSONObject latLongObject = resultObject.getJSONObject("geometry").getJSONObject("location");

            System.out.println(latLongObject.toString());

            Float latitude = (float) latLongObject.getDouble("lat");
            Float longitude = (float) latLongObject.getDouble("lng");

            returnLocation = new ComplaintLocation(latitude, longitude);
            return returnLocation;
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return returnLocation;
    }

    /**
     * URL CONTENTS READING
     **/

    private URL constructApiURL(RawAdress rawAdress) throws Exception
    {
        String street = rawAdress.getStreet();
        String city = rawAdress.getCity();
        String zipCode = rawAdress.getZipCode();

        String googleApisPartOfURL = "https://maps.googleapis.com";
        String adressPartOfURL = "/maps/api/geocode/json?address=";

        adressPartOfURL += street;
        adressPartOfURL += ",";
        adressPartOfURL += zipCode;
        adressPartOfURL += ",";
        adressPartOfURL += city;

        String apiKeyPartOfURL = "&key=AIzaSyCplgG4qwTJOGMQZUfMgm5wzSf5-Y8ytLo";

        String finalUrlString = googleApisPartOfURL += adressPartOfURL += apiKeyPartOfURL;

        //TODO EXTRACT TO METHOD
        finalUrlString = finalUrlString.replaceAll(" ", "");
        finalUrlString = finalUrlString.replace("ü", "ue");

        return new URL(finalUrlString);
    }

    private String getUrlReturnString(URL url) throws Exception
    {
        BufferedReader reader = null;

        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuffer buffer = new StringBuffer();

        int read;
        char[] chars = new char[1024];
        while ((read = reader.read(chars)) != -1)
            buffer.append(chars, 0, read);

        if (reader != null)
            reader.close();

        return buffer.toString();
    }

    public JSONObject getJSONObject(String jsonText)
    {
        //Potentially returning null is quite dirty.
        JSONObject jsonObject = null;
        try
        {
            jsonObject = new JSONObject(jsonText);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /*
    *
     Appending to CSV helpers
    *
     */

    private String sanitizeUrl(String urlString)
    {
        urlString = urlString.replaceAll(" ", "");
        urlString = urlString.replace("ü", "ue");
        return urlString;
    }

    private void addLineToCsv(ComplaintLocation complaintLocationToBeAdded, File csvFileToPopulate) throws IOException
    {
        FileWriter csvFile = new FileWriter(csvFileToPopulate, true);

        csvFile.append(String.valueOf(complaintLocationToBeAdded.getLatitude()));
        csvFile.append(",");
        csvFile.append(String.valueOf(complaintLocationToBeAdded.getLongitude()));
        csvFile.append("\n");

        csvFile.flush();
        csvFile.close();
    }
}
