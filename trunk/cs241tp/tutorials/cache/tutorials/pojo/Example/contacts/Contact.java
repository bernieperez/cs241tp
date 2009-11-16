/*
 * Contact.java
 *
 * Created on August 4, 2006, 11:19 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package contacts;

import com.jalapeno.annotations.*;
import java.util.ArrayList;
import java.util.List;

@Access(type = AccessType.FIELD, level = AccessLevel.PRIVATE)
@Index(name = "NameIndex", propertyNames = { "name" }, isUnique = false, isPrimaryKey = false)
public class Contact {

   private Address primaryAddress;

  @ID(type = IDType.SYSTEM_ASSIGNED)
  protected String idPlaceHolder;
    private String name;

     @PropertyParameters({

     @PropertyParameter(name="VALUELIST", value=",Business,Personal"),

     @PropertyParameter(name="DISPLAYLIST", value=",Bus,Pers")
    })

    @CacheProperty (name="ContactType", type="%Library.String")
    private String type;


   @Relationship(type=RelationshipType.ONE_TO_MANY,inverseClass="contacts.PhoneNumber",inverseProperty="owner")
    public List<PhoneNumber> phoneNumbers;

    public Contact() {

      setPhoneNumbers(new ArrayList <PhoneNumber> ());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


   public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

   public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }



}
