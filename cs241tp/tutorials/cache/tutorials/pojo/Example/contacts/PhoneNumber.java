/*
 * PhoneNumber.java
 *
 * Created on August 4, 2006, 11:19 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package contacts;
import com.jalapeno.annotations.*;

@Access(type = AccessType.FIELD,level = AccessLevel.PRIVATE)
@Index(name = "NumberIndex", propertyNames = { "number" }, isPrimaryKey = true)
public class PhoneNumber {

    @PropertyParameters({

     @PropertyParameter(name="VALUELIST", value=",Home,Office,Mobile"),

     @PropertyParameter(name="DISPLAYLIST", value=",H,O,M")
    })
    @CacheProperty(name="PhoneNumberType", type="%Library.String")
    private String type;

    @Relationship(type=RelationshipType.MANY_TO_ONE,inverseClass="contacts.Contact",inverseProperty="phoneNumbers")
    private Contact owner;
    private String number;

    public PhoneNumber() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Contact getOwner() {
        return owner;
    }

    public void setOwner(Contact owner) {
        this.owner = owner;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
