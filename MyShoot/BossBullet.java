package MyShoot;

import java.awt.image.BufferedImage;

public class BossBullet extends FlyingObject{
	
	public BossBullet(int x, int y) {
		this.x = x;
		this.y = y;
		image = Shoot.bbu0;
		speed = 2;
		width = image.getWidth();
		height = image.getHeight();
		images = new BufferedImage[]{
				Shoot.bbu0, Shoot.bbu1
		};	
	}
	// 子弹走步
	public void step() {
		y += speed;
	}
	// 与英雄机的碰撞
	public boolean bang(int x, int y, int width, int height) {
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
	public boolean outOfBound() {
		return y > Shoot.HEIGHT;
	}
	
}
