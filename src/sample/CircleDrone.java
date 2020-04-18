package sample;

import javafx.scene.canvas.GraphicsContext;

public class CircleDrone extends DrawDrone {

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.fillOval(x,y,height,width);

    }
}
