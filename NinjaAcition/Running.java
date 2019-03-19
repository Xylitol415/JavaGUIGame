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
	
	// 背景图片属性
	static BufferedImage bk;
	static BufferedImage bk1;
	static BufferedImage nl1;
	static BufferedImage nl2;
	static BufferedImage nr1;
	static BufferedImage nr2;
	
	static boolean changeDir = false;
	
	Ninja n = new Ninja(); // 忍者对象
//	Barrier b = new Barrier(); // 障碍物对象
	Barrier[] bls = {}; // 左侧障碍物数组
	Barrier[] brs = {}; // 右侧障碍物数组
	// 静态代码块
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
	
	int enterIndex = 0; // 控制障碍物数量
	public void enterAction() { 
		enterIndex++;
		if(enterIndex % 80 == 0) {
			bls = Arrays.copyOf(bls, bls.length+1);
			bls[bls.length-1] = nextOne();
			brs = Arrays.copyOf(brs, brs.length+1);
			brs[brs.length-1] = nextOne();
		}
	}
	
	// 画背景
//	int a = 0;
	public void paintBackGround(Graphics g) {
		g.drawImage(bk, 0, 0, null);
//		g.drawImage(bk, 0, a++, null);
//		g.drawImage(bk1, 0, -HEIGHT + a, null);
//		if (a >= HEIGHT) {
//			a = 0;
//		}
	}
	// 画忍者
	public void paintNinja(Graphics g) {
		g.drawImage(n.image, n.x, n.y, null);
	}
	// 画障碍物
	public void paintBarrier(Graphics g) {
		//g.fillRect(b.x, b.y, b.width, b.height);
		// 填充左侧障碍物
		for(int i = 0; i < bls.length; i++) {
			g.setColor(new Color(70, 80, 67));
			g.fillRect(bls[i].x, bls[i].y, bls[i].width, bls[i].height);	
			g.setColor(new Color(255, 38, 38));
			float lineWidth = 5.0f;
			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));
			g.drawRect(bls[i].x, bls[i].y, bls[i].width, bls[i].height);
		}
		
		// 填充右侧障碍物
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
		paintBackGround(g); // 画背景
		paintBarrier(g); // 画障碍物
		paintNinja(g); // 画忍者
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
	
	// 碰撞
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
	// 障碍物越界
	public void outOfBoundAction() {
		int index = 0;
		// 左侧
		Barrier[] barrierLeftLive = new Barrier[bls.length];
		for(int i = 0; i <bls.length; i++) {
			if(!bls[i].outOfBound()) {
				barrierLeftLive[index] = bls[i];
				index++;	
			}
		}
		bls = Arrays.copyOf(barrierLeftLive, index);
		// 右侧
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
		// 鼠标控制事件
			MouseAdapter l = new MouseAdapter() {
				// 鼠标单击
				public void mouseClicked(MouseEvent e) {
					switch(n.direction) {
					case 0:
						// 单击后更改方向
						n.direction = 1;
						changeDir = true;
						// 到障碍物边界单击后调回原速度
						n.xspeed = 3;
						break;
					case 1:
						n.direction = 0;
						changeDir = true;
						n.xspeed = 3;
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
					enterAction(); // 障碍物出现
					stepAction(); // 走步
					collisionAction(); // 碰撞
					continuousAction();
					//heigherThanBarrierAction();
					//outOfBoundAction();
				}
			}, 10, 10);
	}
	
	
	public static void main(String[] args) {
		// 创建一个画板对象
		Running r = new Running();
		// 创建窗口类对象
		JFrame j = new JFrame();
		// 将画板添加到窗口上去
		j.add(r);
		j.setTitle("狂奔的忍者"); // 设置主题
		j.setSize(WIDTH, HEIGHT); // 设置大小
		j.setLocationRelativeTo(null); // 窗口中间显示
		// 程序随窗口的关闭而关闭
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true); // 设置可见性
		r.action();
	}
}
