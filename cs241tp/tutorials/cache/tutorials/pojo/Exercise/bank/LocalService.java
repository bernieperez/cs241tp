package bank;

import java.util.Iterator;
import java.util.Random;

public class LocalService
{
    private Random mRandom;

    public void transfer (Account from, Account to, float amount)
    {
        from.withdraw (amount);
        to.deposit (amount);
    }

    public Customer newCustomer (String name, String ssn)
        throws Exception
    {
        Customer customer = new Customer ();
        customer.setName(name);
        customer.setSsn(ssn);
        return customer;
    }

   public Account openAccount (Customer customer, String type)
        throws Exception
    {
        Account account = new Account ();
        //account.owner = customer;
        account.setBalance(0);
        account.setType(type);
        account.setNumber(nextNumber ());
        account.setBalance(100);

        customer.getAccounts().add (account);
        return account;
    }
private synchronized long nextNumber ()
    {
        if (mRandom == null)
            mRandom = new Random (1);
      long number = System.currentTimeMillis ();
        number += mRandom.nextInt (100);

        return number;
    }

    
    
 
}
