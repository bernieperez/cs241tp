package model;

import java.util.*;
import java.io.*;

/**
* A persistent capable class with accessor methods for its properties
* representing the members of the bank.
*/

public class Member
{
  private long ssn;
  private String name;
  private Vector memberAccounts;
  private String emailAddress;

  public Member () {
    memberAccounts = new Vector ();
  }

  public Member (long ssn, String name, String emailAddress) {
    memberAccounts = new Vector();
    this.ssn = ssn;
    this.name = name;
    this.emailAddress = emailAddress;
    memberAccounts = new Vector();
  }

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  public long getSsn () {
    return ssn;
  }

  public void setSsn (long ssn) {
    this.ssn = ssn;
  }

  public String getEmailAddress () {
    return emailAddress;
  }

  public void setEmailAddress (String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public void addAccount (Account account) {
    memberAccounts.addElement (account);
  }

  public void removeAccount (Account account) {
    memberAccounts.removeElement (account);
  }

  public Account getAccount (String accountNumber) {
    Account acc = null;

    try {
      int val = Integer.parseInt (accountNumber);
      for (int i=0;i<memberAccounts.size ();++i) {
        acc = (Account)memberAccounts.get (i);
        if (val == acc.getAccountNumber ())
          return acc;
      }
    } catch (Exception ex) {
      return acc;
    }

    return acc;
  }

  public Vector getMemberAccounts () {
    return memberAccounts;
  }

  public boolean hasMoreAccounts () {
    if (memberAccounts.size () <= 0)
      return false;
    else
      return true;
  }

  public void setMemberAccounts (Vector accounts) {
    this.memberAccounts = accounts;
  }

  public boolean hasAccountOfType (int accountType) {
    for (int i=0;i<memberAccounts.size ();++i) {
      Account acc = (Account)memberAccounts.get (i);
      if (accountType == acc.getAccountType ())
        return true;
    }

    return false;
  }

  public String toString () {
    return name + "; Email Address " + emailAddress;
  }
}
