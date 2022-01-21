package com.example.control.NET;

import android.os.AsyncTask;
import android.util.Log;

import androidx.databinding.adapters.Converters;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutionException;

public class Boot {
    //InetSocketAddress socketAddress;
    //String macStr;
    public Net net;
    public final static int Shutdown = 0;
    public final static int Sleep = 1;
    public final static int Restart = 2;
    public Boot(Net _net){
        net = _net;
    }
    public static Integer CheckConnection(){
        Socket socket = new Socket();
        try {
            //socket.connect(Net.socketAddress,2000);
            socket.connect(new InetSocketAddress(Net.IpAddress,Net.Port),2000);
            socket.close();
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }
    public static String PCBoot(){
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            byte[] bytesMac = new byte[6];
            byte[] bytesSync = new byte[6];
            String[] hex = Net.MacAddress.split("(\\:|\\-)");
            byte[] dat = new byte[bytesSync.length + bytesMac.length * 16];
            if (hex.length != 6) {
                return "Invalid MAC address";
            }
            try {
                for (int i = 0; i < 6; i++) {
                    bytesMac[i] = (byte) Integer.parseInt(hex[i], 16);
                    bytesSync[i] = (byte) 0xff;
                }
            }
            catch (NumberFormatException e){ return "Invalid MAC address digit"; }
            System.arraycopy(bytesSync,0,dat,0,bytesSync.length);
            for (int i = 6; i <= 96; i+=6) {
                System.arraycopy(bytesMac,0,dat,i,bytesMac.length);
            }
            DatagramPacket datagramPacket = new DatagramPacket(dat,dat.length,new InetSocketAddress(Net.socketAddress.getHostName(),9));
            try {
                datagramSocket.send(datagramPacket);
                return "Boot";
            } catch (IOException e) {
                Log.d("Exception", "BootPC: error "+e );
            }

        } catch (SocketException e) {

        }
        return "Error";
    }
     public static String Command(Integer mod){
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(Net.IpAddress,Net.Port),5000);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            DataOutputStream s = new DataOutputStream(socket.getOutputStream());
            writer.println(mod.toString());
            writer.flush();
            writer.close();
            socket.close();
            switch (mod){
                case Sleep:return "Sleep";
                case  Restart: return "Restart";
                case Shutdown: return "Shutdown";
            }

        } catch (IOException e) {

        }
         return "Error";
    }


    public void ControlCommand(Integer command){
        new ControlCommand().execute(command);
    }
    public String BootPC(){
        try {
            return new BootPC().execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }
    public Integer ConnectionCheck(){
        try {
            return new ConnectCheck().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 0;
    }
    class ConnectCheck extends AsyncTask<Void, Void, Integer> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(Void... params) {
            return CheckConnection();
        }
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

        }
    }

    class BootPC extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... params) {
            return PCBoot();
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
    class ControlCommand extends AsyncTask<Integer, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Integer... params) {
            Command(params[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}

