
import com.versant.fund.* ;
import java.util.Properties;

public class PathQueryExample 
{ 

    public static void main (String args[])
    {
        // first argument is the database name
        if (args.length < 1) {
            System.out.println ("Usage: PathQueryExample <db> ");
            System.exit (-1);
        }

        // start a session using FundSession
        String db= args[0];
        Capability capability= new NewSessionCapability();
        Properties properties = new Properties();
        properties.put("database", db);
        FundSession session = new FundSession(properties, capability);

        // populate the database with Employee, Garage, Car and Vehicle
        // objects
        populate (session);
        say("Finished creating Employee, Garage, Car and Vehicle objects.");
        session.commit();

        // create Predicate objects for path query:

        // to connect the nodes on the path, one way is to use "\t"
        Predicate p1 = session.newAttrString("garages\tcars\tcolor").eq("Red");

        
        // the other way is to use the pathTo() methods
        AttrHandleArray garages = session.newAttrHandleArray ("garages");
        AttrHandleArray cars    = session.newAttrHandleArray ("cars");
        AttrHandle vehicle      = session.newAttrHandle ("vehicle");
        AttrString plate        = session.newAttrString ("plate");
        Predicate p2 = garages.pathToHandleArray(cars).pathToHandle(vehicle).
                       pathToString(plate).matches("2YOU*");

        // carry out the path queries:
        
        // first get the ClassHandle to the Employee class
        ClassHandle empCls = session.locateClass ("Employee");

        // then pass a predicate to the select method
        say("**** find all employees who own a red car ****");
        Handle[] result = empCls.select(p1).asArray();
        say("found " + result.length);

        say("**** find all employees who own a car with license plate " +
        "starting with '2YOU' ****");
        result = empCls.select(p2).asArray();
        say("found " + result.length);

        // you can also use FundVQLQuery which supports substitution
        String vqlString = "select selfoid from Employee where " +
                            "garages->cars->year > $1";
        FundVQLQuery vql = new FundVQLQuery (session, vqlString);
        vql.bind (new Integer(89));
        HandleEnumeration enumeration = vql.execute();
        say("**** find all employees who own a car made after year '89 ****");
        int count = 0;
        while (enumeration.hasMoreHandles()) {
            enumeration.nextHandle();
            count++;
        }
        say("found " + count);

        session.endSession();
    } //main
		

    // helper methods for main

    public static void say (String whatToSay)
    {
        System.out.println (whatToSay);
   }

    public static void populate(FundSession s)
    {
	String[] models = { "Toyota", "Honda", "Mistibishi", "BMW", "Volvo" };
	String[] colors = { "Red", "White", "Blue", "Green", "Purple" };

	String[] plates = { "1AOI847", "COROLA 2", "3 NOW 90", "7ABC987", "5POP848", "2UIT576", "COROLA 1", "1BNM746", "3MIT444", "2YOU333" };

	HandleVector carVector = s.newHandleVector(); 
	HandleVector garageVector = s.newHandleVector();
		
        // create Vehicle and Car objects: a car references a vehicle
	for(int i = 0; i < 10; i ++) 
	{
            Vehicle  v = new Vehicle(plates[i]);
            Handle   hv = v.becomePersistent(s);
            v.pickle(hv);

            Car	 c = new Car("car"+i, models[ i%5 ], 89+i, colors[ i%5 ], hv );
            Handle hc = c.becomePersistent(s);
            c.pickle(hc);
            carVector.addHandle(hc);
	}

        // create Garage objects: a garage references multiple cars
	for (int i=0; i< 10; i++ )
	{
            Handle[] tmp = { carVector.handleAt((i+11)%3), carVector.handleAt((i+11)%4+3), carVector.handleAt((i+17)%3+7) };
            Garage g = new Garage("gar", tmp);
            Handle hg = g.becomePersistent(s);
            g.pickle(hg);
            garageVector.addHandle(hg);
	}

        // create Employee objects: an employee references multiple garages
	for (int i=0; i< 10; i++ )
	{
            Handle[] tmp = { garageVector.handleAt((i+13)%5), garageVector.handleAt(i%5+5) };
            Employee e = new Employee("john"+i, i, tmp);
            Handle h = e.becomePersistent(s);
            e.pickle(h);	
	}
    } //populate


} // end class
