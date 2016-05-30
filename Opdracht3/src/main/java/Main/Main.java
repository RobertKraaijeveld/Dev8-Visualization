package Main;

import FileParsing.FileParser;
import FileParsing.ParsedFile;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import processing.core.PApplet;

/**
 *
 * @author gover_000
 */
public class Main extends PApplet
{


        public void settings() {

        }

        public void setup() {
        }

        public void draw() {
             
        }
        
        public static void main(String[] args)
        {
            try 
            {
                DirectorySpecifier specifier = new DirectorySpecifier();
                String fileLocation = specifier.getSpecifiedPathFromCommandLine();
                File textFileForTest = new File(fileLocation);
                
                //C:\Users\gover_000\Documents\GitHub\Dev8-Visualization\Opdracht3\CSVs\rotterdamopendata_hoogtebestandtotaal_oost.csv
                Path csvWest = Paths.get(fileLocation);
                FileParser parser = new FileParser(csvWest);
                parser.createParsedFileInstance();
                
                
                    
                PApplet.main(new String[]{Main.class.getName()});
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }

        
}
