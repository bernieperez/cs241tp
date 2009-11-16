
import com.versant.fund.*;

public class Garage 
{

	// ivar 
	String		name;
	Handle[]	cars;
	
	Handle myhandle;	
	static Pickler mypickler = new Garage_Pickler();
	// constructor

	public Garage ()
	{
	}

	public Garage (String n, Handle[] ha)
	{
		super ();
		name = n;
		cars = ha;
	}

	// inst methods

	public String name ()
	{
		return name;
	}

	public Handle[] cars()
	{
		return cars;
	}

	public void setName (String n)
	{
		name = n;
	}

	public void setCars (Handle[] c)
	{
		cars = c;
	}


	public Handle becomePersistent(Session s)	
	{
		ClassHandle cls;

		try {
			cls = s.locateClass("Garage");
		}
		catch (VException vex)
		{
			if (vex.getErrno() == 6002)
			{
				Garage.mypickler.pseudoConstructor(s);
				cls = Garage.mypickler.defineClass (s);
			}
			else
				throw vex;
		}
	
		return Garage.mypickler.newPersistentInstance (cls);
		
	}

	public boolean isPersistent ()
	{
		return false; //??
	}	

	public void pickle (Handle h)
        {
		Garage_Pickler gp = (Garage_Pickler)Garage.mypickler;
		gp.pseudoConstructor(h.session());
		h.put(gp.name, name);	
		h.put(gp.cars, cars);	
        }
 
        public void unpickle (Handle h)
        {
		Garage_Pickler gp = (Garage_Pickler)Garage.mypickler;
		gp.pseudoConstructor(h.session());
		name = h.get(gp.name);	
		cars = h.get(gp.cars);	
        }

        static public HandleVector select (Session s,Predicate p)
        {
		ClassHandle cls = s.locateClass("Garage");
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
		ClassHandle cls = s.locateClass("Garage");
		cls.dropClass();
	}
		
	static public HandleVector createinsts( Session s, HandleVector hvtc, int num )
	{
		HandleVector hvtg = s.newHandleVector();

		for (int i=0; i< num; i++ )
                {
                        Handle[] tmp = { hvtc.handleAt((i+11)%3), hvtc.handleAt((i+11)%4+3), hvtc.handleAt((i+17)%3+7) };
                        Garage  g = new Garage("gar"+i, tmp);
			if ( i == 3 )
			{
				Handle[] t = { hvtc.handleAt(0)};
                        	g = new Garage("gar"+i, t);
			}

//                        Garage  g = new Garage("gar"+i, tmp);
                        Handle hg = g.becomePersistent(s);
                        g.pickle(hg);
                        hvtg.addHandle(hg);
                }
		return hvtg;
	}

}

class Garage_Pickler extends Pickler
{

	public	AttrString	name;
	public	AttrHandleArray	cars;
	
	public   // public ctor
	Garage_Pickler() {}
	
	void pseudoConstructor (Session s)
	{
		name = s.newAttrString ("name");
		cars = s.newAttrHandleArray ("cars");
		Garage.mypickler = this;		
	}

	public Handle newPersistentInstance (ClassHandle cls)
	{
		Handle h = cls.makeObject();	
		return h;
	}
		
	public ClassHandle defineClass (Session s, String dbname)
	{
		AttrBuilder[] attrs = { s.newAttrBuilder(name),
								s.newAttrBuilder(cars)};
		return s.withDatabase(dbname).withAttrBuilders(attrs).defineClass ("Garage");
	}
		
	public ClassHandle defineClass (Session s)
	{
		return defineClass (s, "");
	}

}
