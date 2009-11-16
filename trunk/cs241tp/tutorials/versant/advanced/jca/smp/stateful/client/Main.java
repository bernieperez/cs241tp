package smp.stateful.client;
import smp.stateful.*;

import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import java.util.*;
import java.text.SimpleDateFormat;
import java.rmi.RemoteException;

/**
 * Client class for the stateless session bean. This looks up the bean and
 * invokes methods to demonstrate using the JVI JCA Connector.
 */
public class Main {


    public static void main(String[] args) {

	 int[] SSN_LIST   = {111111111,123456789,987654321,222222222,333333333};
	 String[] NAME_LIST = {"Robert Mundell","James Heckman","Daniel McFadden","Robert Merton","George Ackerlof"};
     String[] MAIL_LIST = {"robert@versant.com","james@versant.com","daniel@versant.com","rmerton@versant.com","george@versant.com"};

        try {

            // lookup the stateful session bean
            InitialContext context = new InitialContext();
            Object obj = null;
            try {
                obj = context.lookup("smp.stateful.BankHome");
            } catch (NameNotFoundException x) {
                System.out.println("\n Name not found " + x);
            }

             BankHome home = (BankHome)PortableRemoteObject.narrow(obj,BankHome.class);
			 Bank bank = home.create();

			 /* Create some members */
			 System.out.println("\nStoring Member Information to the database : ");

			 for (int i = 0 ; i<5 ; i++)
			 {
				   bank.beginTransaction();
				   try
			       {
			 	   String msg = bank.createMember(SSN_LIST[i],NAME_LIST[i],MAIL_LIST[i]);
			 	   System.out.println("Created member : " + SSN_LIST[i] + "  " + NAME_LIST[i] + "  " + MAIL_LIST[i]);
			 	   }catch(Exception e)
			 	     {
						 System.out.println("\n Exception : " + e);
                         //Rolling back the transaction.
						 bank.rollbackTransaction();
						 continue;
					 }
					bank.commitTransaction();

			 }



			 // Add account details for the members
			 System.out.println("\nAdding Account details for Members ");
			 int accountType = 0;
			 int amount = 10000;

			 for (int i = 0 ; i<5 ; i++)
			 {
                bank.beginTransaction();
			    /* Account type could be either CHECKING or SAVING account */
			  	if (i%2==0)
			       accountType = 0;
			    else
			       accountType = 1;
			    try
			    {
			    String msg = bank.newAccount(SSN_LIST[i],accountType,amount+500);
			    System.out.println("Account Info added for the Member with SSN " + SSN_LIST[i] +  " : " + msg);
			    }catch(Exception e)
			     {
					System.out.println("\n Exception : "  + e);
					//Rolling back the transaction.
					bank.rollbackTransaction();
				    continue;
				 }
				bank.commitTransaction();
			 }


			  // Find the account details for a particular member by searching for ssn
			 try
			  {
			       String msg=bank.findMember(SSN_LIST[3]);
			       System.out.println("\n Member details lookup for Member with SSN " + SSN_LIST[3] + " : " +  msg);
			   }catch(Exception e)
			   {
			       System.out.println("\n Exception : " + e);
			   }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }



    }

}

