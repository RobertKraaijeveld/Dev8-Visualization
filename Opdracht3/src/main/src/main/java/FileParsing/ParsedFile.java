package FileParsing;

import Main.Vector3D;

import java.util.List;

/**
 *
 * @author gover_000
 */
public class ParsedFile {
    private List<Vector3D<Float, Float, Float>> Vectors;
    
    public ParsedFile(List<Vector3D<Float, Float, Float>> Vectors) 
    {
        this.Vectors = Vectors;
    }
    
    public List<Vector3D<Float, Float, Float>> getVectors()
    {
        return this.Vectors;
    }
}
