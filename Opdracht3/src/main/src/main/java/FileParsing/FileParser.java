package FileParsing;

import Main.Vector3D;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author gover_000
 */
public class FileParser 
{
    private Path coordinateFilePath;
    
    public FileParser(Path coordinateFilePath) 
    {
        this.coordinateFilePath = coordinateFilePath;
    }

    public ParsedFile createParsedFileInstance() throws Exception
    {
        List<Vector3D> Vector3DList = new ArrayList<>();


        //size: 37399955
        Vector3DList =
                Files
                   .lines(coordinateFilePath)
                   .skip(1)
                   .map(e -> convertStringArrayToVector3D(e))
                   .collect(Collectors.toList());

        System.out.println("Done...");


        return new ParsedFile(Vector3DList);
    }

    /*
    private String[] SplitStringAfterComma(String input) 
    {
        return input.split(",");
    }
    */

    private Vector3D convertStringArrayToVector3D(String input)
    {
        Vector3D returnVector = new Vector3D();
        returnVector.setxValue(Float.parseFloat(input.substring(0, input.indexOf(","))));
        returnVector.setyValue(Float.parseFloat(input.substring(input.indexOf(",") + 1, input.lastIndexOf(","))));
        returnVector.setzValue(Float.parseFloat(input.substring(input.lastIndexOf(",") + 1)));

        return returnVector;
    }     
}
