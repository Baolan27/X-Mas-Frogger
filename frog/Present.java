package frog;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public class Present {

    private int x, y;
    private int width;
    private int height;

    private Image img;

    public Present(int x, int y) {
        this.x = x;
        this.y = y;
        width = 50;
        height = 50;
        img = getImage("present.png");
        init(x, y);
    }

    private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

    // draw the affine transform
    public void paint(Graphics2D g2) {
        g2.drawImage(img, x, y, null);
    }

    public void delete() {
        x = -1000;
        y = -1000;
    }

    private void init(double a, double b) {
        tx.setToTranslation(a, b);
        tx.scale(1, 1);
    }

    // converts image to make it drawable in paint
    private Image getImage(String path) {
        Image tempImage = null;
        try {
            tempImage = Toolkit.getDefaultToolkit().getImage(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }

    // setters and getters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        tx.setToTranslation(x, y);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        tx.setToTranslation(x, y);
    }

    public Rectangle getRect() {
        Rectangle temp = new Rectangle(x,y,width,height);
        return temp;
    }

}