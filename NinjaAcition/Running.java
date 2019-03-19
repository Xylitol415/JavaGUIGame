package NinjaAcition;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import MyShoot.FlyingObject;
import MyShoot.Shoot;

public class Running extends JPanel{
	static final int WIDTH = 452;
	static final int HEIGHT = 602;
	
	// ����ͼƬ����
	static BufferedImage bk;
	static BufferedImage bk1;
	static BufferedImage nl1;
	static BufferedImage nl2;
	static BufferedImage nr1;
	static BufferedImage nr2;
	
	static boolean changeDir = false;
	
	Ninja n = new Ninja(); // ���߶���
//	Barrier b = new Barrier(); // �ϰ������
	Barrier[] bls = {}; // ����ϰ�������
	Barrier[] brs = {}; // �Ҳ��ϰ�������
	// ��̬�����
	static {
		try {
			
			bk = ImageIO.read(Running.class.getResource("background.jpg"));
			bk1 = ImageIO.read(Running.class.getResource("background.jpg"));
			
			nl1 = ImageIO.read(Running.class.getResource("ninjaL1.png"));
			nl2 = ImageIO.read(Running.class.getResource("ninjaL2.png"));
			nr1 = ImageIO.read(Running.class.getResource("ninjaR1.png"));
			nr2 = ImageIO.read(Running.class.getResource("ninjaR2.png"));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Barrier nextOne() {
		return new Barrier();
	}
	
	int enterIndex = 0; // �����ϰ�������
	public void enterAction() { 
		enterIndex++;
		if(enterIndex % 80 == 0) {
			bls = Arrays.copyOf(bls, bls.length+1);
			bls[bls.length-1] = nextOne();
			brs = Arrays.copyOf(brs, brs.length+1);
			brs[brs.length-1] = nextOne();
		}
	}
	
	// ������
//	int a = 0;
	public void paintBackGround(Graphics g) {
		g.drawImage(bk, 0, 0, null);
//		g.drawImage(bk, 0, a++, null);
//		g.drawImage(bk1, 0, -HEIGHT + a, null);
//		if (a >= HEIGHT) {
//			a = 0;
//		}
	}
	// ������
	public void paintNinja(Graphics g) {
		g.drawImage(n.image, n.x, n.y, null);
	}
	// ���ϰ���
	public void paintBarrier(Graphics g) {
		//g.fillRect(b.x, b.y, b.width, b.height);
		// �������ϰ���
		for(int i = 0; i < bls.length; i++) {
			g.setColor(new Color(70, 80, 67));
			g.fillRect(bls[i].x, bls[i].y, bls[i].width, bls[i].height);	
			g.setColor(new Color(255, 38, 38));
			float lineWidth = 5.0f;
			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));
			g.drawRect(bls[i].x, bls[i].y, bls[i].width, bls[i].height);
		}
		
		// ����Ҳ��ϰ���
		for(int i = 0; i < brs.length; i++) {
			g.setColor(new Color(70, 80, 67));
			g.fillRect(WIDTH - brs[i].width, brs[i].y, brs[i].width, brs[i].height);
			g.setColor(new Color(255, 38, 38));
			float lineWidth = 5.0f;
			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));
			g.drawRect(WIDTH - brs[i].width, brs[i].y, brs[i].width, brs[i].height);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		paintBackGround(g); // ������
		paintBarrier(g); // ���ϰ���
		paintNinja(g); // ������
	}
	
	public void stepAction() {
		n.step();
		//b.step();
		for(int i = 0; i < bls.length; i++) {
			bls[i].step();
		}
		for(int i = 0; i < brs.length; i++) {
			brs[i].step();
		}
	}
	
	// ��ײ
	int count1 = -1;
	int count2 = -2;
	public void collisionAction(){	
		for(int i = 0; i < bls.length; i++) {
			Barrier ba = bls[i];
			if(n.collision(ba.x, ba.y, ba.width, ba.height)) {
					n.xspeed = 0;
					System.out.println("dad");
					count1 = i;
			}else if(count1 == i){
				n.xspeed = 2;
				System.out.println("qwwd");
			}
		}
//		for(int i = 1; i < bls.length; i++) {
		
//			Barrier ba = bls[i-1];
//			Barrier bb = bls[i];
//			if(n.xspeed == 0 && (ba.width - bb.width > 0)) {					
//				n.xspeed = 3;
//			}
//		}
	
		for(int i = 0; i < brs.length; i++) {
			Barrier ba = brs[i];			
			if(n.collision(WIDTH - ba.width, ba.y, ba.width, ba.height)) {
				n.xspeed = 0;
				count2 = i;
			}else if(count2 == i){
				n.xspeed = 3;
			}
		}
//		for(int i = 1; i < brs.length; i++) {
//			Barrier ba = brs[i-1];
//			Barrier bb = brs[i];		
//			if(n.xspeed == 0 && (ba.width - bb.width > 0)) {							
//						n.xspeed = 3;
//			}
//		}
		
	}
	public void continuousAction() {
		if(n.direction == 0) {			
			for(int i = 1; i < bls.length; i++) {
				if(n.xspeed == 0 && !bls[i].continuous(bls[i-1].x, bls[i-1].y, bls[i-1].width, bls[i].height)){
					n.xspeed = 3;
				}
			}
		}
		else {
			for(int i = 1; i < brs.length; i++) {
				if(n.xspeed == 0 && !brs[i].continuous(brs[i-1].x, brs[i-1].y, brs[i-1].width, brs[i].height)){
					n.xspeed = 3;
				}
			}	
		}
	}
	
	public void heigherThanBarrierAction() {
		for(int i = 0; i < bls.length; i++) {
			Barrier ba = bls[i];
			if(ba.y >= n.y && !n.edge) {
				n.xspeed = 1;
			}
			
		}
		for(int i = 0; i < brs.length; i++) {
			Barrier ba = brs[i];
			if(ba.y >= n.y && !n.edge) {
				n.xspeed = 1;
			}
		}
	}
	// �ϰ���Խ��
	public void outOfBoundAction() {
		int index = 0;
		// ���
		Barrier[] barrierLeftLive = new Barrier[bls.length];
		for(int i = 0; i <bls.length; i++) {
			if(!bls[i].outOfBound()) {
				barrierLeftLive[index] = bls[i];
				index++;	
			}
		}
		bls = Arrays.copyOf(barrierLeftLive, index);
		// �Ҳ�
		index = 0;
		Barrier[] barrierRightLive = new Barrier[brs.length];
		for(int i = 0; i <brs.length; i++) {
			if(!brs[i].outOfBound()) {
				barrierRightLive[index] = brs[i];
				index++;	
			}
		}
		brs = Arrays.copyOf(barrierRightLive, index);
	}
	
	public void action() {
		// �������¼�
			MouseAdapter l = new MouseAdapter() {
				// ��굥��
				public void mouseClicked(MouseEvent e) {
					switch(n.direction) {
					case 0:
						// ��������ķ���
						n.direction = 1;
						changeDir = true;
						// ���ϰ���߽絥�������ԭ�ٶ�
						n.xspeed = 3;
						break;
					case 1:
						n.direction = 0;
						changeDir = true;
						n.xspeed = 3;
					}
				}
			};
			this.addMouseListener(l); // ����¼�
			this.addMouseMotionListener(l);
			// ��ʱ��
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					repaint(); // �ػ�
					enterAction(); // �ϰ������
					stepAction(); // �߲�
					collisionAction(); // ��ײ
					continuousAction();
					//heigherThanBarrierAction();
					//outOfBoundAction();
				}
			}, 10, 10);
	}
	
	
	public static void main(String[] args) {
		// ����һ���������
		Running r = new Running();
		// �������������
		JFrame j = new JFrame();
		// ��������ӵ�������ȥ
		j.add(r);
		j.setTitle("�񱼵�����"); // ��������
		j.setSize(WIDTH, HEIGHT); // ���ô�С
		j.setLocationRelativeTo(null); // �����м���ʾ
		// �����洰�ڵĹرն��ر�
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true); // ���ÿɼ���
		r.action();
	}
}
