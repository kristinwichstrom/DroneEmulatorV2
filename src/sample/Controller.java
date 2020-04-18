package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;

public class Controller {

    @FXML
    TableView<Messages> table;
    @FXML
    ToggleButton toggleBtnDrone;
    @FXML
    ToggleButton toggleBtnBroadcast;

    private UdpConnector udpConnector;
    private BroadcastServer broadcastServer;

    //Method creates an instance of the BroadcastServer class and starts a thread for the clas

    public void toggleBtnDrone(ActionEvent actionEvent) {
        System.out.println("toggle Drone button");
        if (udpConnector.isReceiveMessages()){

            udpConnector.setReceiveMessages(false);
            toggleBtnDrone.setText("OFF");
        } else
            startUdpConnection();
        toggleBtnDrone.setText("ON");
    }

    public void toggleBtnBroadcast(ActionEvent actionEvent) {
        System.out.println("Toggle Broadcast button");
        if
    }

    public void clearTable() {
        System.out.println("Clear table is pressed");
    }

    public void pressCircle() {
        System.out.println("Circle is pressed");
    }
    public void handleMessages () {
        //Updating table
    }
    public void initialize()
    {
        System.out.println("Initialize broadcasting");

        startUdpConnection();
        startBroadcasting();
    }
    private void startBroadcasting() {
        broadcastServer = new BroadcastServer();
        new Thread(broadcastServer).start();
    }

    private void startUdpConnection() {
        if (udpConnector != null) udpConnector.closeSocket();
        udpConnector = new UdpConnector(this);
        new Thread(udpConnector).start();
    }

    public void receiveMessage(Messages udpMessage)
    {
        table.getItems().add(0, udpMessage);
    }
}