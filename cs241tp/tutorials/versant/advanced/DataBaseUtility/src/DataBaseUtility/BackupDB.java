/*Program which allows us to create a Backup for a database*/
package DataBaseUtility;

import com.versant.fund.*;
import com.versant.trans.*;
import com.versant.util.*;
import com.versant.util.backup.*;
import com.versant.util.backup.VBackupManager;
import com.versant.util.backup.BackupListener;
import com.versant.util.backup.BackupVisitor;
import com.versant.util.backup.Options;
import com.versant.event.*;
import java.util.*;

/*Backup of database*/

public class BackupDB
{

	public static void main(String[] args)
	{

		if (args.length < 3)
		{
        		System.out.println ("Usage: BackupDB <db> <device> <level>");
        		System.exit(-1);
		}

		/* The name of the  database,device and level are accepted from the console  */

		 String database=args[0];
		 String device=args[1];
		 String level=args[2];

		/*create the object of VBackuoManager with the database */

		VBackupManager vbm=new VBackupManager(database);

		/*create the object of Options */

		Options opt=new Options();

		/*set the device for backup, level of backup and capacity */

		opt.setDevice(device);
		opt.setLevel(level);
		opt.setCapacity("10M");
		opt.setComment(Options.COMMENT);

		/* implements the BackupListener */

		BackupListener listener=new BackupListener()
		{
			public boolean couldNotOpenDevice(BackupVisitor bv)
			{
				System.out.println("could not open and copy to a filenamed db");
				bv.setFileName("db");
				return true;
			}
			public boolean mountNextDevice(BackupVisitor bv)
			{
				System.out.println("could not mount and copy to a filenamed db");
				bv.setFileName("back");
				return true;
			}
		};

		System.out.println("start backup");

		/* Creating the backup of the specified database */

		vbm.backup(listener,opt);

		System.out.println("backup finish");
	}
}
