package sample;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String logTime;
    private String message;
    private String command;
    private String param1;
    private String param2;
    private String param3;
    private String ip;
   
    public Message(String message, String ip){

        //Object for getting the current time
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date currentTime = new Date();
        logTime = formatter.format(currentTime);
        this.ip = ip;
        this.message = message;

        //Splits the incoming message into separate parts - to be used for controlling the drone's x + y axis and colors
        String[] messageArray = message.split(" ");
        command = messageArray[0];
        System.out.println("length: " + messageArray.length);

        if (messageArray.length > 1) {
            param1=messageArray[1];
            if (messageArray.length > 2) {
                param2=messageArray[2];
                if (messageArray.length > 3) {
                    param3 = messageArray[3];
                }
            }
        }
    }

    /*
    Constructor to convert the byte[], to a String.
     */
    public Message (byte[] message, int length, InetAddress ip){
        this(new String(message, 0, length), ip.getHostAddress());
    }

    public String getMessage() {
        return message;
    }

  public String getLogTime() {
        return logTime;
    }

    public String getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }

    public String getParam3() {
        return param3;
    }


    public String getCommand() {
        return command;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "LogTime: " + logTime + ", Message: " + message + ", ip: " + ip;
    }
}
