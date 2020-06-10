package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

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
    @FXML
    Label droneOnOffLabel;
    @FXML 
    ToggleButton toggleBtnBroadcast;
    @FXML
    private ListView<String> listViewXY;
    @FXML
    Button clearTableListView;


    GraphicsContext graphicsContext;
    private UdpReceiver udpReceiver;
    private double currentX = 200;
    private double currentY = 200;
    private double speed = 20; // For each time the canvas is updated the shape moves 20 pixels
    int color1 = 0; // Stores the first value color value (in RGB)
    int color2 = 0; // Second RGB value
    int color3 = 0; // Third RGB value
    String shape = "";

    public void initialize() {

        graphicsContext = canvas.getGraphicsContext2D();
        new Thread(udpReceiver).start();
    }

    public Controller() {
        udpReceiver = new UdpReceiver(this);

    }
    public void toggleBtnDrone(ActionEvent actionEvent) {
        System.out.println("Toggle Drone button");
        if (udpReceiver.isReceiveMessages()) {
            /*
            Stopping udpReceiver thread
             */
            udpReceiver.setReceiveMessages(false);
            toggleBtnDrone.setText("OFF");
            droneOnOffLabel.setText("DRONE OFF");
        } else {
          /*
            Starting udpReceiver thread
             */
            udpReceiver.setReceiveMessages(true);
            new Thread(udpReceiver).start();
            toggleBtnDrone.setText("ON");
            droneOnOffLabel.setText("DRONE ON");
        }
    }

    public void clearTable() {
        inputLogTable.getItems().clear();
    }

    public void clearTableList(ActionEvent actionEvent) {

        listViewXY.getItems().clear();
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

            // Sets the drone in center (starts in 200, 200)
            case "stop":
                break;

            case "init":
                String x = message.getParam1();
                String y = message.getParam2();
                currentX = Double.parseDouble(x);
                currentY = Double.parseDouble(y);
                Platform.runLater(() -> updateCanvas());
                break;


            case "moveup":
                if (currentY <= 20) { //Prevents drone from moving outside canvas
                    break;
                }
                currentY -= speed;
                Platform.runLater(() -> updateCanvas());
                break;

            case "movedown":
                if (currentY >= 370) {
                    break;
                }
                currentY += speed;
                Platform.runLater(() -> updateCanvas());
                break;

            case "moveleft":
                if (currentX <= 20) {
                    break;
                }
                currentX -= speed;
                Platform.runLater(() -> updateCanvas());

                break;

            case "moveright":
                if (currentX >= 370) {
                    break;
                }
                currentX += speed;
                Platform.runLater(() -> updateCanvas());
                break;


            case "color":
                color1 = Integer.parseInt(message.getParam1());
                color2 = Integer.parseInt(message.getParam2());
                color3 = Integer.parseInt(message.getParam3());
                // System.out.println(message.getParam1() + " " + message.getParam2() + " " + message.getParam3());
                graphicsContext.setFill(Color.rgb(color1, color2, color3));
                Platform.runLater(() -> updateCanvas());
        }
    }

    /*
     Method for updating canvas (shape), enabling shape to move
      */
    private void updateCanvas() {

        if (graphicsContext != null) {
            graphicsContext.clearRect(0, 0, 1000, 1000);
            if (shape.equals("circle")) { // If user clicks circle button
                graphicsContext.fillOval(currentX, currentY, 60, 60);
            } else {
                graphicsContext.fillRect(currentX, currentY, 60, 60);
            }
            if (listViewXY!= null) {
                listViewXY.getItems().add("X: " + currentX + "  Y:" + currentY);
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

    public void receivePacket(Message message) {
    }

    public void toggleBtnBroadcast(ActionEvent actionEvent) {
    }
}