package star;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class StarJPanel extends JPanel {
	int[] x = new int[200];
	int[] y = new int[200];

	public StarJPanel() {
		this.setBackground(Color.BLACK);
		for (int i = 0; i < x.length; i++) {
			x[i] = (int) (Math.random() * 790);
			y[i] = (int) (Math.random() * 590);
		}
	}

	public void paint(Graphics g) {
		super.paint(g); // ˢ�´���
		g.setFont(new Font("����", Font.BOLD, 20));
		g.setColor(Color.YELLOW);
		g.fillOval(60, 60, 50, 50); // ��һ������
		g.setColor(Color.BLACK);
		g.fillOval(48, 48, 50, 50); 
		for (int i = 0; i < x.length; i++) {
			int R = (int) (Math.random() * 255);
			int G = (int) (Math.random() * 255);
			int B = (int) (Math.random() * 255);
			Color color = new Color(R, G, B);
			g.setColor(color);
			g.drawString("��", x[i], y[i]);
		}
	}

	public void action() {
		// ��ʱ��
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				for (int i = 0; i < y.length; i++) {
					if(y[i] < 600)
						y[i] += 2;
					else
						y[i] = 0;
				}
				repaint(); // �ػ�
			}
		}, 10, 10); // ע�⣺�����ʱ�䵥λ�Ǻ���
	}
}
