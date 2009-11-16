package bank;

import com.jalapeno.annotations.ID;
import com.jalapeno.annotations.IDType;
import com.jalapeno.annotations.Index;
import com.jalapeno.annotations.PropertyParameter;
import com.jalapeno.annotations.Relationship;
import com.jalapeno.annotations.RelationshipType;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;



@Index (name="ssnIdx",propertyNames={"ssn"},isPrimaryKey=true)
public class Customer
{
    @ID(type = IDType.SYSTEM_ASSIGNED)
    protected String idPlaceHolder;
    
    private Address primaryAddress;
    private String name;
    
    @PropertyParameter (name = "PATTERN", value = "3N1\"-\"2N1\"-\"4N")
    private String ssn;
     @Relationship(type=RelationshipType.ONE_TO_MANY,inverseClass="Account",
        inverseProperty="owner")
    private List <Account>accounts;
    public Customer ()
    {
        setAccounts(new ArrayList<Account>());
    }

    public Account getByType (String type)
    {
        for (Iterator it = getAccounts().iterator (); it.hasNext ();)
            {
                Account account = (Account) it.next ();
                if (account.getType().equals (type))
                    return account;
            }

        return null;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
