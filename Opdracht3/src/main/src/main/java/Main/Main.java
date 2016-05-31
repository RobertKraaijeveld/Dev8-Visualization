package Main;

import FileParsing.FileParser;
import FileParsing.ParsedFile;
import processing.core.PApplet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author gover_000
 */
public class Main extends PApplet
{
    static List<Vector3D> vectorList = new ArrayList<>();

    @Override
    public void settings() {

    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {

    }


    public static void main(String[] args) {
        Main m = new Main();

        try
        {
            vectorList = m.parseCsvFile("oost.csv");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.print("finished");
    }

    public static Vector3D parseRow(String row) {
        Vector3D vector = new Vector3D();
        vector.setxValue(Float.parseFloat(row.substring(0, row.indexOf(","))));
        vector.setyValue(Float.parseFloat(row.substring(row.indexOf(",") + 1, row.lastIndexOf(","))));
        vector.setzValue(Float.parseFloat(row.substring(row.lastIndexOf(",") + 1)));

        return vector;
    }

    private List<Vector3D> parseCsvFile(String path) throws IOException {
        return Files
                .lines(Paths.get(path))
                .skip(1)
                .map(e -> parseRow(e))
                .collect(Collectors.toList());
    }
        
}
