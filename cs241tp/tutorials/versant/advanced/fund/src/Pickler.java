
import com.versant.fund.*;

abstract class Pickler
{
	// variables

	// methods
		
	void pseudoConstructor (Session s) {};
	
	abstract public Handle newPersistentInstance(ClassHandle cls);

	abstract public ClassHandle defineClass(Session s);
	
	abstract public ClassHandle defineClass(Session s, String dbname);
}
