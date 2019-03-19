package flappy_bird;

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

public class FlappyBird extends JPanel{
	//设置宽度
	static final int WIDTH = 800;
	//设置高度
	static final int HEIGHT = 600;
	
	//设置背景图片1,2
	 static BufferedImage sky1;
	 static BufferedImage sky2;
	 //设置陆地图片1,2
	 static BufferedImage land1;
	 static BufferedImage land2;//两张图片方便屏幕滚动
	 //下管道图片
	 static BufferedImage pipe1;
	 //上管道图片
	 static BufferedImage pipe2;
	 //鸟儿图片集
	 static BufferedImage[] birds = new BufferedImage[3];
	 
	 Birds b = new Birds();//创建鸟类对象
	 Pipe[] pup ={};//创建管道数组
	 Pipe[] pdown ={};
	 int speed=0;//鸟儿运动速度
	 int[] by = {};//空隙y坐标
	 int[] bankwidth ={};//空隙的宽度
	 int state =0;//状态
	 /*
	  * state = 0  为开始状态
	  * state = 1 为运行状态
	  * state = 2 为游戏结束状态
	  */
	 int score =0;//得分
	 
	 
	 //静态代码块赋值
	 static{
		try {
			sky1 = ImageIO.read(FlappyBird.class.getResource("sky.png"));
			sky2 = ImageIO.read(FlappyBird.class.getResource("sky.png"));
			land1 = ImageIO.read(FlappyBird.class.getResource("land.png"));
			land2 = ImageIO.read(FlappyBird.class.getResource("land.png"));
			pipe1 = ImageIO.read(FlappyBird.class.getResource("pipe1.png"));
			pipe2 = ImageIO.read(FlappyBird.class.getResource("pipe2.png"));
			for(int i=0;i<birds.length;i++){//数组图片集赋值
				int j;
				j=i+1;
				birds[i] = ImageIO.read(FlappyBird.class.getResource("birds"+j+".png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	 }
	int x1 = 0;
	int x2 = WIDTH;
	
	//绘制背景
	 public void paintBackground(Graphics g){
		 //天空绘制
		 g.drawImage(sky1,x1--,0,null);
		 g.drawImage(sky2,x2--,0,null);
		 //陆地绘制
		 g.drawImage(land1,x1--,488,null);
		 g.drawImage(land2,x2--,488,null);
		 //两张图片同时滚动
		 if(x1 <= -800){x1= WIDTH;}
		 if(x2 <= -800){x2 = WIDTH;}
		 
	 }
	 
	 //绘制上下管道
	 public void paintPipe(Graphics g){
		 for(int i=0;i<pup.length;i++){
			 /*
			  * by[i]+bankwidth[i]  下管道的Y坐标  
			  * by[i]-pdown[i].height   上管道的Y坐标
			  */
			 g.drawImage(pup[i].image1, pup[i].x1--,by[i]+bankwidth[i], null);
			 g.drawImage(pdown[i].image2,pdown[i].x1--,by[i]-pdown[i].height, null);
		 }
	 }
	 
	 //绘制bird
	 public void paintBirds(Graphics g){
		 g.drawImage(b.image, b.x, b.y, null);
	 }
	 
	 //绘画状态
	 public void paintState(Graphics g){
		 g.setColor(Color.red);
		 
		 if(state == 0){
			 g.setFont(new Font("黑体",Font.BOLD,80));
			 g.drawString("开始游戏", 240, 280);
			 g.setFont(new Font("黑体",Font.BOLD,20));
			 g.drawString("点击任意位置开始", 20, 540);
			 g.drawString("长按鼠标，鸟儿往上飞", 550, 540);
			 
		 }
		 if(state == 1){
			 g.setFont(new Font("黑体",Font.BOLD,20));
			 g.drawString("score:"+score, 650, 50);
			 }
		 if(state == 2){
			 g.setFont(new Font("黑体",Font.BOLD,80));
			 g.drawString("GAME OVER", 240, 280);
			 g.setFont(new Font("黑体",Font.BOLD,20));
			 g.drawString("点击任意位置重新开始!", 300, 540);
		 }
	 }
	 //绘制函数
	 public void paint(Graphics g){
		 super.paint(g);
		 paintBackground(g);
		 paintBirds(g);
		 paintPipe(g);
		 paintState(g);
		 
	 }
	//走步
	 public void StepAction(){
		 b.step();
	 }
	 //生成管道
	 int enterindex = 0;
	 public void enterPipe(){
		 enterindex++;
		 if(enterindex%200 == 0){
			 /*
			  * 生成一个上管道的值
			  * 一个下管道的值
			  * 一个空隙Y坐标的值
			  * 一个空隙的宽度的值
			  * 
			  * 都使用了数组的扩容
			  */
			 pup = Arrays.copyOf(pup,pup.length+1);
			 pup[pup.length-1] = new Pipe();
			 
			 pdown = Arrays.copyOf(pdown, pdown.length+1);
			 pdown[pdown.length-1] = new Pipe();
			 
			 by = Arrays.copyOf(by, by.length+1);
			 by[by.length-1] = (int)(Math.random()*180+240);
			 
			 bankwidth =Arrays.copyOf(bankwidth, bankwidth.length+1);
			 bankwidth[bankwidth.length-1] = (int)(Math.random()*20+70);
		 }
		 
	 }
	 //越界判断
	 public void outOfBoundAction(){
		 if(b.outOfBound()){//鸟儿越界判断
			 state =2;
		 }
		 for(int i =0;i<pup.length;i++){
			 if(pup[i].outOfBound()){
				 /*
				  * 判断管道越界
				  * 因为上下管道的X的值一样
				  * 所以只判断一种管道的越界
				  * 如果越界
				  * 同样下标的上下管道和空隙以及空隙的宽度清楚
				  * 
				  * 使用的数组的缩容
				  */
				 pup[i] = pup[pup.length-1];
				 pup = Arrays.copyOf(pup,pup.length-1);
				 
				 pdown[i] = pdown[pdown.length-1];
				 pdown = Arrays.copyOf(pdown, pdown.length-1);
				 
				 by[i] = by[by.length-1];
				 by = Arrays.copyOf(by, by.length-1);
				 
				 bankwidth[i] = bankwidth[bankwidth.length-1];
				 bankwidth =Arrays.copyOf(bankwidth,bankwidth.length-1);
			 }
		 }
	 }
	 // 碰撞
	 public void bangaction(){
		 /*
		  * 逻辑最强部分
		  * 
		  * 主要思路： 鸟儿和管道碰撞，游戏结束
		  * 碰撞有3种情况 1：鸟儿的X+宽度和管道的X接触，且鸟儿的Y不在空隙里面  就是发生碰撞
		  *:			  2;鸟儿的上边界和上管道的下边界
		  *				  3;鸟儿的下边界和下管道上边界
		  *
		  *实现方案：鸟儿的X固定值为180；所以管道的左右边界在180的两边，然后判断鸟儿Y是否在空隙内
		  *如果在  则state =1
		  *不在  state=2
		  *
		  *得分：设置一个X的临界点 175；如果管道的边界等于175 且鸟的Y在空隙内，则表示通过，加5分；
		  * 
		  */
		 for(int i=0;i<pup.length;i++){
			 if( pup[i].x1<=180 && pup[i].x1+pup[i].width>=180 ){
				 if(( b.y+5 <= by[i] && b.y >=0)||(b.y+40>= by[i]+bankwidth[i] && b.y<=HEIGHT)) {
				 
					 state =2;
					 }
				 else{
					 state = 1;
				 }
			 }
			 if(pup[i].x1+pup[i].width==175 && b.x>=pup[i].x1+pup[i].width && b.x<=pup[i].x1+pup[i].width+100){
				 score+=5;
			 }
		 } 
	 }
	 //游戏结束
	 public void GameOver(){
		 if(state == 2){
			 //数组的清空
			 pup =Arrays.copyOf(pup,0);
			 pdown = Arrays.copyOf(pdown,0);
			 by = Arrays.copyOf(by,0);
			 bankwidth = Arrays.copyOf(bankwidth,0);
			 
		 }
	 }
	 
	 //action函数
	 public void action(){
		 //鼠标监听
		 MouseAdapter l =new MouseAdapter(){
			 //长按或者按都执行
			 public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
						speed=2;
						
				}
			 //释放执行
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
						speed=0;
					}
				//点击执行  state = 0 或 2时才有用
				//state =0  执行后state = 1
				//state =2 执行后state = 0 score = 0 
				public void mouseClicked(MouseEvent e){
					switch(state){
					case 0:
						state = 1;
						break;
					case 2:
						state = 0;
						score = 0;
					
					}
				}
			};
		this.addMouseListener(l);//添加鼠标监听事件
		this.addMouseMotionListener(l);
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			 public void run(){
				 repaint();
				 if(state ==1){
					 bangaction();//碰撞
					 b.moveTo(speed);//鸟儿移动
					 enterPipe();//管道生成
					 StepAction();//走步
					 outOfBoundAction();//越界
					 }
				 GameOver();//游戏结束
				 }
		 },10,10);
	 }
	public static void main(String[] args){
		FlappyBird f =  new FlappyBird();
		
		JFrame j =new JFrame();
		j.add(f);
		j.setTitle("Flappy Bird");//设置主题
		j.setSize(WIDTH, HEIGHT);//设置大小
		j.setLocationRelativeTo(null);//窗口中间显示
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//程序随窗口的关闭而关闭
		j.setVisible(true);//设置可见性
		f.action();
	}
}

