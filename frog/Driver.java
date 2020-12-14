package frog;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener {

	Froggy froggy;
	Narwhal[] narwhalArray;
	Elf[] elfArray;
	Toboggan[] tobogganArray;
	Ornament[] ornamentArray;
	Present[] presentArray;
	boolean touchingWater;

	Font big = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
	Font biggest = new Font("Courier New", 1, 80);
	// paint method
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Image bg = Toolkit.getDefaultToolkit().getImage("background1.png"); //set bg

		super.paintComponent(g2);
		g.drawImage(bg, 0, 0, null);

		touchingWater = (froggy.getY() < 290 && froggy.getY() > 20);
		for (int i = 0; i < narwhalArray.length; i++) {
			narwhalArray[i].paint(g2);
			if (froggy.getRect().intersects(narwhalArray[i].getRect())) {
				touchingWater = false;
			}
		}

		if (touchingWater) {
			froggy.reset();
			froggy.setLives(froggy.getLives() - 1);
		}

		for(int i = 0; i < elfArray.length; i++) {
			elfArray[i].paint(g2);
			if (froggy.getRect().intersects(elfArray[i].getRect())) {
				froggy.reset();
				froggy.setLives(froggy.getLives() - 1);
			}
		}

		for (int i = 0; i < tobogganArray.length; i++) {
			tobogganArray[i].paint(g2);
			if (froggy.getRect().intersects(tobogganArray[i].getRect())) {
				froggy.reset();
				froggy.setLives(froggy.getLives() - 1);
			}
		}

		for (int i = 0; i < ornamentArray.length; i++) {
			ornamentArray[i].paint(g2);
			if (froggy.getRect().intersects(ornamentArray[i].getRect())) {
				froggy.reset();
				froggy.setLives(froggy.getLives() - 1);
			}
		}

		for (int i = 0; i < presentArray.length; i++) {
			presentArray[i].paint(g2);
			if (froggy.getRect().intersects(presentArray[i].getRect())) {
				froggy.reset();
				presentArray[i].delete();
				System.out.println("Won!");
			}
		}

		froggy.paint(g2);
	}

	public void update() {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("X-Mas Frogger");
		f.setSize(450, 650);
		f.setResizable(false);
		f.addKeyListener(this);

		//setup objects and arrays here
		froggy = new Froggy();
		narwhalArray = new Narwhal[10];
		for (int i = 0; i < narwhalArray.length; i++) {
			if (i < 5) {
				if (i%2 == 0) {
					narwhalArray[i] = new Narwhal(520, 25 + (i*60), 1);
				} else {
					narwhalArray[i] = new Narwhal(-200, 25 + (i*60), 2);
				}
			} else {
				if (i%2 != 0) {
					narwhalArray[i] = new Narwhal(770, 25 + ((i-5)*60), 1);
				} else {
					narwhalArray[i] = new Narwhal(-450, 25 + ((i-5)*60), 2);
				}
			}
		}
		elfArray = new Elf[4];
		for (int i = 0; i < elfArray.length; i++) {
			if (i%2 == 0) {
				elfArray[i] = new Elf(520, 320 + (i*60), 1);
			} else {
				elfArray[i] = new Elf(-200, 320 + (i*60), 2);
			}
		}
		tobogganArray = new Toboggan[4];
		for (int i = 0; i < tobogganArray.length; i++) {
			if (i%2 == 0) {
				tobogganArray[i] = new Toboggan(800, 320 + (i*60), 1);
			} else {
				tobogganArray[i] = new Toboggan(-600, 320 + (i*60), 2);
			}
		}
		ornamentArray = new Ornament[4];
		for (int i = 0; i < ornamentArray.length; i++) {
			if (i%2 == 0) {
				ornamentArray[i] = new Ornament(800, 320 + (i*60), 1);
			} else {
				ornamentArray[i] = new Ornament(-600, 320 + (i*60), 2);
			}
		}
		presentArray = new Present[5];
		for (int i = 0; i < presentArray.length; i++) {
			presentArray[i] = new Present(i*100, 0);
		}

		f.addMouseListener(this);
		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			froggy.setVx(5);
			froggy.setImage(4);
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			froggy.setVx(-5);
			froggy.setImage(3);
		}
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			froggy.setVy(-5);
			froggy.setImage(1);
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			froggy.setVy(5);
			froggy.setImage(2);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*
		 * turn off velocity for Frog if you don't want it moving when you have stopped
		 * pressing the keys
		 */

		// do the same thing for the other keys
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
			froggy.setVx(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
			froggy.setVy(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}