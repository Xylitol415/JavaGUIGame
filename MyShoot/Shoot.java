package MyShoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
/*
 *  ��ǰ���ǻ�����
 */
import javax.swing.event.MenuDragMouseEvent;

public class Shoot extends JPanel {
	static final int WIDTH = 512; // ���ڵĿ�
	static final int HEIGHT = 768; // ���ڵĸ�
	/*
	 * static���ε����Ժ���Ϊ���Ǿ�̬�� һ�����Ǽ�����Դ��ʱ��Ὣ��Դ�����Զ���Ϊstatic�� ��Ϊ��̬���������ڴ�ռ���ֻ��һ��
	 * 
	 * ��̬��������������ģ��������ڶ����
	 */
	static BufferedImage bk; // ����ͼƬ����
	static BufferedImage bk1;

	// Ӣ�ۻ�ͼƬ����
	static BufferedImage hero0;
	static BufferedImage hero1;
	static BufferedImage hero2;
	static BufferedImage hero3;
	static BufferedImage hero4;
	static BufferedImage hero5;
	static BufferedImage hero6;
	static BufferedImage hero7;
	static BufferedImage hero8;
	static BufferedImage hero9;

	// �л�ͼƬ����
	static BufferedImage a0;
	static BufferedImage a1;
	static BufferedImage a2;
	static BufferedImage a3;
	static BufferedImage a4;
	static BufferedImage a5;

	// ����ͼƬ����
	static BufferedImage q0;
	static BufferedImage q1;
	static BufferedImage q2;
	static BufferedImage q3;
	static BufferedImage q4;
	static BufferedImage q5;
	static BufferedImage q6;
	static BufferedImage q7;
	static BufferedImage q8;

	// �ӵ�ͼƬ����
	static BufferedImage bullet;

	// ״̬ͼƬ����
	static BufferedImage start;
	static BufferedImage pause;
	static BufferedImage game_over;

	// boss��ͼƬ����
	static BufferedImage b0;
	static BufferedImage b1;
	static BufferedImage b2;
	static BufferedImage b3;

	// boss���ӵ�
	static BufferedImage bbu0;
	static BufferedImage bbu1;

	// ״̬
	static final int START = 0;
	static final int RUNNING = 1;
	static final int PAUSE = 2;
	static final int GAME_OVER = 3;
	int state = 0;
	
	FlyingObject[] flying = {};

	Hero h = new Hero(); // Ӣ�ۻ�����
	// AirPlane ap = new AirPlane(); // С�л�����
	// С�л�����
//	AirPlane[] aps = {};
	// Heart ht = new Heart(); // С���Ķ���
	// ��������
//	Heart[] hts = {};
	// �ӵ�����
	Bullet[] bs = {};
	// boss������
	Boss[] bos = {};
	BossBullet[] bbs = {};

