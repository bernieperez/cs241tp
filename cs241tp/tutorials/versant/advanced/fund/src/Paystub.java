
import com.versant.fund.*;

public class Paystub 
{
	
	Handle myhandle;

	// instance variables 
	int[]		vac;
	float[]		salary;
	boolean		sex;
	boolean[]	insurance;
	float		salary_to_date;
	byte[]		emp_id;
	byte		misc1;
	String		misc2;
	short		misc3;
	short[]		misc4;
	double		misc5;
	double[]	misc6;	
	
	static Pickler mypickler = new Paystub_Pickler();

	// constructor

	public Paystub()
	{
	}

	public Paystub (int[] ia, float[] fa, boolean b, boolean[] ba, float f, byte[] bya, byte by, String s, short ss, short[] ssa, double d, double[] da)
	{
		super ();
		vac = ia;
		salary = fa;
		sex = b;
		insurance = ba;
		salary_to_date = f;
		emp_id = bya;
		misc1 = by;
		misc2 = s;
		misc3 = ss;
		misc4 = ssa;
		misc5 = d;
		misc6 = da; 
	}

	// inst methods

	public int[] vac ()
	{
		return vac;
	}

	public float[] salary()
	{
		return salary;
	}

	public boolean sex()
	{
		return sex;
	}

	public boolean[] insurance()
	{
		return insurance;
	}

	public float salary_to_date()
	{
		return salary_to_date;
	}

	public byte[] emp_id()
	{
		return emp_id;
	}

	public byte misc1()
	{
		return misc1;
	}

	public String misc2()
	{
		return misc2;
	}

	public short misc3()
	{
		return misc3;
	}

	public short[] misc4()
	{
		return misc4;
	}

	public double misc5()
	{
		return misc5;
	}

	public double[] misc6()
	{
		return misc6;
	}

	public void setVac (int[] n)
	{
		vac = n;
	}

	public void setSalary (float[] i)
	{
		salary = i;
	}


	public void setSex (boolean g)
	{
		sex = g;
	}

	public void setInsurance (boolean[] i)
	{
		insurance = i;
	}

	public void setSalary_to_date (float i)
	{
		salary_to_date = i;
	}

	public void setEmp_id (byte[] i)
	{
		emp_id = i;
	}

	public void setMisc1 (byte i)
	{
		misc1 = i;
	}

	public void setMisc2 (String i)
	{
		misc2 = i;
	}

	public void setMisc3 (short i)
	{
		misc3 = i;
	}

	public void setMisc4 (short[] i)
	{
		misc4 = i;
	}

	public void setMisc5 (double i)
	{
		misc5 = i;
	}

	public void setMisc6 (double[] i)
	{
		misc6 = i;
	}

	public Handle becomePersistent(Session s)	
	{
		ClassHandle cls;

		try {
			cls = s.locateClass("Paystub");
/*			if ( cls == null )
			{
				Paystub.mypickler.pseudoConstructor(s);
				cls = Paystub.mypickler.defineClass (s);
			}
*/
		}
		catch (VException vex)
		{
			if (vex.getErrno() == 6002)
			{
				Paystub.mypickler.pseudoConstructor(s);
				cls = Paystub.mypickler.defineClass (s);
			}
			else
				throw vex;
		}
	
		return Paystub.mypickler.newPersistentInstance (cls);
		
	}

	public boolean isPersistent ()
	{
		return false; //??
	}	

	public void pickle (Handle h)
        {
		Paystub_Pickler pp = (Paystub_Pickler)Paystub.mypickler;
		pp.pseudoConstructor(h.session());
		h.put(pp.vac, vac);	
		h.put(pp.salary, salary);	
		h.put(pp.sex, sex);
		h.put(pp.insurance, insurance);
		h.put(pp.salary_to_date, salary_to_date);
		h.put(pp.emp_id, emp_id);
		h.put(pp.misc1, misc1);
		h.put(pp.misc2, misc2);
		h.put(pp.misc3, misc3);
		h.put(pp.misc4, misc4);
		h.put(pp.misc5, misc5);
		h.put(pp.misc6, misc6);
        }
 
        public void unpickle (Handle h)
        {
		Paystub_Pickler pp = (Paystub_Pickler)Paystub.mypickler;
		pp.pseudoConstructor(h.session());
		vac = h.get(pp.vac);	
		salary = h.get(pp.salary);	
		sex = h.get(pp.sex);
		insurance = h.get(pp.insurance);
		salary_to_date = h.get(pp.salary_to_date);
		emp_id = h.get(pp.emp_id);
		misc1 = h.get(pp.misc1);
		misc2 = h.get(pp.misc2);
		misc3 = h.get(pp.misc3);
		misc4 = h.get(pp.misc4);
		misc5 = h.get(pp.misc5);
		misc6 = h.get(pp.misc6);
        }

