﻿using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace NetServer
{
    class Program
    {

        static int port = 8008; // порт для приема входящих запросов
        static void Main(string[] args)
        {
            // получаем адреса для запуска сокета
            IPEndPoint ipPoint = new IPEndPoint(IPAddress.Parse("192.168.0.103"), port);
            // создаем сокет
            Socket listenSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            try
            {
                // связываем сокет с локальной точкой, по которой будем принимать данные
                listenSocket.Bind(ipPoint);

                // начинаем прослушивание
                listenSocket.Listen(10);
                Console.WriteLine("Сервер запущен. Ожидание подключений...");
                while (true)
                {
                    Socket handler = listenSocket.Accept();

                    NetworkStream nws = new NetworkStream(handler);
                    BinaryReader br = new BinaryReader(nws);
                    BinaryWriter bs = new BinaryWriter(nws);

                    ClientObject clientObject = new ClientObject(handler);

                    Console.WriteLine("Соединение с клиентом установлено ");
                    // создаем новый поток для обслуживания нового клиента
                    Thread clientThread = new Thread(new ThreadStart(clientObject.ConnectionProcess));
                    clientThread.Start();

                    // получаем сообщение
                    //StringBuilder builder = new StringBuilder();
                    //int bytes = 0; // количество полученных байтов
                    //byte[] data = new byte[256]; // буфер для получаемых данных

                    //do
                    //{
                    //    bytes = handler.Receive(data);
                    //    builder.Append(Encoding.Unicode.GetString(data, 0, bytes));
                    //}
                    //while (handler.Available > 0);



                    // отправляем ответ
                    //string message = "ваше сообщение доставлено";
                    //data = Encoding.Unicode.GetBytes(message);
                    //handler.Send(data);
                    //// закрываем сокет
                    //handler.Shutdown(SocketShutdown.Both);
                    //handler.Close();
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
        }
    }
}
