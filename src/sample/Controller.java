package sample;

import javafx.event.ActionEvent;

public class Controller {

    public void toggleBtnDrone(ActionEvent actionEvent) {
        System.out.println("toggle Drone button");
    }

    public void toggleBtnBroadcast(ActionEvent actionEvent) {
        System.out.println("Toggle Broadcast button");
    }
    public void clearTable (){
        System.out.println("Clear table is pressed");
    }
    public void pressCircle () {
        System.out.println("Circle is pressed");
    }
}
