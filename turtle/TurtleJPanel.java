package turtle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JPanel;

/*
 * ������
 * extends: ��һ�̳�
 * implements: ��ʵ��
 */
public class TurtleJPanel extends JPanel implements Runnable {
	// ��
	Point pt = new Point(350, 350);
	Point pt1 = new Point(pt.x - 80, pt.y + 80);
	Point pt2 = new Point(pt.x - 80, pt.y + 140);
	Point pt3 = new Point(pt.x + 30, pt.y + 80);
	Point pt4 = new Point(pt.x + 30, pt.y + 140);
	int count = 0; // ��������
	// �������߷���
	boolean up = true;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	// �����ڹ��������
	boolean fake = true;
	// �����ڹ���ƶ�
	boolean move = true;

	// ����
	public void paint(Graphics g) {
		super.paint(g);
		if (fake) {

			g.setColor(Color.CYAN);
			g.setFont(new Font("����", Font.BOLD, 30));
			g.drawString("hello����Һã����Ǻ���Ϊ����", 125, 40);

			// �ڹ�����
			// ����
			g.setColor(new Color(0, 128, 64));
			g.fillRect(pt.x - 15, pt.y + 30, 30, 30);
			// ͷ
			g.setColor(new Color(0, 117, 0));
			g.fillOval(pt.x - 25, pt.y - 10, 50, 50);
			// �۾�
			g.setColor(Color.BLACK);
			g.fillOval(pt.x - 22, pt.y - 5, 10, 10);
			g.fillOval(pt.x + 12, pt.y - 5, 10, 10);
			// ��
			g.setColor(new Color(0, 91, 46));
			g.fillOval(pt1.x, pt1.y, 50, 30);
			g.fillOval(pt2.x, pt2.y, 50, 30);
			g.fillOval(pt3.x, pt3.y, 50, 30);
			g.fillOval(pt4.x, pt4.y, 50, 30);
			// ����� ��β��
			Polygon py = new Polygon();
			py.addPoint(pt.x + 6, pt.y + 200);
			py.addPoint(pt.x - 6, pt.y + 200);
			py.addPoint(pt.x, pt.y + 250);
			// �������
			g.fillPolygon(py);
			// ����
			g.setColor(new Color(0, 117, 0));
			g.fillOval(pt.x - 60, pt.y + 50, 120, 150);

			// �����
			float lineWidth = 3.0f;
			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));
			g.setColor(new Color(255, 128, 0));
			g.drawOval(pt.x - 60, pt.y + 50, 120, 150);

			g.drawLine(pt.x, pt.y + 70, pt.x - 27, pt.y + 100);
			g.drawLine(pt.x - 27, pt.y + 100, pt.x - 27, pt.y + 140);
			g.drawLine(pt.x - 27, pt.y + 140, pt.x, pt.y + 175);

			g.drawLine(pt.x, pt.y + 70, pt.x + 27, pt.y + 100);
			g.drawLine(pt.x + 27, pt.y + 100, pt.x + 27, pt.y + 140);
			g.drawLine(pt.x + 27, pt.y + 140, pt.x, pt.y + 175);

			g.drawLine(pt.x, pt.y + 50, pt.x, pt.y + 70);
			g.drawLine(pt.x - 27, pt.y + 100, pt.x - 50, pt.y + 90);
			g.drawLine(pt.x - 27, pt.y + 140, pt.x - 55, pt.y + 155);

