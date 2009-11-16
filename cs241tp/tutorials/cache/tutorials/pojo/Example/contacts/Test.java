/*
 * Test.java
 *
 * Created on August 4, 2006, 3:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package contacts;
import java.util.Iterator;
import java.util.Random;

public class Test {
    private static DBService service;
    private static Random random;
    public Test() {
    }
    
    public static void main(String[] args)
    {
       try{
        service = new DBService();
        
        System.out.println("Start: =======");
        
        
        System.out.println("Populate: =======");
        populate();
        
        System.out.println("List All Contacts: =============");
        listContacts();
        
        
        System.out.println("List One Contact: ============");
        displayContact(service.contactsByName("Smith, John J."));
        
        System.out.println("Update and Display One Contact: ==============");
        updateContact(service.contactsByName("Smith, John J."));
        displayContact(service.contactsByName("Smith, John J."));
        
        System.out.println("Display Phones By Name: ==============");
        displayPhonesByName("Smith, John J.");
        System.out.println("End: ==========");
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
    }
    
    public static void populate() throws Exception{
        Contact contact = new Contact();
        contact.setType("Personal");
        contact.setName("Smith, John J.");
        contact.setPrimaryAddress(newAddress(1));
        for (int i=0;i<3;i++) {
            insertPhoneNumber(contact);
        }
        service.saveContact(contact);
        addDuplicatePhoneNumber(contact);
        
        contact = new Contact();
        contact.setType("Business");
        contact.setName("Smith, Jane J.");
        for (int i=0;i<5;i++) {
            insertPhoneNumber(contact);
        }
        contact.setPrimaryAddress(newAddress(2));
        service.saveContact(contact);
        
        contact = new Contact();
        contact.setType("Business");
        contact.setName("Jones, John P.");
        for (int i=0;i<6;i++) {
            insertPhoneNumber(contact);
        }
        contact.setPrimaryAddress(newAddress(3));
        service.saveContact(contact);    
    }
    
    private static void updateContact(Contact contact)
    throws Exception
    {
        if(contact.getType().equals("Business")){
            contact.setType("Personal");
        }
        else contact.setType("Business");
        service.saveContact(contact);
        
        
    }
    private static void displayContact(Contact contact)
    {
        Address addr = contact.getPrimaryAddress();
        System.out.printf("Name: %s Type: %s \n", contact.getName(),
                contact.getType());
        System.out.printf("Address: %s %s %s %s\n", addr.getStreet(),
                addr.getCity(), addr.getState(), addr.getZip());
        for (PhoneNumber pn: contact.getPhoneNumbers()) {
            System.out.printf("\t%s\t%s\n",pn.getNumber(),
                    pn.getType());
            
        }
        
        
    }
    
    private static void listContacts()
    throws Exception {
        for (Iterator it = service.allContacts(); it.hasNext();) {
            Contact contact = (Contact)it.next();
            displayContact(contact);
            
        }
        
    }
    
    private static void displayPhonesByName(String name)
    throws Exception {
        
        System.out.printf("Phone Numbers for %s\n", name);
        for (Iterator it = service.phonesByName(name); it.hasNext();)
        {
            PhoneNumber phone = (PhoneNumber)it.next();
            System.out.printf("Number: %s Type: %s\n",phone.getNumber(), 
                                                        phone.getType());
            
        }
        
    }
        
    private static void addDuplicatePhoneNumber(Contact contact)
    throws Exception
    {
        PhoneNumber pn2 = new PhoneNumber();
        PhoneNumber pn1 = null;
             
            Iterator iter = contact.getPhoneNumbers().iterator();
            iter.next();
            pn1 = (PhoneNumber)iter.next();
            pn2.setNumber(pn1.getNumber());
            pn2.setType("Office");
            pn2.setOwner(contact);
            contact.getPhoneNumbers().add(pn2);
            service.saveContact(contact);
            
    }
    
    private static void insertPhoneNumber(Contact contact) {
        PhoneNumber pn = new PhoneNumber();
        contact.getPhoneNumbers().add(pn);
        
        if (random == null)
            random = new java.util.Random();
        
        StringBuffer sb = new StringBuffer();
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append('-');
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append('-');
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        
        pn.setNumber(sb.toString());
        
        int i = random.nextInt(3);
        
        switch (i) {
            case 0:
                pn.setType("Home");
                break;
            case 1:
                pn.setType("Office");
                break;
            case 2:
                pn.setType("Mobile");
                break;
            default:
                pn.setType("Home");
        }
        
    }
    private static Address newAddress(int i) {
        Address address = new Address();
        switch (i) {
            case 1:
                address.setCity("Cambridge");
                address.setStreet("One Memorial Dr.");
                address.setZip("02142");
                address.setState("MA");
                break;
            case 2:
                address.setCity("Newton");
                address.setStreet("25 Glendale Avenue");
                address.setZip("02459");
                address.setState("MA");
                break;
            case 3:
                address.setCity("Worcester");
                address.setStreet("100 Main Street");
                address.setZip("03129");
                address.setState("MA");
                break;
            default:
                throw new IllegalArgumentException("No address #" + i);
        }
        return address;
    }
    
    
    
}
