package com.example.control.NET;

import com.example.control.ui.home.BootViewModel;

import java.net.InetSocketAddress;
import java.net.Socket;

public class Net {
    public Socket socket;
    public static InetSocketAddress socketAddress;
    public static String MacAddress;
    public static String IpAddress;
    public static Integer Port;
    public Boot boot;
    public Net(String mac_address,String ip_address,String port){
            MacAddress = mac_address;
            IpAddress = ip_address;
            try{Port = Integer.parseInt(port);}
            catch (Exception e){ Port =0;}
            socket = new Socket();
            socketAddress = new InetSocketAddress(IpAddress, Port);
            boot = new Boot(this);

    }

}
