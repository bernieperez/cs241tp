
import com.versant.fund.*;

public class Vehicle 
{

	// ivar 
	String		plate;
	
	Handle myhandle;
	static Pickler mypickler = new Vehicle_Pickler();
	// constructor

	public Vehicle ()
	{
	}

	public Vehicle (String n)
	{
		super ();
		plate = n;
	}

	// inst methods

	public String plate ()
	{
		return plate;
	}

	public void setPlate (String n)
	{
		plate = n;
	}

	public Handle becomePersistent(Session s)	
	{
		ClassHandle cls;

		try {
			cls = s.locateClass("Vehicle");
		}
		catch (VException vex)
		{
			if (vex.getErrno() == 6002) // Class Undefined
			{
				Vehicle.mypickler.pseudoConstructor(s);
				cls = Vehicle.mypickler.defineClass (s);
			}
			else
				throw vex;
		}
	
		return Vehicle.mypickler.newPersistentInstance (cls);
		
	}

	public boolean isPersistent ()
	{
		return false; //??
	}	

	public void pickle (Handle h)
        {
		Vehicle_Pickler vp = (Vehicle_Pickler)Vehicle.mypickler;
		vp.pseudoConstructor(h.session());
		h.put(vp.plate, plate);	
        }
 
        public void unpickle (Handle h)
        {
		Vehicle_Pickler vp = (Vehicle_Pickler)Vehicle.mypickler;
		vp.pseudoConstructor(h.session());
		plate = h.get(vp.plate);	
        }

        static public HandleVector select (Session s,Predicate p)
        {
		ClassHandle cls = s.locateClass("Vehicle");
		Handle hds[];
		if (p == null)
			hds = cls.select().asArray();
		else
			hds = cls.select(p).asArray();
		HandleVector hv = s.newHandleVector(hds);
		return hv;
	}

	static public HandleVector createinsts( Session s, String[] ps )
	{
		HandleVector hve = s.newHandleVector();
		for ( int i=0; i< ps.length; i++)
		{
			Vehicle  v = new Vehicle(ps[i]);
                        Handle h = v.becomePersistent(s);
                        v.pickle(h);
			hve.addHandle(h);
		}
		return  hve;
	}

	static public void dropClass(Session s)
	{
		ClassHandle cls = s.locateClass("Vehicle");
		cls.dropClass();
	}
		
}

class Vehicle_Pickler extends Pickler
{

	public	AttrString	plate;
	
	public   // public ctor
	Vehicle_Pickler() {}
	
	void pseudoConstructor (Session s)
	{
		plate = s.newAttrString ("plate");
		Vehicle.mypickler = this;		
	}

	public Handle newPersistentInstance (ClassHandle cls)
	{
		Handle h = cls.makeObject();	
		return h;
	}
		
	public ClassHandle defineClass (Session s, String dbname)
	{
		AttrBuilder[] attrs = { s.newAttrBuilder(plate) };
		return s.withDatabase(dbname).withAttrBuilders(attrs).defineClass ("Vehicle");
	}
		
	public ClassHandle defineClass (Session s)
	{
		return defineClass(s, "");
	}
	
}
