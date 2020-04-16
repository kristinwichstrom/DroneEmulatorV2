package sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpConnector {
    public DatagramSocket socket;
    private int port = 7000;
    private int udpPortDrone = 7007;
    private Controller controller;
    boolean receiveMsgs = true;

    public UdpConnector(Controller controller) {
        this.controller=controller;
    }
    public void setupSocket () {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    public void receiveMessage () {
        byte [] buffer = new byte[256]; //256 is the largest amount of information that goes into one UDP packet
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
        try {
            socket.receive(packet); //blocking statement
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}

