package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Controller {

    /*
    our FXML elements we need to grab.
     */

    @FXML
    TableView<Message> inputLogTable;
    @FXML
    ToggleButton toggleBtnDrone;
    @FXML
    Canvas canvas;
    @FXML
    Button btnCircle;
    @FXML
    Button btnSquare;
    @FXML
    Label droneLabel;


    DrawShapes selectedTool;
    GraphicsContext graphicsContext;
    private UdpReceiver udpReceiver;
    private double currentX;
    private double currentY;
    private double speed = 60;

    public void initialize ()
    {
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public Controller (){
        udpReceiver = new UdpReceiver(this);
        new Thread(udpReceiver).start();
    }

    public void toggleBtnDrone(ActionEvent actionEvent) {
        System.out.println("Toggle Drone button");
        if (udpReceiver.isReceiveMessages()){
            /*
            Kill udpReceiver thread
             */
            udpReceiver.setReceiveMessages(false);
            toggleBtnDrone.setText("OFF");
        } else {
            /*
            Start udpReceiver thread
             */
            udpReceiver.setReceiveMessages(true);
            new Thread(udpReceiver).start();
            toggleBtnDrone.setText("ON");
        }

    }

    public void clearTable () {
        inputLogTable.getItems().clear();
    }


    public void handleMessage(Message message) {
        if (inputLogTable != null) {
            inputLogTable.getItems().add(0, message);
        }

        String command = message.getCommand();
        switch (command){

            case "init":
                String x = message.getParam1();
                String y = message.getParam2();
                currentX = Double.parseDouble(x);
                currentY = Double.parseDouble(y);
                drawCircle();
                break;

            case "moveup":
                clearCircle();
                currentY -= speed;
                drawCircle();
                break;

            case "movedown":
                clearCircle();
                currentY += speed;
                drawCircle();
                break;

            case "moveleft":
                clearCircle();
                currentX -= speed;
                drawCircle();
                break;

            case "moveright":
                clearCircle();
                currentX += speed;
                drawCircle();
                break;

            case "stop":
                clearCircle();
                break;

            case "color 255":

        }
    }
        private void drawCircle () {
        if (graphicsContext != null) {
            graphicsContext.setFill(Color.LIGHTSALMON);
            graphicsContext.fillOval(currentX-30, currentY-30, 50, 50);
        }
    }
    private void clearCircle () {
        if (graphicsContext !=null) {
            graphicsContext.setFill(Color.LIGHTSALMON);
            graphicsContext.clearRect(currentX-30, currentY-30, 50, 50);
        }
    }

    public void selectCircle(ActionEvent actionEvent) {
      //  System.out.println("Circle button");
       // selectedTool = new DrawShapes(Color.LIGHTSALMON,200,200);
        //selectedTool.drawCircle(graphicsContext);
    }
    public void selectSquare(ActionEvent actionEvent) {
       // System.out.println("Square button");
        //selectedTool = new DrawShapes(Color.LAVENDER,200,200);
        //selectedTool.drawRectangle(graphicsContext);
    }

}