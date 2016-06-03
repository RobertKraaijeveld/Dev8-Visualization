package FileParsing;

import Datastructures.GenericPair;
import Datastructures.Vector3D;
import Main.Main;

import java.io.BufferedReader;
import java.io.FileReader;
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

    //private Vector3D ZADKINE_STATUE_LOCATION = new Vector3D(92798.3067017f, 436965.057678f,4.212f);
    private Vector3D ZADKINE_STATUE_LOCATION = new Vector3D(92800f, 436955f,4f);
    private int desiredMapRadiusAroundStatue;

    
    public FileParser(Path coordinateFilePath, int desiredMapRadiusAroundStatue)
    {
        this.coordinateFilePath = coordinateFilePath;
        this.desiredMapRadiusAroundStatue = desiredMapRadiusAroundStatue;
    }

    public ParsedFile createParsedFileInstance() throws Exception
    {
        List<Vector3D> Vector3DList = new ArrayList<>();

        System.out.println("Started parsing.");

        String currentLine;
        BufferedReader reader = new BufferedReader(new FileReader(coordinateFilePath.toString()));

        //skip first line
        reader.readLine();

        while((currentLine = reader.readLine()) != null)
        {
            Vector3D currentVector = convertStringArrayToVector3D(currentLine);

            //The else might not be necessary with java's garbage collector
            if(this.doesVectorLieWithinRadius(currentVector))
            {
                Vector3DList.add(currentVector);
            }
            else
            {
                currentVector = null;
            }
        }
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

    private boolean doesVectorLieWithinRadius(Vector3D vector)
    {
        //TODO: Why minus?
        float thisVectorsXDistanceFromStatue = (float) Math.abs(this.ZADKINE_STATUE_LOCATION.getX() - vector.getX());
        float thisVectorsYDistanceFromStatue = (float) Math.abs(this.ZADKINE_STATUE_LOCATION.getY() - vector.getY());

        if(thisVectorsXDistanceFromStatue < this.desiredMapRadiusAroundStatue
        && thisVectorsYDistanceFromStatue < this.desiredMapRadiusAroundStatue)
        {
            return true;
        }
        else
            return false;
    }

}
