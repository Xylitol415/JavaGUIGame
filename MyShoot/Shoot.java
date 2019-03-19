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
 *  当前类是画板类
 */
import javax.swing.event.MenuDragMouseEvent;

public class Shoot extends JPanel {
	static final int WIDTH = 512; // 窗口的宽
	static final int HEIGHT = 768; // 窗口的高
	/*
	 * static修饰的属性和行为都是静态的 一般我们加载资源的时候会将资源的属性定义为static， 因为静态的属性在内存空间中只有一份
	 * 
	 * 静态的属性是属于类的，不是属于对象的
	 */
	static BufferedImage bk; // 背景图片属性
	static BufferedImage bk1;

	// 英雄机图片属性
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

	// 敌机图片属性
	static BufferedImage a0;
	static BufferedImage a1;
	static BufferedImage a2;
	static BufferedImage a3;
	static BufferedImage a4;
	static BufferedImage a5;

	// 爱心图片属性
	static BufferedImage q0;
	static BufferedImage q1;
	static BufferedImage q2;
	static BufferedImage q3;
	static BufferedImage q4;
	static BufferedImage q5;
	static BufferedImage q6;
	static BufferedImage q7;
	static BufferedImage q8;

	// 子弹图片属性
	static BufferedImage bullet;

	// 状态图片属性
	static BufferedImage start;
	static BufferedImage pause;
	static BufferedImage game_over;

	// boss机图片属性
	static BufferedImage b0;
	static BufferedImage b1;
	static BufferedImage b2;
	static BufferedImage b3;

	// boss机子弹
	static BufferedImage bbu0;
	static BufferedImage bbu1;

	// 状态
	static final int START = 0;
	static final int RUNNING = 1;
	static final int PAUSE = 2;
	static final int GAME_OVER = 3;
	int state = 0;
	
	FlyingObject[] flying = {};

	Hero h = new Hero(); // 英雄机对象
	// AirPlane ap = new AirPlane(); // 小敌机对象
	// 小敌机数组
//	AirPlane[] aps = {};
	// Heart ht = new Heart(); // 小爱心对象
	// 爱心数组
//	Heart[] hts = {};
	// 子弹数组
	Bullet[] bs = {};
	// boss机数组
	Boss[] bos = {};
	BossBullet[] bbs = {};