	// ��̬�����
	static {
		try {
			// ���ر���ͼƬ
			bk = ImageIO.read(Shoot.class.getResource("background.jpg"));
			bk1 = ImageIO.read(Shoot.class.getResource("background.jpg"));
			// ����Ӣ�ۻ�ͼƬ
			hero0 = ImageIO.read(Shoot.class.getResource("ws00.png"));
			hero1 = ImageIO.read(Shoot.class.getResource("ws01.png"));
			hero2 = ImageIO.read(Shoot.class.getResource("ws02.png"));
			hero3 = ImageIO.read(Shoot.class.getResource("ws03.png"));
			hero4 = ImageIO.read(Shoot.class.getResource("ws04.png"));
			hero5 = ImageIO.read(Shoot.class.getResource("ws05.png"));
			hero6 = ImageIO.read(Shoot.class.getResource("ws06.png"));
			hero7 = ImageIO.read(Shoot.class.getResource("ws07.png"));
			hero8 = ImageIO.read(Shoot.class.getResource("ws08.png"));
			hero9 = ImageIO.read(Shoot.class.getResource("ws09.png"));
			// ���صл�ͼƬ
			a0 = ImageIO.read(Shoot.class.getResource("flys0.png"));
			a1 = ImageIO.read(Shoot.class.getResource("flys1.png"));
			a2 = ImageIO.read(Shoot.class.getResource("flys2.png"));
			a3 = ImageIO.read(Shoot.class.getResource("flys3.png"));
			a4 = ImageIO.read(Shoot.class.getResource("flys4.png"));
			a5 = ImageIO.read(Shoot.class.getResource("flys5.png"));
			// ���ذ���ͼƬ
			q0 = ImageIO.read(Shoot.class.getResource("qq00.png"));
			q1 = ImageIO.read(Shoot.class.getResource("qq01.png"));
			q2 = ImageIO.read(Shoot.class.getResource("qq02.png"));
			q3 = ImageIO.read(Shoot.class.getResource("qq03.png"));
			q4 = ImageIO.read(Shoot.class.getResource("qq04.png"));
			q5 = ImageIO.read(Shoot.class.getResource("qq05.png"));
			q6 = ImageIO.read(Shoot.class.getResource("qq06.png"));
			q7 = ImageIO.read(Shoot.class.getResource("qq07.png"));
			q8 = ImageIO.read(Shoot.class.getResource("qq08.png"));

			bullet = ImageIO.read(Shoot.class.getResource("bullets.png"));
			// ����״̬ͼƬ
			start = ImageIO.read(Shoot.class.getResource("start.jpg"));
			pause = ImageIO.read(Shoot.class.getResource("zan.jpg"));
			game_over = ImageIO.read(Shoot.class.getResource("over.jpg"));
			// ����boss��ͼƬ
			b0 = ImageIO.read(Shoot.class.getResource("boss0.png"));
			b1 = ImageIO.read(Shoot.class.getResource("boss1.png"));
			b2 = ImageIO.read(Shoot.class.getResource("boss2.PNG"));
			b3 = ImageIO.read(Shoot.class.getResource("boss3.PNG"));
			// ����boss�ӵ�ͼƬ
			bbu0 = ImageIO.read(Shoot.class.getResource("bossbu0.png"));
			bbu1 = ImageIO.read(Shoot.class.getResource("bossbu1.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FlyingObject nextOne() {
		int num = (int)(Math.random()*20);
		if(num < 10) {
			return new AirPlane();
		} else {
			return new Heart();
		}
	}

//	// ����С�л�
//	public AirPlane nextOne() {
//		return new AirPlane();
//	}

//	// ���ɰ��Ķ���
//	public Heart nextOne1() {
//		return new Heart();
//	}

	// // ����boss������
	// public Boss nextOne2() {
	// return new Boss();
	// }
	// ������ǳ�
	int enterIndex = 0; // ����С�л�����

	public void enterAction() { // 10���� --> 100����
		enterIndex++;
		if(enterIndex % 40 == 0) {
			flying = Arrays.copyOf(flying, flying.length+1);
			flying[flying.length-1] = nextOne();
		}
//		if (enterIndex % 40 == 0) {// С�л��ǳ�
//			// ���������
//			aps = Arrays.copyOf(aps, aps.length + 1);
//			aps[aps.length - 1] = nextOne();
//		}
//
//		if (enterIndex % 100 == 0) { // ���ĵǳ�
//			// ���������
//			hts = Arrays.copyOf(hts, hts.length + 1);
//			hts[hts.length - 1] = nextOne1();
//		}
//		// if(enterIndex % 150 == 0) { // boss���ǳ�
//		// bos = Arrays.copyOf(bos, bos.length+1);
//		// bos[bos.length-1] = nextOne2();
//		// }
		// boss���ǳ�
		if (h.life % 5 == 0) {
			if (bos.length == 0) {
				bos = Arrays.copyOf(bos, bos.length + 1);
				bos[bos.length - 1] = new Boss();
			}
		}
		if (enterIndex > Integer.MAX_VALUE) {
			enterIndex = 0;
		}
	}

	// ������
	int a = 0;

	public void paintBackGround(Graphics g) {
		g.drawImage(bk, 0, a++, null);
		g.drawImage(bk1, 0, -HEIGHT + a, null);
		if (a >= HEIGHT) {
			a = 0;
		}
	}

	// ��Ӣ�ۻ�
	public void paintHero(Graphics g) {
		g.drawImage(h.image, h.x, h.y, null);
	}
	
/*
	// ��С�л�
	public void paintAirPlane(Graphics g) {
		// g.drawImage(ap.image, ap.x, ap.y, null);
		for (int i = 0; i < aps.length; i++) {
			g.drawImage(aps[i].image, aps[i].x, aps[i].y, null);
		}
	}

	// ������
	public void paintHeart(Graphics g) {
		// g.drawImage(ht.image, ht.x, ht.y, null);
		for (int i = 0; i < hts.length; i++) {
			g.drawImage(hts[i].image, hts[i].x, hts[i].y, null);
		}
	}
*/
	// ������
	public void paintFlyingObject(Graphics g) {
		for(int i = 0; i < flying.length; i++) {
			g.drawImage(flying[i].image, flying[i].x, flying[i].y, null);
		}
	}
	// ���ӵ�
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bs.length; i++) {
			g.drawImage(bs[i].image, bs[i].x, bs[i].y, null);
			// �������ӵ�
			// g.drawImage(bs[i].image, bs[i].x+4*bs[i].width, bs[i].y, null);
		}
	}

	// ������ֵ
	public void paintLife(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("����", Font.BOLD, 20));
		g.drawString("����ֵ:" + h.life, 380, 50);
	}

