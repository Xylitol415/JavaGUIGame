package godhands;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import MyShoot.FlyingObject;


public class GodHands extends JPanel  implements KeyListener{
	static final int WIDTH =591;
	static final int HEIGHT = 1067;
	
	static BufferedImage bk1;
	static BufferedImage bk2;
	static BufferedImage[] shitou = new BufferedImage[2];
	static BufferedImage[] shou = new BufferedImage[4];
	static BufferedImage[] st = new BufferedImage[3];
	static BufferedImage dfb;
	static BufferedImage start;
	static BufferedImage pause;
	static BufferedImage game_over;
	
	
	// 状态
	static final int START = 0;
	static final int RUNNING = 1;
	static final int PAUSE = 2;
	static final int GAME_OVER = 3;
	
	int state = 0;
	static int speed=0;

	Shou s = new Shou();
	static Shitou stt1;
	static Shitou stt2;
	St[] std ={};
	int[] x ={0,150,292,435};
	int[] type = {};
	
	 //静态代码块赋值
	 static{
		try {
			bk1 = ImageIO.read(GodHands.class.getResource("background.png"));
			bk2 = ImageIO.read(GodHands.class.getResource("background.png"));
			dfb = ImageIO.read(GodHands.class.getResource("defenban.png"));
			
			start = ImageIO.read(GodHands.class.getResource("start.png"));
			pause = ImageIO.read(GodHands.class.getResource("pause.png"));
			game_over = ImageIO.read(GodHands.class.getResource("game_over.png"));

			for(int i=0;i<shitou.length;i++){
				int j;
				j=i+1;
				shitou[i] = ImageIO.read(GodHands.class.getResource("shitou"+j+".png"));
			}
			
			for(int i=0;i<shou.length;i++){
				int j;
				j=i+1;
				shou[i] = ImageIO.read(GodHands.class.getResource("shou"+j+".png"));
			}
			
			for(int i=0;i<st.length;i++){
				int j;
				j=i+1;
				st[i] = ImageIO.read(GodHands.class.getResource("st"+j+".png"));
			}
			stt1 = new Shitou();
			stt2 = new Shitou();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	 }
	 public GodHands(){
		 this.addKeyListener(this);
		 this.setFocusable(true);
		 //this.addMouseListener(this);
	 }
	 int y1 = 0;
	 int y2 = HEIGHT;
		
		//绘制背景
		 public void paintBackground(Graphics g){
			g.drawImage(bk1,0,y1+=3,null);
			g.drawImage(bk2,0,y2+=3,null);
			//两张图片同时滚动
			if(y1 >= HEIGHT){y1= -HEIGHT;}
			if(y2 >= HEIGHT){y2 = -HEIGHT;}
			g.drawImage(dfb, WIDTH/2-dfb.getWidth()/2, 0, null);
			g.setColor(Color.CYAN);
			g.setFont(new Font("楷体", Font.BOLD, 25));
			g.drawString(stt1.score+"分", 270, 50);
			 
		 }
		 //绘制手
		 public void paintShou(Graphics g){
			 g.drawImage(s.images[0], s.x, s.y,null);
			 g.drawImage(s.images[1], s.x+s.width+5, s.y,null);
			 
		 }
		 //绘制石头
		 public void paintShitou(Graphics g){
			 g.drawImage(stt1.images[0], stt1.x, stt1.y,null);
			 g.drawImage(stt2.images[0], stt2.x+143, stt2.y,null);
		 }
		 
		 public void paintSt(Graphics g){
			 for(int i=0;i<std.length;i++){
				 g.drawImage(std[i].images[type[i]], std[i].x, std[i].y,null);
			 }
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
				g.drawImage(dfb, WIDTH/2-dfb.getWidth()/2, 0, null);
				g.setColor(Color.CYAN);
				g.setFont(new Font("楷体", Font.BOLD, 25));
				g.drawString(stt1.score+"分", 270, 50);
			}
		}

			
		 //绘制函数
		 public void paint(Graphics g){
			 super.paint(g);
			 paintBackground(g);
			 paintSt(g);
			 paintShitou(g);
			 paintShou(g);
			 paintState(g);
		 }
		 //
		 public void stepAction(){
			 for(int i=0;i<std.length;i++){
				 std[i].step();
			 }
		 }
		 //
		 int enterindex= 0;
		 public void enterAction(){
			 enterindex++;
			 if(enterindex%60==0){
				 std = Arrays.copyOf(std, std.length+1);
				 std[std.length-1]= new St();
				 
				 int i;
				 i =(int)(Math.random()*4);
				 std[std.length-1].x = x[i];
				 std[std.length-1].y-=-120;
				 
				 i =(int)(Math.random()*3);
				 type = Arrays.copyOf(type,type.length+1);
				 type[type.length-1] = i;
//				 if(std.length>=2){
//					 for(int j =0;j<std.length-1;j++){
//						 if((std[std.length-1].x+143 == std[j].x && std[std.length-1].x == x[0] || std[std.length-1].x == x[2])||(std[std.length-1].x-143 == std[j].x)&&std[std.length-1].x == x[1] || std[std.length-1].x == x[3]){
//							 if(std[std.length-1].y<=std[j].y-110){
//							 std= Arrays.copyOf(std, std.length-1);
//							 type = Arrays.copyOf(type, type.length-1);}
//						 }
//					 }
//					 
//				 }
			}
			 
		 }
		 // 碰撞
		 public void bangAction() {
			 for(int i = 0; i < std.length; i++){
				 if(stt1.bang1(std[i].x, std[i].y, std[i].width, std[i].height)){
//					 System.out.println("左游戏结束！");
//					 System.out.println("左std["+i+"].x ..." + std[i].x);
//					 System.out.println("stt1.x..." + stt1.x);
					 std[i].x = 42;
					 state = GAME_OVER;
				 } else {
					 if(((stt1.y - std[i].y)>=0&&(stt1.y - std[i].y)<=2)){
						 stt1.score += 1;
					 }
				 }
			 }
			 for(int i = 0; i < std.length; i++){
				 if(stt2.bang2(std[i].x, std[i].y, std[i].width, std[i].height)){
//					 System.out.println("右游戏结束！");
//					 System.out.println("右std["+i+"].x ..." + std[i].x);
//					 System.out.println("stt2.x..." + stt2.x);
					 std[i].x = 42;
					 state = GAME_OVER;
				 } 
//				 else {
//					 if(((stt2.y - std[i].y)>=0&&(stt2.y - std[i].y)<=2)){
//						 stt1.score += 1;
//					 }
//				 }
			 }
		 }
		// 越界
		public void outOfBoundAction(){
			int index = 0;
			// 保留未越界石头
			St[] stdLive = new St[std.length];
			int[] typelive = new int[type.length];
			for(int i = 0; i <std.length; i++) {
				if(!std[i].outOfBound()) {
					stdLive[index] = std[i];
					typelive[index] = type[i];
					index++;	
				}
			}
			std = Arrays.copyOf(stdLive, index);
			type = Arrays.copyOf(typelive,index);
		}
		 
		 //
		 public void action(){
			// 鼠标控制事件
			MouseAdapter l = new MouseAdapter() {
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
						stt1.score = 0;
					}
				}
			};
			this.addMouseListener(l); // 添加事件
			this.addMouseMotionListener(l);
			 // 定时器
			 Timer timer = new Timer();
			 timer.schedule(new TimerTask(){
				 public void run(){
					 repaint();
					 if(state == RUNNING) {						 
						 enterAction();
						 stepAction();
						 bangAction();
						outOfBoundAction();
					 }
				}
			 },10,10);
		}
		 public static void main(String[] args) {
			GodHands g =  new GodHands();
			JFrame j =new JFrame();
			j.add(g);
			j.setTitle("God Hands");//设置主题
			j.setSize(WIDTH, HEIGHT);//设置大小
			j.setLocationRelativeTo(null);//窗口中间显示
			j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//程序随窗口的关闭而关闭
			j.setVisible(true);
			g.action();

	}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				System.out.println("123左");
				stt1.x =42;
				
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				stt2.x=335 ;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				System.out.println("123右");
				stt1.x =185;
				
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				stt2.x=185 ;
			}	
				
		}
}
