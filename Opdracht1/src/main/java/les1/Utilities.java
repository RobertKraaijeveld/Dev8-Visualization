package les1;

import org.json.*;
import java.util.ArrayList;
import java.util.Set;
import static processing.core.PApplet.map;


/**
 * Created by Kraaijeveld on 25-4-2016.
 */

public class Utilities
{
    public static float convertDepthToColor(float depth)
    {
        depth = depth * 10;
        return depth;
    }

    public static float convertRichterScaleToElipseSize(float richterScale)
    {
        //8 is just an arbitrary multiplicant, mind you
        int baseElipseSize = 10;
        return baseElipseSize + (richterScale * 8);
    }
}