        static public HandleVector select (Session s,Predicate p)
        {
		ClassHandle cls = s.locateClass("Paystub");
		Handle hds[];
		if (p == null)
			hds = cls.select().asArray();
		else
			hds = cls.select(p).asArray();
		HandleVector hv = s.newHandleVector(hds);
		return hv;
	}

	static public ClassHandle defineClass (Session s, String dbname)
	{
		Paystub.mypickler.pseudoConstructor(s);
        return mypickler.defineClass (s, dbname);
	}

	static public void dropClass(Session s)
	{
		ClassHandle cls = s.locateClass("Paystub");
		cls.dropClass();
	}
		
	static public HandleVector createinsts ( Session s, int num )
	{
		HandleVector hvte = s.newHandleVector();

		int[] ia = { 10, 8, 23, 11 };
                float[] fa = new float[3];
                fa[0]=(float)2300.0;
                fa[1]=(float)2400.23;
                fa[2]=(float)2200.32;
                boolean[] ba = { true, false, true ,true };
                short[] sa = { 12, 13, 23, 56 };
                double[] da = { 12342, 878743, 64536, 3847 };

		for(int i = 0; i < num; i ++)
                {
                        Paystub p;
                        if ( i%2==0 )
                                p = new Paystub(ia, fa, true, ba, (float)(230000+i), (byte[])null, (byte)i, "Hello1", (short)(1284+i), sa, 766773+i, da );
                        else
                                p = new Paystub(ia, fa, false, ba, (float)(230000+i), (byte[])null, (byte)i, "Hello", (short)(1284+i), sa, 766773+i, da );
                        Handle   hve = p.becomePersistent(s);
                        p.pickle(hve);
			hvte.addHandle(hve);
		}

		return hvte;
	}


	static public AttrBoolean ToSex()
        {
                return ((Paystub_Pickler)Paystub.mypickler).sex;
        }

	static public AttrFloat ToSalary()
        {
                return ((Paystub_Pickler)Paystub.mypickler).salary_to_date;
        }

	static public AttrByte ToMisc1()
        {
                return ((Paystub_Pickler)Paystub.mypickler).misc1;
        }

	static public AttrString8Bit ToMisc2()
        {
                return ((Paystub_Pickler)Paystub.mypickler).misc2;
        }

	static public AttrShort ToMisc3()
        {
                return ((Paystub_Pickler)Paystub.mypickler).misc3;
        }

	static public AttrDouble ToMisc5()
        {
                return ((Paystub_Pickler)Paystub.mypickler).misc5;
        }

}

class Paystub_Pickler extends Pickler
{

	public	AttrIntArray	vac;
	public	AttrFloatArray	salary;
	public	AttrBoolean	sex;
	public  AttrBooleanArray	insurance;
	public  AttrFloat	salary_to_date;
	public	AttrByteArray	emp_id;
	public  AttrByte	misc1;
	public	AttrString8Bit	misc2;
	public	AttrShort	misc3;
	public	AttrShortArray	misc4;
	public	AttrDouble	misc5;
	public	AttrDoubleArray	misc6;
	
	public   // public ctor
	Paystub_Pickler() {}
	
	void pseudoConstructor (Session s)
	{
		vac = s.newAttrIntArray("vac");
		salary = s.newAttrFloatArray("salary");
		sex = s.newAttrBoolean("sex");
		insurance = s.newAttrBooleanArray("insurance");
		salary_to_date = s.newAttrFloat("salary_to_date");
		emp_id = s.newAttrByteArray("emp_id");
		misc1 = s.newAttrByte("misc1");
		misc2 = s.newAttrString8Bit("misc2");
		misc3 = s.newAttrShort("misc3");
		misc4 = s.newAttrShortArray("misc4");
		misc5 = s.newAttrDouble("misc5");
		misc6 = s.newAttrDoubleArray("misc6");
		Paystub.mypickler = this;		
	}

	public Handle newPersistentInstance (ClassHandle cls)
	{
		Handle h = cls.makeObject();	
		return h;
	}
		
	public ClassHandle defineClass (Session s, String dbname)
	{
		AttrBuilder[] attrs = { s.newAttrBuilder(vac),
					s.newAttrBuilder(salary),
					s.newAttrBuilder(sex),
					s.newAttrBuilder(insurance),
					s.newAttrBuilder(salary_to_date),
					s.newAttrBuilder(emp_id),
					s.newAttrBuilder(misc1),
					s.newAttrBuilder(misc2),
					s.newAttrBuilder(misc3),
					s.newAttrBuilder(misc4),
					s.newAttrBuilder(misc5),
					s.newAttrBuilder(misc6)};

		return s.withDatabase(dbname).withAttrBuilders(attrs).defineClass ("Paystub");
	}
		

	public ClassHandle defineClass (Session s)
	{
		return defineClass(s, "");
	}
}
