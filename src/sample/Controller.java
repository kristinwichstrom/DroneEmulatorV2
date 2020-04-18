package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    DrawDrone selectedTool;
    GraphicsContext graphicsContext;
    Color selectedColor = Color.BLACK;

    private UdpReceiver udpReceiver;

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

    public void toggleBtnBroadcast(ActionEvent actionEvent) {
        System.out.println("Toggle Broadcast button");
    }

    public void clearTable () {
        inputLogTable.getItems().clear();
    }

    public void selectCircle(ActionEvent actionEvent) {
        System.out.println("Circle button");
        // Initialize circle
        //Change color of circle
        // Move circle around
    }

    public void selectSquare(ActionEvent actionEvent) {
        //selectedTool = new DrawDrone();
        System.out.println("Circle square");
        selectedTool.setColor(selectedColor);
        selectedTool.drawRectangle(graphicsContext);
        selectedTool.setX(200);
        selectedTool.setY(200);
        selectedTool.setWidth(10);
        selectedTool.setHeight(10);
    }

    public void handleMessage(Message message){
        if (inputLogTable != null){
            inputLogTable.getItems().add(0, message);
        }
    }
}