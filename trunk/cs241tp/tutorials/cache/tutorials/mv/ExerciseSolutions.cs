using System;
using System.Collections.Generic;
using System.Text;
using NUnit.Framework;
using InterSystems.Data.CacheTypes;
using InterSystems.Data.CacheClient;

namespace Exercises
{
    [TestFixture]
    public class ExerciseSolutions
    {

        CacheConnection cnCache;
        [SetUp]
        public void Connect()
        {

            string cacheString = "Server=localhost;" +
                                  "Port=1972;" +
                                  "Namespace=MYACCOUNT;" +
                                  "Password=SYS;" +
                                  "User ID=_system;";

            cnCache = new CacheConnection(cacheString);

        }
        [Test]
        public void AddPhone()
        {
            String number = "999-888-7777";
            cnCache.Open();
            String sql = "SELECT ItemId FROM MVFILE.PERSON WHERE NAME %Startswith 'SMITH'";
            CacheCommand command = new CacheCommand(sql, cnCache);
            CacheDataReader reader = command.ExecuteReader();

                while (reader.Read())
                {
                    String id = (String)reader["ItemId"];
                    Console.WriteLine("ItemId {0}", id);
                    MVFILE.PERSON person = MVFILE.PERSON.OpenId(cnCache, id);
                    person.PHONE.Add(number);
                    CacheStatus status = person.Save();
                    Console.WriteLine("Status {0}", status.Message);

                }


                cnCache.Close();

        }
        [Test]
        public void AddPerson()
        {
            cnCache.Open();
            String sql = "INSERT INTO MVFILE.PERSON (ItemId,NAME, HAIR, AGE, PHONE) VALUES ('15','WILLIAMS,ROGER', 'BRUNETTE','44','911-111-2222')";
            CacheCommand command = new CacheCommand(sql, cnCache);
            int rows = command.ExecuteNonQuery();
            Console.WriteLine("Updated rows: {0}",rows);
            cnCache.Close();

        }
        [Test]
        public void RunRoutine()
        {
            cnCache.Open();
           String search =  MVExercise.Update.SetValue(cnCache, "3", "HAIR", "1", "RED");
           cnCache.Close();

           Console.WriteLine("Search {0}", search);

        }

    }
}
