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
    Sets our necessary FXML
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

    GraphicsContext graphicsContext;
    private UdpReceiver udpReceiver;
    private double currentX = 200;
    private double currentY = 200;
    private double speed = 30;
    int color1 = 0;
    int color2 = 0;
    int color3 = 0;
    String shape = "circle";

    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public Controller() {
        udpReceiver = new UdpReceiver(this);
        new Thread(udpReceiver).start();
    }

    public void toggleBtnDrone(ActionEvent actionEvent) {
        System.out.println("Toggle Drone button");
        if (udpReceiver.isReceiveMessages()) {
            /*
            Stopping thread
             */
            udpReceiver.setReceiveMessages(false);
            toggleBtnDrone.setText("OFF");
        } else {
          /*
            Starting thread
             */
            udpReceiver.setReceiveMessages(true);
            new Thread(udpReceiver).start();
            toggleBtnDrone.setText("ON");
        }

    }

    public void clearTable() {
        inputLogTable.getItems().clear();
    }


    public void handleMessage(Message message) {
        if (inputLogTable != null) {
            inputLogTable.getItems().add(0, message);
        }

        String command = message.getCommand();

        /*
        The switch creates a case for each command (message)
         */
        switch (command) {

            case "init":
                String x = message.getParam1();
                String y = message.getParam2();
                currentX = Double.parseDouble(x);
                currentY = Double.parseDouble(y);

                updateCanvas();
                break;

            case "moveup":
                //clearCanvas();
                if (currentY <= 0) { //Prevents shape to move outside canvas
                    break;
                }
                currentY -= speed;
                updateCanvas();
                break;

            case "movedown":
                //clearCanvas();
                if (currentY >= 400) {
                    break;
                }
                currentY += speed;
                updateCanvas();
                break;

            case "moveleft":
                //clearCanvas();
                if (currentX <= 0) {
                    break;
                }
                currentX -= speed;
                updateCanvas();
                break;

            case "moveright":
                //clearCanvas();
                if (currentX >= 400) {
                    break;
                }
                currentX += speed;
                updateCanvas();
                break;

            case "stop":
                //clearCanvas();
                break;

            case "color":
                color1 = Integer.parseInt(message.getParam1());
                color2 = Integer.parseInt(message.getParam2());
                color3 = Integer.parseInt(message.getParam3());
                // System.out.println(message.getParam1() + " " + message.getParam2() + " " + message.getParam3());
                graphicsContext.setFill(Color.rgb(color1, color2, color3));
                updateCanvas();
        }
    }

    /*
    Method for updating canvas (shape) for each move
     */
    private void updateCanvas() {
        if (graphicsContext != null) {
            graphicsContext.clearRect(0, 0, 1000, 1000);
            if (shape.equals("circle")) { // If user clicks circle button
                graphicsContext.fillOval(currentX, currentY, 50, 50);
            } else {
                graphicsContext.fillRect(currentX, currentY, 50, 50);
            }

        }
    }

    /*
    When Circle button is selected
     */
    public void selectCircle(ActionEvent actionEvent) {
        shape = "circle";
        updateCanvas();
    }

    /*
    When Square button is selected
     */
    public void selectSquare(ActionEvent actionEvent) {
        shape = "rectangle";
        updateCanvas();
    }
}