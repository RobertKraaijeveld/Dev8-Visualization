package FileParsing;

import Main.Vector3D;

import java.util.List;

/**
 *
 * @author gover_000
 */
public class ParsedFile {
    private List<Vector3D> Vectors;
    
    public ParsedFile(List<Vector3D> Vectors)
    {
        this.Vectors = Vectors;
    }
    
    public List<Vector3D> getVectors()
    {
        return this.Vectors;
    }
}