			g.drawLine(pt.x + 27, pt.y + 100, pt.x + 50, pt.y + 90);
			g.drawLine(pt.x + 27, pt.y + 140, pt.x + 55, pt.y + 155);
			g.drawLine(pt.x, pt.y + 175, pt.x, pt.y + 200);
		} else {

			// �ڹ걳��
			// ����
			g.setColor(new Color(0, 117, 0));
			g.fillOval(pt.x - 60, pt.y + 50, 120, 150);
			// �����
			float lineWidth = 3.0f;
			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));
			g.setColor(new Color(255, 128, 0));
			g.drawOval(pt.x - 60, pt.y + 50, 120, 150);
			// ���β���
			g.setColor(new Color(0, 128, 64));
			g.fillRect(pt.x - 15, pt.y + 22, 30, 40);

			// ���ĽŹ���
			g.setColor(new Color(255, 128, 0));
			g.drawOval(pt1.x + 18, pt1.y, 30, 35);
			g.drawOval(pt2.x + 18, pt2.y - 2, 30, 35);
			g.drawOval(pt3.x + 2, pt3.y, 30, 33);
			g.drawOval(pt4.x + 2, pt4.y, 30, 33);
			// �ţ���ס�ŵĹ���
			g.setColor(new Color(0, 91, 46));
			g.fillOval(pt1.x - 3, pt1.y - 1, 50, 35);
			g.fillOval(pt2.x - 3, pt2.y - 2, 50, 35);
			g.fillOval(pt3.x + 3, pt3.y - 1, 50, 35);
			g.fillOval(pt4.x + 3, pt4.y - 2, 50, 35);

			// �����Ĺ���
			g.setColor(new Color(255, 128, 0));
			g.drawLine(pt.x, pt.y + 80, pt.x - 15, pt.y + 100);
			g.drawLine(pt.x - 15, pt.y + 100, pt.x - 15, pt.y + 140);
			g.drawLine(pt.x - 15, pt.y + 140, pt.x, pt.y + 160);

			g.drawLine(pt.x, pt.y + 160, pt.x + 15, pt.y + 140);
			g.drawLine(pt.x + 15, pt.y + 100, pt.x + 15, pt.y + 140);
			g.drawLine(pt.x + 15, pt.y + 100, pt.x, pt.y + 80);
			// �����·�����
			g.drawOval(pt.x - 15, pt.y + 35, 30, 30);
			g.setColor(new Color(0, 128, 64));
			g.fillRect(pt.x - 15, pt.y + 34, 30, 20);

			// β���·�����
			g.setColor(new Color(255, 128, 0));
			g.drawOval(pt.x - 15, pt.y + 180, 30, 30);
			// β���·�Բ�ι����°벿Ϳ��
			g.setColor(new Color(255, 255, 255));
			g.fillRect(pt.x - 15, pt.y + 201, 30, 30);

			// g.fillRect(pt.x - 15, pt.y + 34, 30, 20);
			// �۾�
			g.setColor(Color.BLACK);
			g.fillOval(pt.x - 22, pt.y - 20, 10, 10);
			g.fillOval(pt.x + 12, pt.y - 20, 10, 10);
			// ͷ
			g.setColor(new Color(0, 117, 0));
			g.fillOval(pt.x - 25, pt.y - 25, 50, 60);
			// ����� ��β��
			g.setColor(new Color(0, 91, 46));
			Polygon py = new Polygon();
			py.addPoint(pt.x + 6, pt.y + 190);
			py.addPoint(pt.x - 6, pt.y + 190);
			py.addPoint(pt.x, pt.y + 250);
			// �������
			g.fillPolygon(py);

			g.setColor(new Color(255, 128, 0));
			g.drawLine(pt.x, pt.y + 65, pt.x, pt.y + 80);
			// g.drawLine(pt.x - 27, pt.y + 100, pt.x - 50, pt.y + 90);
			// g.drawLine(pt.x - 27, pt.y + 140, pt.x - 55, pt.y + 155);

			// g.drawLine(pt.x + 27, pt.y + 100, pt.x + 50, pt.y + 90);
			// g.drawLine(pt.x + 27, pt.y + 140, pt.x + 55, pt.y + 155);
			g.drawLine(pt.x, pt.y + 158, pt.x, pt.y + 180);

		}
	}

	@Override
	public void run() {
		while (true) {
			count++;
			if (count >= 4) {
				count = 0;
			}
			if (move) {

				if (up) {
					pt.y--;
					if (count == 0) {
						pt1.y -= 4;
					}
					if (count == 1) {
						pt2.y -= 4;
					}
					if (count == 2) {
						pt3.y -= 4;
					}
					if (count == 3) {
						pt4.y -= 4;
					}
				}
				if (down) {
					pt.y++;
					if (count == 0) {
						pt1.y += 4;
					}
					if (count == 1) {
						pt2.y += 4;
					}
					if (count == 2) {
						pt3.y += 4;
					}
					if (count == 3) {
						pt4.y += 4;
					}
				}
				if (left) {
					pt.x--;
					if (count == 0) {
						pt1.x -= 4;
					}
					if (count == 1) {
						pt2.x -= 4;
					}
					if (count == 2) {
						pt3.x -= 4;
					}
					if (count == 3) {
						pt4.x -= 4;
					}
				}
				if (right) {
					pt.x++;
					if (count == 0) {
						pt1.x += 4;
					}
					if (count == 1) {
						pt2.x += 4;
					}
					if (count == 2) {
						pt3.x += 4;
					}
					if (count == 3) {
						pt4.x += 4;
					}
				}
			}

			// �߳�����
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (pt.y <= 0) {
				pt.y = 300;
				pt1.y = 380;
				pt2.y = 440;
				pt3.y = 380;
				pt4.y = 440;
			}
			repaint();
		}
	}

}
