package FileParsing;

import Datastructures.GenericPair;
import Datastructures.Vector3D;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author gover_000
 */
public class ParsedFile
{
    private List<Vector3D> Vectors;
    
    public ParsedFile(List<Vector3D> Vectors)
    {
        this.Vectors = Vectors;
    }
    
    public List<Vector3D> getSourceVectors()
    {
        return this.Vectors;
    }

    public GenericPair<Vector3D, Vector3D> getVectorsWithSmallestXYs()
    {
        GenericPair<Vector3D, Vector3D> returnVectorPair;

        //Note to self; Float has its own comparator, the static Float.compare(f1,f2).
        Vector3D smallestXvector = Vectors
                                        .stream()
                                        .min((v1, v2) -> Float.compare(v1.getX(), v2.getX()))
                                        .get();

        Vector3D smallestYvector = Vectors
                                        .stream()
                                        .min((v1, v2) -> Float.compare(v1.getY(), v2.getY()))
                                        .get();

        return new GenericPair<>(smallestXvector, smallestYvector);
    }

    public GenericPair<Vector3D, Vector3D> getVectorsWithLargestXYs()
    {
        GenericPair<Vector3D, Vector3D> returnVectorPair;

        Vector3D largestXvector = Vectors
                                        .stream()
                                        .max((v1, v2) -> Float.compare(v1.getX(), v2.getX()))
                                        .get();

        Vector3D largestYvector = Vectors
                                        .stream()
                                        .max((v1, v2) -> Float.compare(v1.getY(), v2.getY()))
                                        .get();

        return new GenericPair<>(largestXvector, largestYvector);
    }

    public GenericPair<Vector3D, Vector3D> getSmallestAndLargestZvectors()
    {
        Vector3D smallestVector = Vectors
                .stream()
                .min((v1, v2) -> Float.compare(v1.getZ(), v2.getZ()))
                .get();

        Vector3D largestVector = Vectors
                            .stream()
                           .max((v1, v2) -> Float.compare(v1.getZ(), v2.getZ()))
                           .get();

        return new GenericPair<>(smallestVector, largestVector);
    }
}
