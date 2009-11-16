package DataBaseUtility.model;

import com.versant.trans.*;

public class Student
{
	public int rn;
	public String name;

	public Student()
	{}
	public Student(String n,int r)
	{
		name=n;
		rn=r;
	}
	public Student(String n)
	{
		name=n;
	}
	public String toString()
	{
		return "student: " + name + " roll: " + rn;
	}
}
