package les1;

import org.json.*;
import java.io.*;
import java.net.URL;


/**
 * Created by Kraaijeveld on 25-4-2016.
 */

public class JSONGetter
{
    private JSONObject jsonObject = new JSONObject();

    private String readUrl(String urlString) throws Exception
    {
        BufferedReader reader = null;
        URL url = new URL(urlString);
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

    public JSONObject getJSONObject(String url)
    {
        try
        {
            String jsonText = readUrl(url);
            JSONObject jsonObject = new JSONObject(jsonText);
            this.jsonObject = jsonObject;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
