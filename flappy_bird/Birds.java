package flappy_bird;

import java.awt.image.BufferedImage;
/*
 * 鸟类
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
	//走步
	public void step(){
		image = images[index++/10%images.length];//
		if(index >= Integer.MAX_VALUE){
			index = 0;
		}
	}
	//移动
	public void moveTo(int speed){
		this.y= this.y+yspeed-speed;
	}
	
	//越界
	public boolean outOfBound(){
		return this.y>=600-45 ||this.y <= 0;
	}

}
