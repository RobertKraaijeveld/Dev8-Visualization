package les1.earthquakePlotting;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import static processing.core.PApplet.map;

/**
 * Created by Kraaijeveld on 9-5-2016.
 */
public class CoordinateToAxisConverter
{
    private static JSONObject jsonObject = new JSONGetter().getJSONObject("http://apis.is/earthquake/is");
    private static ArrayList<Earthquake> earthquakeArrayList = setEarthquakeArrayList(jsonObject);

    public static ArrayList<Earthquake> getEarthquakeArrayList() {
        return earthquakeArrayList;
    }

    public static ArrayList<Earthquake> calculateXYValuesForEarthquakesInList(ArrayList<Earthquake> earthquakesList)
    {
        for(Earthquake e : earthquakesList)
        {
            float xValueForLatitude = map(e.getlatLongPair().getLeftValue(), 66.8f, 63.1f, 0.0f, 480.0f);
            float yValueForLongitude = map(e.getlatLongPair().getRightValue(), -25.0f, -13.0f, 0.0f, 640.0f);
            e.setConvertedCoordinatePair(new GenericPair<>(yValueForLongitude , xValueForLatitude));
        }
        return earthquakesList;
    }

    private static ArrayList<Earthquake> setEarthquakeArrayList(JSONObject jsonObjectToParse)
    {
        JSONArray resultsArray = jsonObjectToParse.getJSONArray("results");
        ArrayList<Earthquake> returnList = new ArrayList<>();

        for (Object earthquakeObject : resultsArray)
        {
            JSONObject currentEarthquakeJSONObject = (JSONObject) earthquakeObject;
            float currentEarthquakeRichterScale = (float) currentEarthquakeJSONObject.getDouble("size");
            float currentEarthquakeLat = (float) currentEarthquakeJSONObject.getDouble("latitude");
            float currentEarthquakeLong = (float) currentEarthquakeJSONObject.getDouble("longitude");
            float currentEarthquakeDepth = (float) currentEarthquakeJSONObject.getDouble("depth");

            Earthquake e = new Earthquake(currentEarthquakeLat, currentEarthquakeLong, currentEarthquakeRichterScale, currentEarthquakeDepth);

            returnList.add(e);
        }
        return returnList;
    }
}
