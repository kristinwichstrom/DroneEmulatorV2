package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawDrone {
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    private Color color;
    protected String shape;

    public void drawCircle(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(this.color);
        graphicsContext.setFill(this.color);
        graphicsContext.fillOval(x, y, width, height);
    }
    public void drawRectangle(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(this.color);
        graphicsContext.setFill(this.color);
        graphicsContext.fillRect(x, y, width, height);
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
