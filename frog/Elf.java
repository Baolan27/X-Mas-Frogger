package frog;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public class Elf {
    private int x, y;
    private int vx, vy;
    private int width;
    private int height;

    private Image img;
    private Image left, right;

    public Elf(int x, int y, int imgNumber) {
        this.x = x;
        this.y = y;
        vy = 0;
        width = 50;
        height = 50;
        left = getImage("elfleft.png");
        right = getImage("elfright.png");
        if (imgNumber == 1) {
            img = left;
            vx = -1 * ((int) (Math.random()*2) + 1);
        } else {
            img = right;
            vx = (int) (Math.random()*2) + 1;
        }
        init(x, y);
    }

    public void reset() {
        if (img == left) {
            x = 520;
        } else {
            x = -70;
        }
    }

    // gets image and process it
    public void move() {
        y += vy;
        x += vx;
        tx.setToTranslation(x, y);
    }

    public void hop(int t) {
        tx.setToTranslation(x, y);
    }

    private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

    // draw the affine transform
    public void paint(Graphics2D g2) {
        if ((getX() < -70 && getVx() < 0) || (getX() > 520 && getVx() > 0)) {
            reset();
        }
        move();
        g2.drawImage(img, tx, null);
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

    public void setImage(int imgNumber) {
        if (imgNumber == 1) {
            img = left;
        } else {
            img = right;
        }
    }

    // setters and getters
    public void setVx(int vx) {
        this.vx = vx;
    }
    public int getVx() {
        return vx;
    }
    public void setVy(int vy) {
        this.vy = vy;
    }
    public int getVy() {
        return vy;
    }

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