package bank;

import java.util.Iterator;

public class Test
{
   private static DBService dbService;
   private static LocalService localService;

   public static void main (String[] args)
      throws Exception
   {
      localService = new LocalService ();
       dbService = new DBService(args);

      System.out.println ("Start: =======");

      System.out.println ("Populate: =======");

      populate ();
      
      System.out.println ("Display By Name: =======");
      displayCustomerByName("Smith,John");
      System.out.println ("Display By SSN: =======");
      displayCustomerBySSN("111-11-1111");
      System.out.println ("Move Money and Display: =======");
      moveMoney("111-11-1111");
      displayCustomerBySSN("111-11-1111");
      System.out.println ("Display All Custmoers: =======");
      displayAllCustomers();
     
   }

 public static void populate ()
      throws Exception
   {
      Customer john = localService.newCustomer("Smith,John", "111-11-1111");
      Customer bill = localService.newCustomer("Jones,William", "222-22-2222");
      john.setPrimaryAddress(newAddress(1));
      bill.setPrimaryAddress(newAddress(2));
      localService.openAccount(john,"checking");
      localService.openAccount(john,"saving");
      localService.openAccount(bill,"checking");
      localService.openAccount(bill,"saving");
      
      dbService.saveCustomer(john);
      dbService.saveCustomer(bill);
     
   }
 
 public static void moveMoney(String ssn)
 throws Exception
 {
     Customer customer = dbService.customerBySSN(ssn);
     Account acct1 = customer.getByType("checking");
     Account acct2 = customer.getByType("saving");
     localService.transfer(acct2,acct1,50);
     dbService.saveCustomer(customer);
         
 }
 
 public static void displayCustomer(Customer customer)
 throws Exception
 {
     System.out.printf("Customer Name: %s SSN: %s\n",customer.getName(), customer.getSsn());
     for(Account account : customer.getAccounts()){
         System.out.printf("Account Number: %s Balance: %s Type: %s\n", account.getNumber(), account.getBalance(),account.getType());
         
     }    
     
 }
 
 public static void displayCustomerByName(String name)
 throws Exception
 {
     
     Customer customer = dbService.customerByName(name);
     displayCustomer(customer);      
     
 }
 
 public static void displayCustomerBySSN(String ssn)
 throws Exception
 {
     Customer customer = dbService.customerBySSN(ssn);
     displayCustomer(customer);
     
     
 }
 
 public static void displayAllCustomers()
 throws Exception
 {
    for(Iterator iter = dbService.allCustomers(); iter.hasNext();)
    {
        Customer customer = (Customer)iter.next();
        displayCustomer(customer);
        
    }
       
        
 }
 
 private static Address newAddress (int i)
{
   Address address = new Address ();
   switch (i)
      {
         case 1:
            address.setCity ("Cambridge");
            address.setStreet ("One Memorial Dr.");
            address.setZip ("02142");
            address.setState ("MA");
            break;
         case 2:
            address.setCity ("Newton Center");
            address.setStreet ("25 Glen Ave");
            address.setZip ("02459");
            address.setState ("MA");
            break;
         default:
            throw new IllegalArgumentException ("No address #" + i);
      }
   return address;
}
 
 
   
}
