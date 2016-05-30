package Main;

import FileParsing.FileParser;
import FileParsing.ParsedFile;
import processing.core.PApplet;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author gover_000
 */
public class Main extends PApplet
{
        public void settings()
        {

        }

        public void setup()
        {
        }

        public void draw()
        {
        }
        
        public static void main(String[] args)
        {
            try
            {
                Path csvWest = Paths.get("west.csv");
                FileParser parser = new FileParser(csvWest);
                ParsedFile parsedFile = parser.createParsedFileInstance();

                /*
                for(Vector3D v : parsedFile.getVectors())
                {
                    System.out.println("X: " + v.getX() + " Y: " + v.getY() + " Z " + v.getZ());
                }*/

                PApplet.main(new String[]{Main.class.getName()});
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        
}
