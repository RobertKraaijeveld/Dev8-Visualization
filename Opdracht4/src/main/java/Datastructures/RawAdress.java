package Datastructures;

/**
 * Created by Kraaijeveld on 21-6-2016.
 */

//I should have coded this to an interface. Food for thought.
public class RawAdress
{
    private String adress;
    private String zipCode;
    private String city;

    public RawAdress(String adress, String zipCode, String city)
    {
        this.adress = adress;
        this.zipCode = zipCode;
        this.city = city;
    }

    @Override
    public boolean equals(Object anotherObject)
    {
        RawAdress anotherAdress = (RawAdress) anotherObject;

        if(this.adress.equals(anotherAdress.adress) &&
           this.zipCode.equals(anotherAdress.zipCode) &&
           this.city.equals(anotherAdress.city))
        {
            return true;
        }
        else
            return false;
    }
}