	// 静态代码块
	static {
		try {
			// 加载背景图片
			bk = ImageIO.read(Shoot.class.getResource("background.jpg"));
			bk1 = ImageIO.read(Shoot.class.getResource("background.jpg"));
			// 加载英雄机图片
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
			// 加载敌机图片
			a0 = ImageIO.read(Shoot.class.getResource("flys0.png"));
			a1 = ImageIO.read(Shoot.class.getResource("flys1.png"));
			a2 = ImageIO.read(Shoot.class.getResource("flys2.png"));
			a3 = ImageIO.read(Shoot.class.getResource("flys3.png"));
			a4 = ImageIO.read(Shoot.class.getResource("flys4.png"));
			a5 = ImageIO.read(Shoot.class.getResource("flys5.png"));
			// 加载爱心图片
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
			// 加载状态图片
			start = ImageIO.read(Shoot.class.getResource("start.jpg"));
			pause = ImageIO.read(Shoot.class.getResource("zan.jpg"));
			game_over = ImageIO.read(Shoot.class.getResource("over.jpg"));
			// 加载boss机图片
			b0 = ImageIO.read(Shoot.class.getResource("boss0.png"));
			b1 = ImageIO.read(Shoot.class.getResource("boss1.png"));
			b2 = ImageIO.read(Shoot.class.getResource("boss2.PNG"));
			b3 = ImageIO.read(Shoot.class.getResource("boss3.PNG"));
			// 加载boss子弹图片
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

//	// 生成小敌机
//	public AirPlane nextOne() {
//		return new AirPlane();
//	}

//	// 生成爱心对象
//	public Heart nextOne1() {
//		return new Heart();
//	}

	// // 生成boss机对象
	// public Boss nextOne2() {
	// return new Boss();
	// }
	// 飞行物登场
	int enterIndex = 0; // 控制小敌机数量

	public void enterAction() { // 10毫秒 --> 100毫秒
		enterIndex++;
		if(enterIndex % 40 == 0) {
			flying = Arrays.copyOf(flying, flying.length+1);
			flying[flying.length-1] = nextOne();
		}
//		if (enterIndex % 40 == 0) {// 小敌机登场
//			// 数组的扩容
//			aps = Arrays.copyOf(aps, aps.length + 1);
//			aps[aps.length - 1] = nextOne();
//		}
//
//		if (enterIndex % 100 == 0) { // 爱心登场
//			// 数组的扩容
//			hts = Arrays.copyOf(hts, hts.length + 1);
//			hts[hts.length - 1] = nextOne1();
//		}
//		// if(enterIndex % 150 == 0) { // boss机登场
//		// bos = Arrays.copyOf(bos, bos.length+1);
//		// bos[bos.length-1] = nextOne2();
//		// }
		// boss机登场
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

	// 画背景
	int a = 0;

	public void paintBackGround(Graphics g) {
		g.drawImage(bk, 0, a++, null);
		g.drawImage(bk1, 0, -HEIGHT + a, null);
		if (a >= HEIGHT) {
			a = 0;
		}
	}

	// 画英雄机
	public void paintHero(Graphics g) {
		g.drawImage(h.image, h.x, h.y, null);
	}
	
/*
	// 画小敌机
	public void paintAirPlane(Graphics g) {
		// g.drawImage(ap.image, ap.x, ap.y, null);
		for (int i = 0; i < aps.length; i++) {
			g.drawImage(aps[i].image, aps[i].x, aps[i].y, null);
		}
	}

	// 画爱心
	public void paintHeart(Graphics g) {
		// g.drawImage(ht.image, ht.x, ht.y, null);
		for (int i = 0; i < hts.length; i++) {
			g.drawImage(hts[i].image, hts[i].x, hts[i].y, null);
		}
	}
*/
	// 画敌人
	public void paintFlyingObject(Graphics g) {
		for(int i = 0; i < flying.length; i++) {
			g.drawImage(flying[i].image, flying[i].x, flying[i].y, null);
		}
	}
	// 画子弹
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bs.length; i++) {
			g.drawImage(bs[i].image, bs[i].x, bs[i].y, null);
			// 画两颗子弹
			// g.drawImage(bs[i].image, bs[i].x+4*bs[i].width, bs[i].y, null);
		}
	}

	// 画生命值
	public void paintLife(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("宋体", Font.BOLD, 20));
		g.drawString("生命值:" + h.life, 380, 50);
	}

	// 画状态
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

	// 画boss机
	public void paintBoss(Graphics g) {
		for (int i = 0; i < bos.length; i++) {
			g.drawImage(bos[i].image, bos[i].x, bos[i].y, null);
		}
	}

