package Utilities;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kraaijeveld on 22-6-2016.
 */
public class Utilities {
    public static ArrayList<String> splitStringByDelimiter(String currentLine, String delimiter) {
        String[] splitLineValues = currentLine.split(delimiter);
        return new ArrayList<>(Arrays.asList(splitLineValues));
    }
}
