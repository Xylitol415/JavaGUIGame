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
	//���ÿ��
	static final int WIDTH = 800;
	//���ø߶�
	static final int HEIGHT = 600;
	
	//���ñ���ͼƬ1,2
	 static BufferedImage sky1;
	 static BufferedImage sky2;
	 //����½��ͼƬ1,2
	 static BufferedImage land1;
	 static BufferedImage land2;//����ͼƬ������Ļ����
	 //�¹ܵ�ͼƬ
	 static BufferedImage pipe1;
	 //�Ϲܵ�ͼƬ
	 static BufferedImage pipe2;
	 //���ͼƬ��
	 static BufferedImage[] birds = new BufferedImage[3];
	 
	 Birds b = new Birds();//�����������
	 Pipe[] pup ={};//�����ܵ�����
	 Pipe[] pdown ={};
	 int speed=0;//����˶��ٶ�
	 int[] by = {};//��϶y����
	 int[] bankwidth ={};//��϶�Ŀ��
	 int state =0;//״̬
	 /*
	  * state = 0  Ϊ��ʼ״̬
	  * state = 1 Ϊ����״̬
	  * state = 2 Ϊ��Ϸ����״̬
	  */
	 int score =0;//�÷�
	 
	 
	 //��̬����鸳ֵ
	 static{
		try {
			sky1 = ImageIO.read(FlappyBird.class.getResource("sky.png"));
			sky2 = ImageIO.read(FlappyBird.class.getResource("sky.png"));
			land1 = ImageIO.read(FlappyBird.class.getResource("land.png"));
			land2 = ImageIO.read(FlappyBird.class.getResource("land.png"));
			pipe1 = ImageIO.read(FlappyBird.class.getResource("pipe1.png"));
			pipe2 = ImageIO.read(FlappyBird.class.getResource("pipe2.png"));
			for(int i=0;i<birds.length;i++){//����ͼƬ����ֵ
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
	
	//���Ʊ���
	 public void paintBackground(Graphics g){
		 //��ջ���
		 g.drawImage(sky1,x1--,0,null);
		 g.drawImage(sky2,x2--,0,null);
		 //½�ػ���
		 g.drawImage(land1,x1--,488,null);
		 g.drawImage(land2,x2--,488,null);
		 //����ͼƬͬʱ����
		 if(x1 <= -800){x1= WIDTH;}
		 if(x2 <= -800){x2 = WIDTH;}
		 
	 }
	 
	 //�������¹ܵ�
	 public void paintPipe(Graphics g){
		 for(int i=0;i<pup.length;i++){
			 /*
			  * by[i]+bankwidth[i]  �¹ܵ���Y����  
			  * by[i]-pdown[i].height   �Ϲܵ���Y����
			  */
			 g.drawImage(pup[i].image1, pup[i].x1--,by[i]+bankwidth[i], null);
			 g.drawImage(pdown[i].image2,pdown[i].x1--,by[i]-pdown[i].height, null);
		 }
	 }
	 
	 //����bird
	 public void paintBirds(Graphics g){
		 g.drawImage(b.image, b.x, b.y, null);
	 }
	 
	 //�滭״̬
	 public void paintState(Graphics g){
		 g.setColor(Color.red);
		 
		 if(state == 0){
			 g.setFont(new Font("����",Font.BOLD,80));
			 g.drawString("��ʼ��Ϸ", 240, 280);
			 g.setFont(new Font("����",Font.BOLD,20));
			 g.drawString("�������λ�ÿ�ʼ", 20, 540);
			 g.drawString("������꣬������Ϸ�", 550, 540);
			 
		 }
		 if(state == 1){
			 g.setFont(new Font("����",Font.BOLD,20));
			 g.drawString("score:"+score, 650, 50);
			 }
		 if(state == 2){
			 g.setFont(new Font("����",Font.BOLD,80));
			 g.drawString("GAME OVER", 240, 280);
			 g.setFont(new Font("����",Font.BOLD,20));
			 g.drawString("�������λ�����¿�ʼ!", 300, 540);
		 }
	 }
	 //���ƺ���
	 public void paint(Graphics g){
		 super.paint(g);
		 paintBackground(g);
		 paintBirds(g);
		 paintPipe(g);
		 paintState(g);
		 
	 }
	//�߲�
	 public void StepAction(){
		 b.step();
	 }
	 //���ɹܵ�
	 int enterindex = 0;
	 public void enterPipe(){
		 enterindex++;
		 if(enterindex%200 == 0){
			 /*
			  * ����һ���Ϲܵ���ֵ
			  * һ���¹ܵ���ֵ
			  * һ����϶Y�����ֵ
			  * һ����϶�Ŀ�ȵ�ֵ
			  * 
			  * ��ʹ�������������
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
	 //Խ���ж�
	 public void outOfBoundAction(){
		 if(b.outOfBound()){//���Խ���ж�
			 state =2;
		 }
		 for(int i =0;i<pup.length;i++){
			 if(pup[i].outOfBound()){
				 /*
				  * �жϹܵ�Խ��
				  * ��Ϊ���¹ܵ���X��ֵһ��
				  * ����ֻ�ж�һ�ֹܵ���Խ��
				  * ���Խ��
				  * ͬ���±�����¹ܵ��Ϳ�϶�Լ���϶�Ŀ�����
				  * 
				  * ʹ�õ����������
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
	 // ��ײ
	 public void bangaction(){
		 /*
		  * �߼���ǿ����
		  * 
		  * ��Ҫ˼·�� ����͹ܵ���ײ����Ϸ����
		  * ��ײ��3����� 1�������X+��Ⱥ͹ܵ���X�Ӵ����������Y���ڿ�϶����  ���Ƿ�����ײ
		  *:			  2;������ϱ߽���Ϲܵ����±߽�
		  *				  3;������±߽���¹ܵ��ϱ߽�
		  *
		  *ʵ�ַ����������X�̶�ֵΪ180�����Թܵ������ұ߽���180�����ߣ�Ȼ���ж����Y�Ƿ��ڿ�϶��
		  *�����  ��state =1
		  *����  state=2
		  *
		  *�÷֣�����һ��X���ٽ�� 175������ܵ��ı߽����175 �����Y�ڿ�϶�ڣ����ʾͨ������5�֣�
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
	 //��Ϸ����
	 public void GameOver(){
		 if(state == 2){
			 //��������
			 pup =Arrays.copyOf(pup,0);
			 pdown = Arrays.copyOf(pdown,0);
			 by = Arrays.copyOf(by,0);
			 bankwidth = Arrays.copyOf(bankwidth,0);
			 
		 }
	 }
	 
	 //action����
	 public void action(){
		 //������
		 MouseAdapter l =new MouseAdapter(){
			 //�������߰���ִ��
			 public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
						speed=2;
						
				}
			 //�ͷ�ִ��
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
						speed=0;
					}
				//���ִ��  state = 0 �� 2ʱ������
				//state =0  ִ�к�state = 1
				//state =2 ִ�к�state = 0 score = 0 
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
		this.addMouseListener(l);//����������¼�
		this.addMouseMotionListener(l);
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			 public void run(){
				 repaint();
				 if(state ==1){
					 bangaction();//��ײ
					 b.moveTo(speed);//����ƶ�
					 enterPipe();//�ܵ�����
					 StepAction();//�߲�
					 outOfBoundAction();//Խ��
					 }
				 GameOver();//��Ϸ����
				 }
		 },10,10);
	 }
	public static void main(String[] args){
		FlappyBird f =  new FlappyBird();
		
		JFrame j =new JFrame();
		j.add(f);
		j.setTitle("Flappy Bird");//��������
		j.setSize(WIDTH, HEIGHT);//���ô�С
		j.setLocationRelativeTo(null);//�����м���ʾ
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�����洰�ڵĹرն��ر�
		j.setVisible(true);//���ÿɼ���
		f.action();
	}
}

