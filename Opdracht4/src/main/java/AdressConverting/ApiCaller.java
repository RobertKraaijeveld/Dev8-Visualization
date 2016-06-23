package AdressConverting;

import Datastructures.ComplaintLocation;
import Datastructures.RawAdress;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Kraaijeveld on 22-6-2016.
 */
public class ApiCaller {
    public void populateCsvFileWithApiResults(ArrayList<RawAdress> rawAdressesList) throws Exception {
        for (RawAdress rawAdress : rawAdressesList) {
            ComplaintLocation thisAdressesComplaintLocation = this.getLatLongFromGoogleAPI(rawAdress);
            this.addLineToCsv(thisAdressesComplaintLocation);
        }
    }

    public void createResultCSVFile() throws IOException {
        File file = new File("latlongs.csv");
        file.createNewFile();
    }

    private ComplaintLocation getLatLongFromGoogleAPI(RawAdress rawAdress) {
        //This is dirty
        ComplaintLocation returnLocation = null;

        try {
            String apiResponse = getUrlReturnString(this.constructApiURL(rawAdress));
            JSONObject jsonObject = this.getJSONObject(apiResponse);

            Float latitude = (float) jsonObject.getDouble("lat");
            Float longitude = (float) jsonObject.getDouble("lng");

            System.out.println("Lat " + latitude);
            System.out.println("Lng " + longitude);

            returnLocation = new ComplaintLocation(latitude, longitude);
            return returnLocation;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnLocation;
    }

    /**
     * URL CONTENTS READING
     **/

    private String constructApiURL(RawAdress rawAdress) throws Exception {
        String street = rawAdress.getStreet();
        String city = rawAdress.getCity();
        String zipCode = rawAdress.getZipCode();

        String googleApisPartOfURL = "https://maps.googleapis.com/maps/api/geocode/json?";
        String adressPartOfURL = "address=";

        adressPartOfURL += street;
        adressPartOfURL += ",";
        adressPartOfURL += zipCode;
        adressPartOfURL += ",";
        adressPartOfURL += city;

        String apiKeyPartOfURL = "&key=AIzaSyCplgG4qwTJOGMQZUfMgm5wzSf5-Y8ytLo";

        String finalHttpRequestString = googleApisPartOfURL + adressPartOfURL + apiKeyPartOfURL;
        return finalHttpRequestString;
    }

    private String getUrlReturnString(String urlString) throws Exception {
        BufferedReader reader = null;

        URI uri = new URI(urlString);
        reader = new BufferedReader(new InputStreamReader(uri.toURL().openStream()));
        StringBuffer buffer = new StringBuffer();

        int read;
        char[] chars = new char[1024];
        while ((read = reader.read(chars)) != -1)
            buffer.append(chars, 0, read);

        if (reader != null)
            reader.close();

        return buffer.toString();
    }

    public JSONObject getJSONObject(String url) {
        //Dirtyyyyyy
        JSONObject jsonObject = null;
        try {
            String jsonText = getUrlReturnString(url);
            jsonObject = new JSONObject(jsonText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /*
    *
     Appending to CSV helpers
    *
     */

    private void addLineToCsv(ComplaintLocation complaintLocationToBeAdded) throws Exception {
        FileWriter csvFile = new FileWriter("latlongs.csv", true);

        csvFile.append(String.valueOf(complaintLocationToBeAdded.getLatitude()));
        csvFile.append(",");
        csvFile.append(String.valueOf(complaintLocationToBeAdded.getLongitude()));
        csvFile.append("\n");

        csvFile.flush();
        csvFile.close();
    }
}
