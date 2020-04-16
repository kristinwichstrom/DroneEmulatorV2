/*
My UDP Broadcast server. Everyone on this Subnet mask will receive this broadcast
 */
package sample;

import java.net.DatagramSocket;

public class BroadcastServer {

    boolean broadcast = true; // Used for telling whether to broadcast or not. Used for button inn app
    DatagramSocket socket; //Socket for sending/receiving data

}
