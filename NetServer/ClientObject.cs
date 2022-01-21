using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

namespace NetServer
{
    class ClientObject
    {
        
        public Socket client;
        const int Shutdown = 0;
        const int Sleep = 1;
        const int Restart = 2;

        [DllImport("Powrprof.dll", CharSet = CharSet.Auto, ExactSpelling = true)]
        public static extern bool SetSuspendState(bool hiberate, bool forceCritical, bool disableWakeEvent);
        public ClientObject(Socket Client)
        {
            client = Client;
        }
        public void ConnectionProcess()
        {
            Console.WriteLine();
            NetworkStream nws = new NetworkStream(client);
            BinaryReader br = new BinaryReader(nws);
            BinaryWriter bs = new BinaryWriter(nws);
           
            
            try
            {
                string mod;
                  using (StreamReader str = new StreamReader(nws))
                  {
                    mod = str.ReadToEnd();
                  }
                switch (Convert.ToInt32(mod))
                {
                    case Shutdown:
                        {
                            var psi = new ProcessStartInfo("shutdown", "/s /t 0");
                            psi.CreateNoWindow = true;
                            psi.UseShellExecute = false;
                            Process.Start(psi);
                        }
                        break;
                    case Sleep:
                        { SetSuspendState(false,true,true); }
                        break;
                        case Restart:
                        { System.Diagnostics.Process.Start("shutdown.exe", "-r -t 0"); }
                        break;
                }

                /*while(true)
                {

                }*/

            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            finally
            {
                if (client != null)
                {
                    client.Close();
                }

            }
        }

        //public void LogIn()
        //{
        //    NetworkStream nws = new NetworkStream(client);
        //    BinaryReader br = new BinaryReader(nws);
        //    BinaryWriter bs = new BinaryWriter(nws);
        //    string login = br.ReadString();
        //    string password = br.ReadString();
        //    Console.WriteLine($"Логин {login}, пароль {password}");

        //    string connectionString = "mongodb://localhost:27017";
        //    MongoClient BD = new MongoClient(connectionString);
        //    var database = BD.GetDatabase("Dickscord");
        //    var collection = database.GetCollection<AccountD>("Accounts");
        //    var accbuilder = Builders<AccountD>.Filter;
        //    var filter = accbuilder.Eq("Login", login) & accbuilder.Eq("Password", password);
        //    var accounts = collection.Find(filter).ToList();
        //    foreach (var acc in accounts)
        //    {
        //        Console.WriteLine($"Сервер нашел такого пользователя: {acc.Login}");

        //    }
        //}
        //public void DataLoad()
        //{
        //    NetworkStream nws = new NetworkStream(client);
        //    BinaryReader br = new BinaryReader(nws);
        //    BinaryWriter bs = new BinaryWriter(nws);

        //    string s1 = br.ReadString();
        //    string s2 = br.ReadString();


        //    string connectionString = "mongodb://localhost:27017";
        //    MongoClient BD = new MongoClient(connectionString);
        //    var database = BD.GetDatabase("оДНОгруппники");
        //    var collection = database.GetCollection<Account>("accounts");
        //    var accbuilder = Builders<Account>.Filter;
        //    var filter = accbuilder.Eq("_id", s1) & accbuilder.Eq("password", s2);
        //    var accounts = collection.Find(filter).ToList();
        //    foreach (var acc in accounts)
        //    {
        //        Console.WriteLine($"Сервер нашел такого пользователя: {acc.name}");
        //        string message = $"Пользователь найден: {acc.name}";
        //        bs.Write($"Пользователь найден: {acc.name}");
        //        bs.Write(acc.age);
        //        bs.Write(acc.info);
        //    }
        //}
    }
}
