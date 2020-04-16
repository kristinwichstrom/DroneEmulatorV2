/*
Class holds methods used for Drone Controller data table in GUI
 */
package sample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Messages {
    private String time;
    private String message;
    private String ip;
    private int length;
    private int port;

    public Messages (String message, String ip, int length, int port ) throws ParseException {
        this.message=message;
        this.ip=ip;
        this.length=length;
        this.port = port;

        SimpleDateFormat pattern;
        pattern = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date time = new Date;
        this.time = pattern.format(time);
    }
    public int getPort() {
        return port;
    }
    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getIp() {
        return ip;
    }

    public int getLength() {
        return length;
    }
}
