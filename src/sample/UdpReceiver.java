package sample;

import java.io.IOException;
import java.net.*;

public class UdpReceiver implements Runnable {
    private int inPort = 8000; //The port Drone Emulator listens to
    private int outPort = 7007; // Sends message back to this port
    private DatagramSocket receiveSocket;
    private boolean receiveMessages = true;
    private Controller messageHandler;

    public UdpReceiver(Controller controller) {
        this.messageHandler = controller;
        setupSocket();
    }

    private void setupSocket() {
        try {

            receiveSocket = new DatagramSocket(inPort);

        } catch (SocketException e) {
            System.out.println("IOEXCEPTION: Could not send to: " + inPort);
            e.printStackTrace();
        }
    }

    public void replyMessage() {
        do {
            Message msg = receivePacket();
            sendMessage("Okay: " + msg.getMessage());
        } while (receiveMessages);
    }


    public void sendMessage(String string) {
        try {
            sendMessage(string.getBytes(), InetAddress.getByName("255.255.255.255"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    //NY
    public void sendMessage(byte[] bytes, InetAddress Name) {
        DatagramPacket packet = null;
        try {
            packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("255.255.255.255"), outPort);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            receiveSocket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Message receivePacket() {
        byte[] inBuf = new byte[256]; // 256 = Largest amount of bytes in one packet
        DatagramPacket packet = new DatagramPacket(inBuf, inBuf.length);
        Message message = null;
        try {

            receiveSocket.receive(packet);
            message = new Message(packet.getData(), packet.getLength(), packet.getAddress());
            System.out.println("received: " + message);
            messageHandler.handleMessage(message);
            packet.getSocketAddress();

            if (receiveMessages) messageHandler.receivePacket(message);
            return message;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        replyMessage();

    }
    public boolean isReceiveMessages() {
        return receiveMessages;
    }

    public void setReceiveMessages(boolean receiveMessages) {
        this.receiveMessages = receiveMessages;
    }
}