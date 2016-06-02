package FileParsing;

import Datastructures.GenericPair;
import Datastructures.Vector3D;
import Main.Main;

import java.io.IOException;
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

        if(this.searchForZadkineVector() == true)
            System.out.println("Found X of zadkine");
        else
            System.out.println("not Found X of zadkine");

        System.out.println("Done parsing.");
        return new ParsedFile(Vector3DList);
    }

    private boolean searchForZadkineVector() throws IOException
    {
        CharSequence cs1 = "int";
        String test = "3930161";
        boolean returnBoolean  = test.contains(cs1);
                                 //Files
                                 //   .lines(coordinateFilePath)
                                 //   .anyMatch(line -> line.contains("3930161"));
        return returnBoolean;
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
