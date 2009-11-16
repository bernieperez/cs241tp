import com.versant.fund.*;
import com.versant.trans.*;

public
class Hooks
{
    public static
    void main (String[] args)
    {
        if (args.length != 1) {
            System.out.println ("Usage: java Hooks <database>");
            System.exit (-1);
        }

        String       database = args [0];
        TransSession session  = new TransSession (database);
        HookedPerson person   = new HookedPerson ();
        Object[]     objects  = { person };
    
        // A newly created persistent object will be activated.
        // prints "activate"
        session.makePersistent (person);

        // Commit causes the object to be deactivated and written.
        // prints "deactivate"
        // prints "preWrite(true)"
        // prints "postWrite(true)"
        session.commit ();

        // When the object is first touched, its state is read
        // into the cache, and the object is activated.
        // prints "preRead(true)"
        // prints "postRead(true)"
        // prints "activate"
        person.name = "John Doe";

        // The state of the object is still in the cache, so
        // again no hooks are invoked.
        person.age = 25;

        // Checkpoint commit causes the object to be written, but
        // not released from the cache.  Hence postWrite is called.
        // prints "preWrite(false)"
        // prints "postWrite(false)"
        session.checkpointCommit ();

        // Since nothing has changed, no hooks are invoked.
        session.checkpointCommit ();

        // The state of the object is still in the cache, so
        // again no hooks are invoked.
        person.age = 26;

        // By default, selecting causes all instances of the class
        // to be flushed.  The object is not released from the cache,
        // so postWrite is called.
        // prints "preWrite(false)"
        // prints "postWrite(false)"
        session.select (HookedPerson.class, null);
        
        // The state of the object is still in the cache, so
        // again no hooks are invoked.
        person.age = 27;

        // The object is flushed but not released, so it is similar
        // to checkpointCommit or select flush.
        // prints "preWrite(false)"
        // prints "postWrite(false)"
        session.groupWriteObjects (objects, 0);

        // The state of the object is still in the cache, so
        // again no hooks are invoked.
        person.age = 28;
        
        // The object was refreshed, but not activated (because it
        // was already in the cache).  Hence preRead is called.
        // prints "preRead(false)"
        // prints "postRead(false)"
        session.refreshObjects (objects, database, Constants.RLOCK);

        // The state of the object is still in the cache, so
        // again no hooks are invoked.
        person.age = 29;

        // The object us directly released from the cache, so it
        // is deactivated without writing any changes.
        // prints "deactivate"
        session.releaseObjects (objects);

        // The object was already released, so no hooks are invoked.
        session.releaseObjects (objects);

        // The object had been released, so touching it causes it
        // to be read into the cache and activated.
        // prints "preRead(true)"
        // prints "postRead(true)"
        // prints "activate"
        person.age = 30;

        // The rollback causes the object to be released from the
        // cache and deactivated.
        // prints "deactivate"
        session.rollback ();

        // The object was already released, so no hooks are invoked.
        session.rollback ();

        // The object had been released, so touching it causes it
        // to be read into the cache and activated.
        // prints "preRead(true)"
        // prints "postRead(true)"
        // prints "activate"
        person.age = 31;

        // The commit causes the object to be flushed and released
        // from the cache.
        // prints "deactivate"
        // prints "preWrite(true)"
        // prints "postWrite(true)"
        session.commit ();

        // The object had been released, so touching it causes it
        // to be read into the cache and activated.
        // prints "preRead(true)"
        // prints "postRead(true)"
        // prints "activate"
        person.age = 32;

        // The delete operation causes the object to be released
        // from the cache.
        // prints "deactivate"
        session.deleteObject (person);

        // The object had already been released, so the rollback
        // doesn't invoke any hooks.
        session.rollback ();

        session.endSession ();
    }
}

class HookedPerson
{
    String name;
    int    age;

    public void activate   () { System.out.println ("activate"  ); }
    public void deactivate () { System.out.println ("deactivate"); }
    public void preRead    (boolean activate)
       { System.out.println ("preRead("   + activate   + ")"); }
    public void postRead   (boolean activate)
       { System.out.println ("postRead("  + activate   + ")"); }
    public void preWrite   (boolean deactivate)
       { System.out.println ("preWrite("  + deactivate + ")"); }
    public void postWrite  (boolean deactivate)
       { System.out.println ("postWrite(" + deactivate + ")"); }
    public void vDelete  ()
       { System.out.println ("vDelete"); }
}
