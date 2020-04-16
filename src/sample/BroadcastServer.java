/*
My UDP Broadcast server. The class implements the Runnable interface. Everyone on this Subnet mask will receive this broadcast.
 */
package sample;

import java.io.IOException;
import java.net.*;

public class BroadcastServer implements Runnable {

    /*
    Initializes the needed DatagramSocket and variables
     */

    private boolean broadcast = true; // Used for telling whether to broadcast or not. Used for button inn app
    private DatagramSocket serverSocket; //Socket for sending/receiving data
    private int portBroadcast = 7007; //Listening to port 7007
    String message = "Drone Server is listening to port 7000 and answers on port 7007";

    public boolean isBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    public void broadcastLoop() throws InterruptedException {
        Thread.sleep(3000);
        if (broadcast == true)
            broadcastMessage(message);

    }
    /*
     Method is used for broadcasting message on network
     */

    private void broadcastMessage(String message) {

        try {
            serverSocket = new DatagramSocket();
            serverSocket.setBroadcast(true);
            byte[] buffer = message.getBytes(); // Converts the message String into a byte array
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"), portBroadcast);
            serverSocket.send(packet); //Send packet
            serverSocket.close(); // Closes my socket

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     Method starts thread
     */
    @Override
    public void run() {
        try {
            broadcastLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
