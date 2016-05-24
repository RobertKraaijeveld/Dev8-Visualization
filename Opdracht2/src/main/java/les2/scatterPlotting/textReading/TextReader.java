package les2.scatterPlotting.textReading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import les2.scatterPlotting.Main.GenericPair;

/**
 *
 * @author gover_000
 */
public class TextReader 
{
    private File textFile;
    
    public TextReader(File textFile) 
    {
        this.textFile = textFile;
    }
    
    public TextFile createTextFileInstance() throws Exception
    {
        BufferedReader reader = new BufferedReader(new FileReader(this.textFile));
        ArrayList<GenericPair<Float, Float>> XYPairsList = new ArrayList<>();
        
        String line;
        int lineCounter = 0;

        while((line = reader.readLine()) != null)
        {
            if(lineCounter != 0)
            {
                GenericPair<String, String> XYstring = splitStringIntoXY(line);
                GenericPair<Float, Float> XYfloat = convertStringPairToDoublePair(XYstring);
                
                XYPairsList.add(XYfloat);
            }
            lineCounter++;
        }
        reader.close();
       
        TextFile returnTextFile = new TextFile(XYPairsList);
        return returnTextFile;
    }
    
    private GenericPair<String, String> splitStringIntoXY(String line)
    {
        line = this.removeSpaces(line);
        String X  = line.substring(1, 3);
        
        String Y = line.substring(3, line.length());
        Y = this.replaceCommasWithDots(Y);

        GenericPair<String, String> resultPair = new GenericPair<>(X,Y);
        return resultPair; 
    }
    
    private GenericPair<Float, Float> convertStringPairToDoublePair(GenericPair<String,String> stringPair) throws Exception
    {
        Float XFloat = Float.parseFloat(stringPair.getLeftValue());
        Float YFloat = Float.parseFloat(stringPair.getRightValue());
       
        GenericPair<Float, Float> returnDoublePair = new GenericPair<>(XFloat, YFloat);
        return returnDoublePair;
    }
    
    private String replaceCommasWithDots(String stringToConvert)
    {
        return stringToConvert.replace(',', '.');
    }
    
    private String removeSpaces(String stringToProcess)
    {
        return stringToProcess.replaceAll("\\s+", "");
    }
}
