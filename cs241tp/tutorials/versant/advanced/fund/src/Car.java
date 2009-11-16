
import com.versant.fund.*;

public class Car 
{

	// ivar 
	String		name;
	String 		model;
	int		year;
	String		color;
	Handle		vehicle;
	
	Handle myhandle;
	static Pickler mypickler = new Car_Pickler();
	// constructor

	public Car ()
	{
	}
	
	public Car (String n, String m, int i, String j, Handle v)
	{
		super ();
		name = n;
		model = m;
		year = i;
		color = j;
		vehicle = v;
	}

	// inst methods

	public String name ()
	{
		return name;
	}

	public String model ()
	{
		return model;
	}

	public int year()
	{
		return year;
	}

	public String color()
	{
		return color;
	}

	public Handle vehicle()
	{
		return vehicle;
	}

	public void setName (String n)
	{
		name = n;
	}

	public void setModel (String n)
	{
		model = n;
	}

	public void setYear (int i)
	{
		year = i;
	}


	public void setColor (String i)
	{
		color = i;
	}

	public void setVehicle (Handle i)
	{
		vehicle = i;
	}

	public Handle becomePersistent(Session s)	
	{
		ClassHandle cls;

		try {
			cls = s.locateClass("Car");
		}
		catch (VException vex)
		{
			if (vex.getErrno() == 6002) // Class Undefined
			{
				Car.mypickler.pseudoConstructor(s);
				cls = Car.mypickler.defineClass (s);
			}
			else
				throw vex;
		}
	
		return Car.mypickler.newPersistentInstance (cls);
		
	}

	public boolean isPersistent ()
	{
		return false; //??
	}	

	public void pickle (Handle h)
        {
		Car_Pickler cp = (Car_Pickler)Car.mypickler;
		cp.pseudoConstructor(h.session());
		h.put(cp.name, name);	
		h.put(cp.model, model);	
		h.put(cp.year, year);	
		h.put(cp.color, color);	
		h.put(cp.vehicle, vehicle);
        }
 
        public void unpickle (Handle h)
        {
		Car_Pickler cp = (Car_Pickler)Car.mypickler;
		cp.pseudoConstructor(h.session());
		name = h.get(cp.name);	
		model = h.get(cp.model);	
		year = h.get(cp.year);	
		color = h.get(cp.color);	
		vehicle = h.get(cp.vehicle);
        }

        static public HandleVector select (Session s,Predicate p)
        {
		ClassHandle cls = s.locateClass("Car");
		Handle hds[];
		if (p == null)
			hds = cls.select().asArray();
		else
			hds = cls.select(p).asArray();
		HandleVector hv = s.newHandleVector(hds);
		return hv;
	}

	static public void dropClass(Session s)
	{
		ClassHandle cls = s.locateClass("Car");
		cls.dropClass();
	}
		
	static public AttrString ToName()
        {
                return ((Car_Pickler)Car.mypickler).name;
        }

	static public AttrHandle ToVehicle()
        {
                return ((Car_Pickler)Car.mypickler).vehicle;
        }

	static public HandleVector createinsts( Session s, HandleVector hve )
	{
		String[] models = { "Toyota", "Honda", "Mistibishi", "BMW", "Volvo" };
                String[] colors = { "Red", "White", "Blue", "Green", "Purple" };
		
		HandleVector hvtc = s.newHandleVector();
		for ( int i=0; i < hve.size(); i++ )
		{
			Car      c = new Car("car"+i, models[ i%5 ], 89+i, colors[ i%5 ], hve.handleAt(i) );
                	Handle hc = c.becomePersistent(s);
                	c.pickle(hc);
	                hvtc.addHandle(hc);
		}
		return hvtc;
	}

}

class Car_Pickler extends Pickler
{

	public	AttrString	name;
	public	AttrString	model;
	public	AttrInt		year;
	public	AttrString	color;
	public	AttrHandle	vehicle;
	
	public   // public ctor
	Car_Pickler() {}
	
	void pseudoConstructor (Session s)
	{
		name = s.newAttrString ("name");
		model = s.newAttrString ("model");
		year = s.newAttrInt ("year");
		color = s.newAttrString ("color");
		vehicle = s.newAttrHandle ("vehicle");
		Car.mypickler = this;		
	}

	public Handle newPersistentInstance (ClassHandle cls)
	{
		Handle h = cls.makeObject();	
		return h;
	}
		
	public ClassHandle defineClass (Session s, String dbname)
	{
		AttrBuilder[] attrs = { s.newAttrBuilder(name),
					s.newAttrBuilder(model),
					s.newAttrBuilder(year),
					s.newAttrBuilder(color),
					s.newAttrBuilder(vehicle)};
		return s.withDatabase(dbname).withAttrBuilders(attrs).defineClass ("Car");
	}
		
	public ClassHandle defineClass (Session s)
	{
		return defineClass(s, "");
	}
}
