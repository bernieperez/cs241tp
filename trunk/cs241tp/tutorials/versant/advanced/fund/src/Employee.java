
import com.versant.fund.*;

public class Employee 
{

	// ivar 
	String		name;
	int		id;
	Handle[]	garages;
	Handle		paystub;
	
        Handle myhandle;
	static Pickler mypickler = new Employee_Pickler();
	// constructor

	public Employee()
	{
	}

	public Employee (String n, int i)
	{
		super();
		name = n;
		id = i;
	}

	public Employee (String n, int i, Handle[] g)
	{
		super ();
		name = n;
		id = i;
		garages = g;
	}

	public Employee (String n, int i, Handle[] g, Handle p)
	{
		super ();
		name = n;
		id = i;
		garages = g;
		paystub = p;
	}

	// inst methods

	public String name ()
	{
		return name;
	}

	public int id()
	{
		return id;
	}

	public Handle[] garages()
	{
		return garages;
	}

	public Handle paystub()
	{
		return paystub;
	}

	public void setName (String n)
	{
		name = n;
	}

	public void setID (int i)
	{
		id = i;
	}


	public void setGarages (Handle[] g)
	{
		garages = g;
	}

	public void setPaystub (Handle p)
	{
		paystub = p;
	}

	public Handle becomePersistent(Session s)	
	{
		ClassHandle cls;

/*			mypickler.pseudoConstructor(s);
			cls = Employee.mypickler.defineClass (s);
*/
		try {
			cls = s.locateClass("Employee");
		}
		catch (VException vex)
		{
			if (vex.getErrno() == 6002)
			{
				Employee.mypickler.pseudoConstructor(s);
				cls = Employee.mypickler.defineClass (s);
			}
			else
				throw vex;
		}
	
		return mypickler.newPersistentInstance (cls);
		
	}

	public boolean isPersistent ()
	{
		return false; //??
	}	

	public void pickle (Handle h)
        {
		Employee_Pickler ep = (Employee_Pickler)Employee.mypickler;
		ep.pseudoConstructor(h.session());
		h.put(ep.name, name);	
		h.put(ep.id, id);	
		h.put(ep.garages, garages);
		h.put(ep.paystub, paystub);
        }
 
        public void unpickle (Handle h)
        {
		Employee_Pickler ep = (Employee_Pickler)Employee.mypickler;
		ep.pseudoConstructor(h.session());
		name = h.get(ep.name);	
		id = h.get(ep.id);	
		garages = h.get(ep.garages);
		paystub = h.get(ep.paystub);
        }

        static public HandleVector select (Session s,Predicate p)
        {
		ClassHandle cls;
		try {
			cls = s.locateClass("Employee");
		} catch (VException vex) {
            if (vex.getErrno() == 6002) {
				return s.newHandleVector();
			}
			else 
				throw vex;
		}		
             	
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
		mypickler.pseudoConstructor(s);
        return mypickler.defineClass (s, dbname);
	}

	static public void dropClass(Session s)
	{
		ClassHandle cls;
		try { 
			cls = s.locateClass("Employee");
		} catch (VException vex) {
			if (vex.getErrno() == 6002) {
				return;
			}
			else
				throw vex;
		}
		cls.dropClass();
	}
		
	static public HandleVector createinsts ( Session s, HandleVector hvtg, int num )
	{
		HandleVector hvt = s.newHandleVector();

		for (int i=0; i< num; i++ )
                {
                        Handle[] tmp = { hvtg.handleAt((i+13)%5), hvtg.handleAt(i%5+5) };               
			if ( i == 0 )
			{
				tmp[0] = hvtg.handleAt(3);
				tmp[1] = null;
			}

                        Employee e = new Employee("john"+i, i, tmp);
                        Handle h = e.becomePersistent(s);
			try {
                        e.pickle(h);
			}
			catch ( VException vex )
			{
				if ( vex.getErrno() == 9616) {
					System.out.println("ignoring " + vex );
					Handle[] t = { hvtg.handleAt(3) };
					h.deleteObject();
                     			e = new Employee("john"+i, i, t);
                        		h = e.becomePersistent(s);
                        		e.pickle(h);
				}
				else 
					throw vex;
			}
				
                        hvt.addHandle(h);
                }

		return hvt;
	}

	static public HandleVector createinsts_withall ( Session s, HandleVector hvtg, HandleVector hvtp, int num )
	{
		HandleVector hvt = s.newHandleVector();

		for (int i=0; i< num; i++ )
                {
			Handle[] tmp = null;
			if ( hvtg == null )
				tmp = null;
			else
			{
                       		tmp [0] = hvtg.handleAt((i+13)%5);
				tmp [1] = hvtg.handleAt(i%5+5);               
			}

                        Employee e = new Employee("john"+i, i, tmp, hvtp.handleAt(i));
                        Handle h = e.becomePersistent(s);
                        e.pickle(h);
                        hvt.addHandle(h);
                }

		return hvt;
	}
}


class Employee_Pickler extends Pickler
{

	public	AttrString	name;
	public	AttrInt		id;
	public	AttrHandleArray	garages;
	public  AttrHandle	paystub;
	
	public   // public ctor
	Employee_Pickler() {}
	
	void pseudoConstructor (Session s)
	{
		name = s.newAttrString ("name");
		id = s.newAttrInt ("id");
		garages = s.newAttrHandleArray ("garages");
		paystub = s.newAttrHandle ("paystub");
		Employee.mypickler = this;		
	}

	public Handle newPersistentInstance (ClassHandle cls)
	{
		Handle h = cls.makeObject();	
		return h;
	}
		
	public ClassHandle defineClass (Session s, String dbname)
	{
		AttrBuilder[] attrs = { s.newAttrBuilder(name),
					s.newAttrBuilder(id),
					s.newAttrBuilder(garages),
					s.newAttrBuilder(paystub)};
		return s.withDatabase(dbname).withAttrBuilders(attrs).defineClass ("Employee");
	}
		
	public ClassHandle defineClass (Session s)
	{
		return defineClass (s, "");
	}

}
