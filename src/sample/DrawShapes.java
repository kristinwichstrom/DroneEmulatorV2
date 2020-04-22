/*package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawShapes {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    private Color color;
    protected String shape;

    public DrawShapes(Color color, double x, double y) {
        this.height = 50;
        this.width = 50;
        this.color = color;
        this.x=x;
        this.y=y;
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

    public void setY(int y) {
        this.y = y;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
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
    public void clearCanvas (GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.color);
        graphicsContext.clearRect(x,y,width,height);
    }
}
*/