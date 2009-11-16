package bank;

import com.jalapeno.annotations.Index;
import com.jalapeno.annotations.PropertyParameter;
import com.jalapeno.annotations.Relationship;
import com.jalapeno.annotations.RelationshipType;

@Index (name="numIdx", propertyNames={"number"},isPrimaryKey=true)
public class Account
{
    @Relationship(type=RelationshipType.MANY_TO_ONE,inverseClass="Customer",
        inverseProperty="accounts")
   private Customer owner;
   private long number;
   
   @PropertyParameter(name="VALUELIST", value=",checking,saving")
   private String type;
   private float balance;

   public void withdraw (float amount)
   {
      setBalance(getBalance() - amount);
   }

   public void deposit (float amount)
   {
      setBalance(getBalance() + amount);
   }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

}
