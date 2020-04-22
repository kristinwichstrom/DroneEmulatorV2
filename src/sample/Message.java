package sample;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String logTime;
    private String message;

    private String command;
    private String param1;
    private String param2;
    private String colorR;
    private String colorG;
    private String colorB;
   
    public Message(String message){

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date currentTime = new Date();
        logTime = formatter.format(currentTime);

        this.message = message;

        //Parses string with split
        String[] messageArray = message.split(" ");
        command = messageArray[0];
        System.out.println("length: " + messageArray.length);

        if (messageArray.length > 1) {
            param1=messageArray[1];
            if (messageArray.length > 2) {
                param2=messageArray[2];
            }
        }
    }

    /*
    Constructor to convert the byte[], to a String.
     */
    public Message (byte[] message, int length){
        this(new String(message, 0, length));
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

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "LogTime: " + logTime + " Message: " + message;
    }
}
