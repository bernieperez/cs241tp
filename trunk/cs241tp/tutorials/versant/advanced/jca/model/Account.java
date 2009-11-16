package model;

import java.util.*;
import java.io.*;

/**
* A persistent capable class with accessor methods for all its properties
* representing the accounts of the all the members of a bank.
*/
public class Account
{
  private int accountNumber;
  private int accountType;
  private double totalAmount;

  public Account () {
  }

  public int getAccountNumber () {
    return accountNumber;
  }

  public void setAccountNumber (int accountNumber) {
    this.accountNumber = accountNumber;
  }

  public int getAccountType () {
    return accountType;
  }

  public String getAccountTypeAsString () {
    if (getAccountType () == 1)
      return "CHECKING";
    else
      return "SAVINGS";
  }

  public void setAccountType (int accountType) {
    this.accountType = accountType;
  }

  public double getTotalAmount () {
    return totalAmount;
  }

  public void setTotalAmount (double amount) {
    this.totalAmount = amount;
  }

  public String toString () {
    StringBuffer strBuf = new StringBuffer ();
    strBuf.append (getAccountNumber ());

    return strBuf.toString ();
  }
}
