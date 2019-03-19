package star;

import java.awt.Color;

import javax.swing.JFrame;

public class StarJFrame extends JFrame{
	StarJPanel jpanel;
	public StarJFrame() {
		jpanel = new StarJPanel();
		this.add(jpanel);
		this.setTitle("MyStar");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		jpanel.action();
	}
	public static void main(String[] args) {
		new StarJFrame();
	}
}
