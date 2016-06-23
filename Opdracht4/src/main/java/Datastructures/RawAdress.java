package Datastructures;

/**
 * Created by Kraaijeveld on 21-6-2016.
 */

//I should have coded this to an interface. Food for thought.
public class RawAdress
{
    private String street;
    private String zipCode;
    private String city;
    private String complaintType;

    public RawAdress(String street, String zipCode, String city, String complaintType)
    {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.complaintType = complaintType;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getComplaintType()
    {
        return complaintType;
    }

    @Override
    public boolean equals(Object anotherObject)
    {
        RawAdress anotherAdress = (RawAdress) anotherObject;

        if (this.street.equals(anotherAdress.street) &&
           this.zipCode.equals(anotherAdress.zipCode) &&
                this.city.equals(anotherAdress.city) &&
                this.complaintType.equals(anotherAdress.complaintType))
        {
            return true;
        }
        else
            return false;
    }
}
