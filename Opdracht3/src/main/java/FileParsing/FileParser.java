package FileParsing;

import Main.Vector3D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
    
    //Todo: Make dis functional, save vectors into parsed file later (maybe)
    public void createParsedFileInstance() throws Exception
    {
        List<Vector3D<Float, Float, Float>> Vector3DList = new ArrayList<>();
        Object[] stringArray;
        
        stringArray = 
                Files
                .lines(coordinateFilePath)
                .skip(1)
                .map(line -> SplitStringAfterComma(line))
                .toArray();
                //.map(splitLines -> convertStringArrayToVector3D(splitLines))
                

        System.out.println("DONE");
        //return new ParsedFile(Vector3DList);
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
