package frog;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public class Froggy{
    // attributes of a frog
    private int x, y; // Position of frog
    private int vx, vy;
    private int alive; // lives
    private int width; // the size of frog
    private int height;

    private Image img; // image of the frog
    private Image left,right,up,down;

    public Froggy() {
        // assignment statements for attributes
        x = 225;
        y = 560;
        vx = 0;
        vy = 0;
        width = 50;
        height = 50;
        left = getImage("frogleft.png");
        right = getImage("frogright.png");
        up = getImage("frogpic.png");
        down = getImage("frogdown.png");
        img = up;
        alive = 3; //lives
        //call init anytime x and y of image is being set
        init(x, y);
    }

    /* if filename is provided */
    public Froggy(String fileName) {
        // assignment statements for attributes
        x = 200;
        y = 250;
        vx = 0;
        vy = 0;
        width = 50;
        height = 50;
        img = getImage(fileName);
        init(x, y);

    }

    public void reset() {
        x = 220;	//reset position
        y = 560;
        vx = 0;
        vy = 0;
        img = up;	//reset img
    }


    // gets image and proccess it
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
        if ((getX() > -5 || getVx() >= 0) && (getX() < 445 || getVx() <= 0) && (getY() > 0 || getVy() >= 0) && (getY() < 570 || getVy() <= 0)) {
            move();
        }
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
        switch (imgNumber) {
            case 2 -> img = down;
            case 3 -> img = left;
            case 4 -> img = right;
            default -> img = up;
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

    public int getLives() {
        return alive;
    }
    public void setLives(int lives) {
        alive = lives;
    }
    public Rectangle getRect() { //collisions
        Rectangle temp = new Rectangle(x,y,width,height);
        return temp;
    }

}