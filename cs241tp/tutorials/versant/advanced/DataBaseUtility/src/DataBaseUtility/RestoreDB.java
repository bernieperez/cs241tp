
/*Restore the database*/
package DataBaseUtility;

import com.versant.fund.*;
import com.versant.trans.*;
import com.versant.util.*;
import com.versant.util.backup.*;
import com.versant.util.backup.VBackupManager;
import com.versant.util.backup.RestoreListener;
import com.versant.util.backup.RestoreVisitor;
import com.versant.util.backup.Options;
import com.versant.event.*;
import java.util.*;

public class RestoreDB
{

	public static void main(String[] args)
	{
		if (args.length < 4)
		{
        		System.out.println ("Usage: RestoreDB <db> <device> <level> <dest db>");
        		System.exit(-1);
		}
		/* The name of the  database,device and level are accepted from the console  */
		 String database=args[0];
		 String device=args[1];
		 String level=args[2];
		 String rename = args[3];

		/*create the object of VBackuoManager with the database */

		VBackupManager vbm=new VBackupManager(database);

		/*create the object of Options */

		Options opt=new Options();

		/*set the device for backup, level of backup  capacity and rename  database*/

		opt.setDevice(device);
		opt.setLevel(level);
		opt.setCapacity("10M");
		opt.setRename(rename);

		/* implements the RestoreListener */

		RestoreListener listener=new RestoreListener()
		{

			/*The variousb methods for RestoreListener */

			public boolean anotherLevelQuery()
			{
				return false;
			}

			public boolean backupNotLatest()
			{
				return false;
			}

			public boolean copyLogicalLog()
			{
				return false;
			}

			public boolean couldNotOpenDevice(RestoreVisitor rv)
			{
				System.out.println("could not open and copy to a filenamed db");
				return false;
			}

			public boolean dbModifiedAfterRestore()
			{
				return false;
			}

			public boolean getPathOfLogicalLog(RestoreVisitor rv)
			{
				return false;
			}

			public boolean getPathToCopyLogicalLog(RestoreVisitor rv)
			{
				return false;
			}

			public boolean mountNextDevice(RestoreVisitor rv)
			{
				return false;
			}

			public boolean logicalLogCopyAlreadyExists()
			{
				return false;
			}

			public boolean restoreLevel2()
			{
				return false;
			}

			public boolean mountNextDeviceForNextLevel(RestoreVisitor rv)
			{
				return false;
			}

			public boolean mountNextLogDevice(RestoreVisitor rv)
			{
				return false;
			}
			public boolean restorePreviousLevel()
			{
				return false;
			}
			public boolean wrongLogArchiveSequenceNumber(RestoreVisitor visitor)
			{
				return false;
			}
			public boolean wrongLogArchive(RestoreVisitor visitor)
			{
				return false;
			}
			public boolean wrongBackupDevice(RestoreVisitor visitor)
			{
				return false;
			}
			public boolean wrongFileBlock(RestoreVisitor visitor)
			{
				return false;
			}
			public boolean wrongByteOrdering(RestoreVisitor visitor)
			{
				return false;
			}
			public boolean wrongFileVersion(RestoreVisitor visitor)
			{
				return false;
			}
			public boolean wrongFileLabel(RestoreVisitor visitor)

			{
				return false;
			}
			public boolean wrongFileVolume(RestoreVisitor visitor)
			{
				return false;
			}
			public boolean wrongFileFormat(RestoreVisitor visitor)
			{
				return false;
			}
			public boolean mountNextDeviceForNextLevel(int level,RestoreVisitor visitor)

			{
				return false;
			}


		};

		System.out.println("start Restore");

		/*Restore the specified database */

		vbm.restore(listener,opt);

		System.out.println("Restore finish");
	}
}
