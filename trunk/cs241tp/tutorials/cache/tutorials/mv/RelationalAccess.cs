using System;
using System.Collections.Generic;
using System.Text;
using System.Data;
using NUnit.Framework;
using InterSystems.Data.CacheClient;
using InterSystems.Data.CacheTypes;


namespace RelationalAccess
{
    [TestFixture]
    public class RelationalAccess
    {
        CacheConnection cnCache;
        
       [SetUp]
        public void Connect()
        {
            string cacheString =
           "Server=localhost;Port=1972; Log File=c:\\MVTutorial.log;Namespace=MYACCOUNT;" +
            "Password=SYS;User ID=_system;";

            cnCache = new CacheConnection(cacheString);

        }

        [Test]
        public void Query()
        {

            //Open the connection
            cnCache.Open();

            //Create and execute the query
            string sql = "SELECT * FROM MVFILE.PERSON"; 
            CacheCommand command = new CacheCommand(sql, cnCache);
            CacheDataReader reader = command.ExecuteReader();
            Assert.AreNotEqual(null, reader);
            Assert.AreNotEqual(false, reader.HasRows);
            //Display the results
            while (reader.Read())
            {
                for (int i = 0; i < reader.FieldCount; i++)
                {
                    Console.Write(reader[i] + " " );

                }

                Console.WriteLine();

            }

            //Close the connection
            cnCache.Close();


        }


        [Test]
        public void Update()
        {

            //Open the connection
            cnCache.Open();

            //Create and execute the update
            String sql = "UPDATE MVFILE.PERSON (AGE,PHONE) VALUES" +
               " ('41','111-111-1111,222-222-2222') WHERE NAME='IDLE,JIM'";
            
            CacheCommand command = new CacheCommand(sql, cnCache);
            int rows = command.ExecuteNonQuery();
            Assert.AreNotEqual(0, rows);
           
            //Display the number of affected rows
            Console.WriteLine("rows {0}", rows);
            
            //Close the connection
            cnCache.Close();


        }

        [Test]
        public void DataSet()
        {

            string select = "SELECT * FROM MVFILE.PERSON";
            CacheDataAdapter dataAdapter = new CacheDataAdapter(select, cnCache);

            DataSet dataSet = new DataSet();

            dataAdapter.Fill(dataSet,"PERSON");
            Assert.AreNotEqual(0, dataSet.Tables.Count);

            foreach (DataRow dr in dataSet.Tables["PERSON"].Rows)
            {
                Console.WriteLine("ID: {0} NAME: {1} AGE: {2} HAIR: {3} PHONES: {4}", dr["ID"],dr["NAME"], 
                    dr["AGE"], dr["HAIR"], dr["PHONE"]);

            }

        }


    }
}
