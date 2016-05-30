package FileParsing;

import Main.Vector3D;

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
        List<Vector3D<Float, Float, Float>> Vector3DList;

        //size: 37399955
        Vector3DList =
                Files
                .lines(coordinateFilePath)
                .limit(37399955)
                .skip(1)
                .map(line -> SplitStringAfterComma(line))
                .map(splitLines -> convertStringArrayToVector3D(splitLines))
                .collect(Collectors.toList());

        System.out.println("DONE");
        return new ParsedFile(Vector3DList);
    }

    private String[] SplitStringAfterComma(String input) 
    {
        return input.split(",");
    }
    
    private Vector3D<Float, Float, Float> convertStringArrayToVector3D(String[] input)
    {  
       Float X = Float.parseFloat(input[0]);
       Float Y = Float.parseFloat(input[1]);
       Float Z = Float.parseFloat(input[2]);
       return new Vector3D<>(X,Y,Z);
    }     
}
