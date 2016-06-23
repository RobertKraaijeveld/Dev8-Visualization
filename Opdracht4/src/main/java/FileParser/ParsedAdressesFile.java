package FileParser;

import Datastructures.RawAdress;

import java.util.*;

/**
 *
 * @author Kraaijeveld
 */


public class ParsedAdressesFile
{
    ArrayList<RawAdress> adresses;

    public ParsedAdressesFile(ArrayList<RawAdress> adresses)
    {
        this.adresses = adresses;
    }

    public ArrayList<RawAdress> getAdresses()
    {
        return this.adresses;
    }
}
