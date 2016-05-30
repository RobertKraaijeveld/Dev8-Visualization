package FileParsing;

import Main.Vector3D;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        List<Vector3D<Float, Float, Float>> Vector3DList = new ArrayList<>();

        System.out.println("Loading...");

        //size: 37399955
        Vector3DList =
                Files
                .lines(coordinateFilePath)
                .skip(1)
                .map(splitLines -> convertStringArrayToVector3D(splitLines))
                .collect(Collectors.toList());

        System.out.println("Done...");


        return null;
    }

    /*
    private String[] SplitStringAfterComma(String input) 
    {
        return input.split(",");
    }
    */

    private Vector3D<Float, Float, Float> convertStringArrayToVector3D(String input)
    {
       String[] splitStrings = input.split(",");

       Float X = Float.parseFloat(splitStrings[0]);
       Float Y = Float.parseFloat(splitStrings[1]);
       Float Z = Float.parseFloat(splitStrings[2]);

       return new Vector3D<>(X,Y,Z);
    }     
}
