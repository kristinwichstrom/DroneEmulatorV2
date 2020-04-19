package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawDrone {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    private Color color;
    protected String shape;

    public DrawDrone (Color color, int x, int y) {
        this.height = 50;
        this.width = 50;
        this.color = color;
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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
    public void drawCircle(GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.color);
        graphicsContext.fillOval(x, y, width, height);
    }
    public void drawRectangle(GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.color);
        graphicsContext.fillRect(x, y, width, height);
    }
}