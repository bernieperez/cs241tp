using System;
using System.Collections.Generic;
using System.Text;
using NUnit.Framework;
using InterSystems.Data.CacheTypes;
using InterSystems.Data.CacheClient;

namespace ObjectAccess
{
    [TestFixture]
    public class ObjectAccess
    {

        CacheConnection cnCache;
        [SetUp]
        public void Connect()
        {

            string cacheString = "Server=localhost;" +
                                  "Port=1972;" +
                                  "Log File=c:\\MVTutorial.log;"+
                                  "Namespace=MYACCOUNT;" +
                                  "Password=SYS;" +
                                  "User ID=_system;";

            cnCache = new CacheConnection(cacheString);

        }
        [Test]
        public void RetrieveObject()
        {
            cnCache.Open();
            
            
            MVFILE.PERSON person = MVFILE.PERSON.OpenId(cnCache, "1");

            Assert.AreNotEqual(null, person);

            Console.WriteLine("Name: {0} Age: {1} Hair: {2}", person.NAME, person.AGE, person.HAIR);

            CacheListOfStrings phones = person.PHONE;

            foreach (String pn in phones)
                Console.WriteLine (pn);

            cnCache.Close();

        }

        [Test]
        public void InsertObject()
        {
            cnCache.Open();

            MVFILE.PERSON person = new MVFILE.PERSON(cnCache);

            person.NAME = "Smith,John";
            person.HAIR = "Blond";
            person.AGE = "42";
            person.ItemId = "10";
         
            CacheListOfStrings phones = person.PHONE;
            phones.Add("111-222-3333");
            phones.Add("444-555-6666");
            phones.Add("777-888-9999");

            CacheStatus status = person.Save();
            Assert.AreEqual(true,status.IsOK);
           
            cnCache.Close();

        }

       [Test]
        public void RunRoutine()
        {
            cnCache.Open();
            String Name = MVExample.Search.GetValue(cnCache, "15", "NAME", "1");
            Assert.AreNotEqual(null, Name);
            Console.WriteLine("NAME: {0}", Name);


        }
    }
}