	// ��״̬
	public void paintState(Graphics g) {
		switch (state) {
		case 0:
			g.drawImage(start, 0, 0, null);
			break;
		case 2:
			g.drawImage(pause, 0, 0, null);
			break;
		case 3:
			g.drawImage(game_over, 0, 0, null);
		}
	}

	// ��boss��
	public void paintBoss(Graphics g) {
		for (int i = 0; i < bos.length; i++) {
			g.drawImage(bos[i].image, bos[i].x, bos[i].y, null);
		}
	}

	// ��boss���ӵ�
	public void paintBossBullet(Graphics g) {
		for (int i = 0; i < bbs.length; i++) {
			g.drawImage(bbs[i].image, bbs[i].x, bbs[i].y, null);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		paintBackGround(g); // ������
		paintHero(g); // ��Ӣ�ۻ�
		
//		paintAirPlane(g); // ��С�л�
//		paintHeart(g); // ������
		paintFlyingObject(g); // ������
		
		paintBullets(g); // ���ӵ�
		paintLife(g); // ������ֵ
		paintBoss(g); // ��boss��
		paintBossBullet(g); // ��boss���ӵ�
		paintState(g); // ��״̬
	}

	// �������߲�
	public void stepAction() {
		h.step();
		// ap.step();
		
		
//		for (int i = 0; i < aps.length; i++) {
//			aps[i].step();
//		}
//		// ht.step();
//		for (int i = 0; i < hts.length; i++) {
//			hts[i].step();
//		}
		// �����߲�
		for(int i = 0; i < flying.length; i++) {
			flying[i].step();;
		}
		
		for (int i = 0; i < bs.length; i++) {
			bs[i].step();
		}
		for (int i = 0; i < bos.length; i++) {
			bos[i].step();
		}
		for (int i = 0; i < bbs.length; i++) {
			bbs[i].step();
		}
	}

	// �����ӵ�
	int shootIndex = 0; // �����ӵ�������

	public void ShootAction() {
		shootIndex++;
		if (shootIndex % 30 == 0) {
			Bullet[] bs1 = h.shootBy();
			bs = Arrays.copyOf(bs, bs.length + bs1.length);
			// ����ĸ���
			System.arraycopy(bs1, 0, bs, bs.length - bs1.length, bs1.length);
		}
		// boss�������ӵ�
		if (shootIndex % 120 == 0) {
			for (int i = 0; i < bos.length; i++) {
				if (!bos[i].UP && bos[i].y == 0) {
					BossBullet[] bbs1 = bos[i].shoot();
					bbs = Arrays.copyOf(bbs, bbs.length + bbs1.length);
					System.arraycopy(bbs1, 0, bbs, bbs.length - bbs1.length, bbs1.length);
				}
			}
		}
	}

	// ��ײ
	public void bangAction() {
		// ���˺��ӵ���ײ
		for(int i = 0; i < bs.length; i++) {
			for(int j = 0; j < flying.length; j++) {
				Bullet b = bs[i];
				FlyingObject f = flying[j];
				if(b.bang(f.x, f.y, f.width, f.height)) {
					if(f instanceof AirPlane) {
						h.doubleFire = 40;
					}
					if(f instanceof Heart) {
						h.life++;
					}
					bs[i] = bs[bs.length-1];
					bs = Arrays.copyOf(bs, bs.length-1);
					flying[j] = flying[flying.length - 1];
					flying = Arrays.copyOf(flying, flying.length-1);
					break;
				} 
			}
		}
		if(h.doubleFire > 0) {
			h.doubleFire--;
		}
		// Ӣ�ۻ���С�л���ײ
		for (int i = 0; i < flying.length; i++) {
			FlyingObject f = flying[i];
			if (h.bang(f.x, f.y, f.width, f.height)) {
				if(f instanceof AirPlane){
					flying[i] = flying[flying.length - 1];
					flying = Arrays.copyOf(flying, flying.length - 1);
					h.life--;					
				}	
			}
		}

//		// �ӵ���С�л���ײ
//		for (int i = 0; i < bs.length; i++) {
//			for (int j = 0; j < aps.length; j++) {
//				Bullet b = bs[i];
//				AirPlane a = aps[j];
//				if (b.bang(a.x, a.y, a.width, a.height)) {
//					// ���������
//					bs[i] = bs[bs.length - 1];
//					bs = Arrays.copyOf(bs, bs.length - 1);
//					aps[j] = aps[aps.length - 1];
//					aps = Arrays.copyOf(aps, aps.length - 1);
//					h.doubleFire = 40;
//					break;
//				}
//			}
//		}
//		if (h.doubleFire > 0) {
//			h.doubleFire--;
//		}
//		// �ӵ��Ͱ��ĵ���ײ
//		for (int i = 0; i < bs.length; i++) {
//			for (int j = 0; j < hts.length; j++) {
//				Bullet b = bs[i];
//				Heart h1 = hts[j];
//				if (b.bang(h1.x, h1.y, h1.width, h1.height)) {
//					bs[i] = bs[bs.length - 1];
//					bs = Arrays.copyOf(bs, bs.length - 1);
//					hts[j] = hts[hts.length - 1];
//					hts = Arrays.copyOf(hts, hts.length - 1);
//					h.life++;
//					break;
//				}
//			}
//		}
//		// Ӣ�ۻ���С�л���ײ
//		for (int i = 0; i < aps.length; i++) {
//			AirPlane a = aps[i];
//			if (h.bang(a.x, a.y, a.width, a.height)) {
//				aps[i] = aps[aps.length - 1];
//				aps = Arrays.copyOf(aps, aps.length - 1);
//				h.life--;	
//			// break: ������ǰѭ���� ���������ѭ����������ѭ�� ���ֻ��һ��ѭ������������ǰѭ��
//			// break; // ����ѭ��
//			}
//		}

		// �ӵ���boss������ײ
		for (int i = 0; i < bos.length; i++) {
			for (int j = 0; j < bbs.length; j++) {
				if (bs[j].bang(bos[i].x, bos[i].y, bos[i].image.getWidth(), bos[i].image.getHeight())) {
					if (!bos[i].UP && bos[i].y == 0) {
						bos[i].life--;
					}
					// System.out.println("bossLife: "+ bos[i].life);
					if (bos[i].life <= 0) {
						bos = Arrays.copyOf(bos, 0);
						// bbs = Arrays.copyOf(bbs, 0); // boss������ɾ������Ļ�ϵ��ӵ�����
					}
					bs[j] = bs[bs.length - 1];
					bs = Arrays.copyOf(bs, bs.length - 1);
				}
				break;
			}
		}
		// boss���ӵ���Ӣ�ۻ���ײ
		for (int i = 0; i < bbs.length; i++) {
			if (bbs[i].bang(h.x, h.y, h.image.getWidth(), h.image.getWidth())) {
				h.life--;
				bbs[i] = bbs[bbs.length - 1];
				bbs = Arrays.copyOf(bbs, bbs.length - 1);
			}

		}
	}

	// Խ�紦��
	public void outOfBoundAction() {
		int index = 0;
		// ����Խ��
		FlyingObject[] flyingLive = new FlyingObject[flying.length];
		for(int i = 0; i <flying.length; i++) {
			if(!flying[i].outOfBound()) {
				flyingLive[index] = flying[i];
				index++;	
			}
		}
		flying = Arrays.copyOf(flyingLive, index);
//		// С�л�Խ��
//		AirPlane[] apsLive = new AirPlane[aps.length];
//		for (int i = 0; i < aps.length; i++) {
//			if (!aps[i].outOfBound()) {
//				apsLive[index] = aps[i];
//				index++;
//			}
//		}
//		aps = Arrays.copyOf(apsLive, index);
//
//		// ����Խ��
//		index = 0;
//		Heart[] htsLive = new Heart[hts.length];
//		for (int i = 0; i < hts.length; i++) {
//			if (!hts[i].outOfBound()) {
//				htsLive[index] = hts[i];
//				index++;
//			}
//		}
//		hts = Arrays.copyOf(htsLive, index);

		// �ӵ�Խ��
		index = 0;
		Bullet[] bsLive = new Bullet[bs.length];
		for (int i = 0; i < bs.length; i++) {
			if (!bs[i].OutOfBound()) {
				bsLive[index] = bs[i];
				index++;
			}
		}
		bs = Arrays.copyOf(bsLive, index);
		
		// boss���ӵ�Խ��
		index = 0;
		BossBullet[] bbsLive = new BossBullet[bbs.length];
		for(int i = 0; i < bbs.length; i++) {
			if(!bbs[i].outOfBound()) {
				bbsLive[index] = bbs[i];
				index++;
			}
		}
		bbs = Arrays.copyOf(bbsLive, index);
	}

	// ��Ϸ����
	public void Game_Over() {
		if (h.life <= 0) {
			state = GAME_OVER;
			
//			aps = Arrays.copyOf(aps, 0);
//			hts = Arrays.copyOf(hts, 0);
			flying = Arrays.copyOf(flying, 0);
			
			bs = Arrays.copyOf(bs, 0);
			
			bos = Arrays.copyOf(bos, 0);
			bbs = Arrays.copyOf(bbs, 0);
		}
	}

	public void action() {
		// �������¼�
		MouseAdapter l = new MouseAdapter() {
			// ����ƶ��¼�
			public void mouseMoved(MouseEvent e) {
				// h.x = e.getX();
				// h.y = e.getY();
				h.moveTo(e.getX(), e.getY());
			}

			// ��굥��
			public void mouseClicked(MouseEvent e) {
				switch (state) {
				case START:
					state = RUNNING;
					break;
				case RUNNING:
					state = PAUSE;
					break;
				case PAUSE:
					state = RUNNING;
					break;
				case GAME_OVER:
					state = START;
					h.life = 3;
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
				// TODO Auto-generated method stub
				if (state == RUNNING) {
					enterAction(); // ������ǳ�
					stepAction(); // �߲�
					ShootAction(); // �����ӵ�
					bangAction(); // ��ײ�¼�
					outOfBoundAction();
				}
				Game_Over();
			}

		}, 10, 10);
	}

	public static void main(String[] args) {
		// ����һ���������
		Shoot s = new Shoot();
		// �������������
		JFrame j = new JFrame();
		// ��������ӵ�������ȥ
		j.add(s);
		j.setTitle("�����Ϸ"); // ��������
		j.setSize(WIDTH, HEIGHT); // ���ô�С
		j.setLocationRelativeTo(null); // �����м���ʾ
		// �����洰�ڵĹرն��ر�
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true); // ���ÿɼ���
		s.action();
	}
}
