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
        selectedTool = new DrawDrone(Color.LIGHTSALMON,200,200);
        selectedTool.drawCircle(graphicsContext);
    }
//needs to be fixed
    public void selectSquare(ActionEvent actionEvent) {
        System.out.println("Square button");
        selectedTool = new DrawDrone(Color.LAVENDER,200,200);
        selectedTool.drawRectangle(graphicsContext);


    }

    public void handleMessage(Message message){
        if (inputLogTable != null){
            inputLogTable.getItems().add(0, message);
        }
    }
}