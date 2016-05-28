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

    /*
    * Construction methods for the 2 different types of TextFile classes.
    */

    public TextFile createTextFileInstance() throws Exception
    {
        BufferedReader reader = new BufferedReader(new FileReader(this.textFile));
        ArrayList<GenericPair<Float, Float>> XYPairsList = new ArrayList<>();
        
        String line;
        int lineCounter = 0;
        int lineIndexToStartSplitting = 1;
        int lineIndexToStopSplitting = 3;

        while((line = reader.readLine()) != null)
        {
            if(lineCounter != 0)
            {
                GenericPair<String, String> XYstring = splitStringIntoXY(line, lineIndexToStartSplitting, lineIndexToStopSplitting);

                Float leftFloatValue = this.convertStringToFloat(XYstring.getLeftValue());
                Float rightFloatValue = this.convertStringToFloat(XYstring.getRightValue());
                GenericPair<Float, Float> XYfloat = new GenericPair<>(leftFloatValue,rightFloatValue);
                
                XYPairsList.add(XYfloat);
            }
            lineCounter++;
        }
        reader.close();
       
        TextFile returnTextFile = new TextFile(XYPairsList);
        return returnTextFile;
    }

    public StudentDataFile createStudentDataFileInstance() throws Exception
    {
        BufferedReader reader = new BufferedReader(new FileReader(this.textFile));
        ArrayList<String> headerList = new ArrayList<>();
        ArrayList<Float> valueList = new ArrayList<>();

        String line;
        int lineCounter = 0;

        //While there are still lines left to read
        while((line = reader.readLine()) != null)
        {
            //We use the first line to get the header values, and dont use it anymore after that.
            if(lineCounter == 0)
            {
                //We grab the header values one by one: Dev, Ana etc.
                //We need these for the creation of the StudentDataFile object later.
                String header1 = getSingleValueFromLine(line, 4, 7);
                String header2 = getSingleValueFromLine(line, 7, 10);
                String header3 = getSingleValueFromLine(line, 10, 13);
                String header4 = getSingleValueFromLine(line, 13, 16);
                headerList.add(header1);
                headerList.add(header2);
                headerList.add(header3);
                headerList.add(header4);
            }
            //second line is blank
            else if(lineCounter >= 2)
            {
                //we do 4 getSingleValueFromLine-grabs:
                Float value1 = Float.parseFloat(getSingleValueFromLine(line, 7, 10));
                Float value2 = Float.parseFloat(getSingleValueFromLine(line, 10, 13));
                Float value3 = Float.parseFloat(getSingleValueFromLine(line, 13, 16));
                Float value4 = Float.parseFloat(getSingleValueFromLine(line, 16, 19));
                valueList.add(value1);
                valueList.add(value2);
                valueList.add(value3);
                valueList.add(value4);
            }
            lineCounter++;
        }
        reader.close();

        ArrayList<GenericPair<String, Float>> StudentDataFileList = this.constructGenericPairsOutOfLists(headerList, valueList);
        return new StudentDataFile(StudentDataFileList);
    }


    /*
    * Helper methods. The splitStringIntoXY() and getSingleValueFromLine() methods are a little duplicated.
    */

    private ArrayList<GenericPair<String, Float>> constructGenericPairsOutOfLists(ArrayList<String> headers, ArrayList<Float> values)
    {
        ArrayList<GenericPair<String, Float>> returnList = new ArrayList<>();
        int headerCounter = 0;
        
        
        for(int i = 0; i < values.size(); i++)
        {
            //Are these ifs necessary?
            if(i % 2 == 0)
            {
                String stringId = headers.get(headerCounter);
                Float value = values.get(i);
                GenericPair<String, Float> pairToBeAdded = new GenericPair<>(stringId, value);
            }
            else if(i % 3 == 0)
            {
                String stringId = headers.get(headerCounter);
                Float value = values.get(i);
                GenericPair<String, Float> pairToBeAdded = new GenericPair<>(stringId, value);
            }
            else if(i % 4 == 0)
            {
                String stringId = headers.get(headerCounter);
                Float value = values.get(i);
                GenericPair<String, Float> pairToBeAdded = new GenericPair<>(stringId, value);
            }
            else
            {
                String stringId = headers.get(headerCounter);
                Float value = values.get(i);
                GenericPair<String, Float> pairToBeAdded = new GenericPair<>(stringId, value);
            }
            
            //we reset the headerCounter after each line (which consists of 4 header elements).
            if(headerCounter == 3)
                headerCounter = 0;
            else
                headerCounter++;
        }
        return returnList;
    }

    private String getSingleValueFromLine(String line, int indexToStartSplitting, int indexToStopSplitting)
    {
        line = this.removeSpaces(line);
        String stringToGrab  = line.substring(indexToStartSplitting, indexToStopSplitting);
        stringToGrab = this.replaceCommasWithDots(stringToGrab);
        
        System.out.println(stringToGrab);
        return stringToGrab;
    }
    
    private GenericPair<String, String> splitStringIntoXY(String line, int indexToStartSplitting, int indexToStopSplitting)
    {
        line = this.removeSpaces(line);
        String X  = line.substring(indexToStartSplitting, indexToStopSplitting);
        
        String Y = line.substring(indexToStopSplitting, line.length());
        Y = this.replaceCommasWithDots(Y);

        GenericPair<String, String> resultPair = new GenericPair<>(X,Y);
        return resultPair; 
    }
    
    private Float convertStringToFloat(String input) throws Exception
    {
        return Float.parseFloat(input);
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