	// 画boss机子弹
	public void paintBossBullet(Graphics g) {
		for (int i = 0; i < bbs.length; i++) {
			g.drawImage(bbs[i].image, bbs[i].x, bbs[i].y, null);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		paintBackGround(g); // 画背景
		paintHero(g); // 画英雄机
		
//		paintAirPlane(g); // 画小敌机
//		paintHeart(g); // 画爱心
		paintFlyingObject(g); // 画敌人
		
		paintBullets(g); // 画子弹
		paintLife(g); // 画生命值
		paintBoss(g); // 画boss机
		paintBossBullet(g); // 画boss机子弹
		paintState(g); // 画状态
	}

	// 飞行物走步
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
		// 敌人走步
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

	// 发射子弹
	int shootIndex = 0; // 控制子弹的数量

	public void ShootAction() {
		shootIndex++;
		if (shootIndex % 30 == 0) {
			Bullet[] bs1 = h.shootBy();
			bs = Arrays.copyOf(bs, bs.length + bs1.length);
			// 数组的复制
			System.arraycopy(bs1, 0, bs, bs.length - bs1.length, bs1.length);
		}
		// boss机发射子弹
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

	// 碰撞
	public void bangAction() {
		// 敌人和子弹碰撞
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
		// 英雄机与小敌机碰撞
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

//		// 子弹和小敌机碰撞
//		for (int i = 0; i < bs.length; i++) {
//			for (int j = 0; j < aps.length; j++) {
//				Bullet b = bs[i];
//				AirPlane a = aps[j];
//				if (b.bang(a.x, a.y, a.width, a.height)) {
//					// 数组的缩容
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
//		// 子弹和爱心的碰撞
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
//		// 英雄机和小敌机碰撞
//		for (int i = 0; i < aps.length; i++) {
//			AirPlane a = aps[i];
//			if (h.bang(a.x, a.y, a.width, a.height)) {
//				aps[i] = aps[aps.length - 1];
//				aps = Arrays.copyOf(aps, aps.length - 1);
//				h.life--;	
//			// break: 跳出当前循环， 如果有两个循环，跳出内循环 如果只有一个循环，则跳出当前循环
//			// break; // 跳出循环
//			}
//		}

		// 子弹和boss机的碰撞
		for (int i = 0; i < bos.length; i++) {
			for (int j = 0; j < bbs.length; j++) {
				if (bs[j].bang(bos[i].x, bos[i].y, bos[i].image.getWidth(), bos[i].image.getHeight())) {
					if (!bos[i].UP && bos[i].y == 0) {
						bos[i].life--;
					}
					// System.out.println("bossLife: "+ bos[i].life);
					if (bos[i].life <= 0) {
						bos = Arrays.copyOf(bos, 0);
						// bbs = Arrays.copyOf(bbs, 0); // boss机对象删除后屏幕上的子弹还在
					}
					bs[j] = bs[bs.length - 1];
					bs = Arrays.copyOf(bs, bs.length - 1);
				}
				break;
			}
		}
		// boss机子弹和英雄机碰撞
		for (int i = 0; i < bbs.length; i++) {
			if (bbs[i].bang(h.x, h.y, h.image.getWidth(), h.image.getWidth())) {
				h.life--;
				bbs[i] = bbs[bbs.length - 1];
				bbs = Arrays.copyOf(bbs, bbs.length - 1);
			}

		}
	}

	// 越界处理
	public void outOfBoundAction() {
		int index = 0;
		// 敌人越界
		FlyingObject[] flyingLive = new FlyingObject[flying.length];
		for(int i = 0; i <flying.length; i++) {
			if(!flying[i].outOfBound()) {
				flyingLive[index] = flying[i];
				index++;	
			}
		}
		flying = Arrays.copyOf(flyingLive, index);
//		// 小敌机越界
//		AirPlane[] apsLive = new AirPlane[aps.length];
//		for (int i = 0; i < aps.length; i++) {
//			if (!aps[i].outOfBound()) {
//				apsLive[index] = aps[i];
//				index++;
//			}
//		}
//		aps = Arrays.copyOf(apsLive, index);
//
//		// 爱心越界
//		index = 0;
//		Heart[] htsLive = new Heart[hts.length];
//		for (int i = 0; i < hts.length; i++) {
//			if (!hts[i].outOfBound()) {
//				htsLive[index] = hts[i];
//				index++;
//			}
//		}
//		hts = Arrays.copyOf(htsLive, index);

		// 子弹越界
		index = 0;
		Bullet[] bsLive = new Bullet[bs.length];
		for (int i = 0; i < bs.length; i++) {
			if (!bs[i].OutOfBound()) {
				bsLive[index] = bs[i];
				index++;
			}
		}
		bs = Arrays.copyOf(bsLive, index);
		
		// boss机子弹越界
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

	// 游戏结束
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
		// 鼠标控制事件
		MouseAdapter l = new MouseAdapter() {
			// 鼠标移动事件
			public void mouseMoved(MouseEvent e) {
				// h.x = e.getX();
				// h.y = e.getY();
				h.moveTo(e.getX(), e.getY());
			}

			// 鼠标单击
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
		this.addMouseListener(l); // 添加事件
		this.addMouseMotionListener(l);
		// 定时器
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				repaint(); // 重绘
				// TODO Auto-generated method stub
				if (state == RUNNING) {
					enterAction(); // 飞行物登场
					stepAction(); // 走步
					ShootAction(); // 发射子弹
					bangAction(); // 碰撞事件
					outOfBoundAction();
				}
				Game_Over();
			}

		}, 10, 10);
	}

	public static void main(String[] args) {
		// 创建一个画板对象
		Shoot s = new Shoot();
		// 创建窗口类对象
		JFrame j = new JFrame();
		// 将画板添加到窗口上去
		j.add(s);
		j.setTitle("射击游戏"); // 设置主题
		j.setSize(WIDTH, HEIGHT); // 设置大小
		j.setLocationRelativeTo(null); // 窗口中间显示
		// 程序随窗口的关闭而关闭
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true); // 设置可见性
		s.action();
	}
}
