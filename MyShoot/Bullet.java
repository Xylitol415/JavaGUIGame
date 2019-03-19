package MyShoot;

import java.awt.image.BufferedImage;

public class Bullet {
	int x;
	int y;
	int width;
	int height;
	int speed;
	BufferedImage image;
	// 有参构造器
	public Bullet(int x, int y) {
		image = Shoot.bullet;
		//x = x;//两个x都是局部变量，JAVA的就近原则
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
		speed = 2;
	}
	// 子弹的移动
	public void step() {
		y -= speed;
	}
	// 与小敌机的碰撞
	public boolean bang(int x, int y,int width, int height) {
		int x1 = this.x + this.width/2;
		int y1 = this.y + this.height/2;
		int x2 = x;
		int y2 = y;
		int x3 = x + width;
		int y3 = y + height;
		if(x1 > x2 && x1 < x3 && y1 > y2 && y1 < y3){			
			return true;
		}
		return false;
	}
		
	// 子弹越界
	public boolean OutOfBound() {
		return y < -height;
	}
	
}
