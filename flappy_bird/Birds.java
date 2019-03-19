package flappy_bird;

import java.awt.image.BufferedImage;
/*
 * ����
 */

public class Birds {
	int x;
	int y;
	int width;
	int height;
	int yspeed;
	int xspeed;
	int index;
	BufferedImage image;
	BufferedImage[] images ={};
	
	public Birds(){
		image = FlappyBird.birds[0];
		width = image.getWidth();
		height = image.getHeight();
		x = 180;
		y =300;
		images = FlappyBird.birds;
		index =0;
		xspeed =2;
		yspeed =1;
		
	}
	//�߲�
	public void step(){
		image = images[index++/10%images.length];//
		if(index >= Integer.MAX_VALUE){
			index = 0;
		}
	}
	//�ƶ�
	public void moveTo(int speed){
		this.y= this.y+yspeed-speed;
	}
	
	//Խ��
	public boolean outOfBound(){
		return this.y>=600-45 ||this.y <= 0;
	}

}
