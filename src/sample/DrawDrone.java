package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class DrawDrone {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    private Color color;
    protected String Objects;

    public abstract void draw(GraphicsContext graphicsContext);

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

    public String getObjects() {
        return Objects;
    }

    public void setObjects(String objects) {
        Objects = objects;
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
