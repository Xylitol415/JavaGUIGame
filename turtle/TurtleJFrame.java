package turtle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class TurtleJFrame extends JFrame implements MouseListener, KeyListener {
	TurtleJPanel jpanel; // ����һ������
	Thread t; // ����һ���߳�����

	public TurtleJFrame() {

		jpanel = new TurtleJPanel();
		t = new Thread(jpanel);

		this.add(jpanel); // ��������ӵ�������
		// ���������
		jpanel.addMouseListener(this);
		// ��Ӽ��̼���
		jpanel.addKeyListener(this); 
		jpanel.setFocusable(true);
		this.setTitle("HappyTurtle");
		this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		t.start();
	}

	public static void main(String[] args) {
		new TurtleJFrame();
	}

	// ������¼�
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (jpanel.fake) {
			jpanel.fake = false;
			jpanel.move = false;
		} else {
			jpanel.fake = true;
			jpanel.move = true;
		}

	}

	// ��곤���¼�
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// ��������¼�
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// ����Ƴ��¼�
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// ����Ƴ��¼�
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// ��������
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// ���̵��
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			jpanel.up = true;
			jpanel.down = false;
			jpanel.left = false;
			jpanel.right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			jpanel.up = false;
			jpanel.down = true;
			jpanel.left = false;
			jpanel.right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			jpanel.up = false;
			jpanel.down = false;
			jpanel.left = true;
			jpanel.right = false;
		} else {
			jpanel.up = false;
			jpanel.down = false;
			jpanel.left = false;
			jpanel.right = true;
		}

	}

	// �����ͷ�
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
