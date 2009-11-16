package bank;

import com.jalapeno.annotations.Access;
import com.jalapeno.annotations.AccessType;
import com.jalapeno.annotations.Embeddable;


@Access(type = AccessType.PROPERTY)
@Embeddable
public class Address{
    private String mCity;
    private String mState;
    private String mStreet;
    private String mZip;



    public Address() {
    }
    public String getCity()  {
        return mCity;
    }
    public void setCity(String value)  {
        mCity = value;
    }
    public String getState()  {
        return mState;
    }
    public void setState(String value)  {
        mState = value;
    }
    public String getStreet()  {
        return mStreet;
    }
    public void setStreet(String value)  {
        mStreet = value;
    }
    public String getZip()  {
        return mZip;
    }
    public void setZip(String value)  {
        mZip = value;
    }
}
