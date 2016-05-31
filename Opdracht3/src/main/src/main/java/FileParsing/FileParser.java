package FileParsing;

import Datastructures.Vector3D;

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

    public ParsedFile createParsedFileInstance() throws Exception
    {
        List<Vector3D> Vector3DList = new ArrayList<>();

        System.out.println("Started parsing.");

        Vector3DList =
                Files
                   .lines(coordinateFilePath)
                   .skip(1)
                   .map(e -> convertStringArrayToVector3D(e))
                   .collect(Collectors.toList());

        System.out.println("Done parsing.");
        return new ParsedFile(Vector3DList);
    }

    private Vector3D convertStringArrayToVector3D(String input)
    {
        Vector3D returnVector = new Vector3D();
        returnVector.setxValue(Float.parseFloat(input.substring(0, input.indexOf(","))));
        returnVector.setyValue(Float.parseFloat(input.substring(input.indexOf(",") + 1, input.lastIndexOf(","))));
        returnVector.setzValue(Float.parseFloat(input.substring(input.lastIndexOf(",") + 1)));

        return returnVector;
    }     
}
