package MyShoot;

import java.awt.image.BufferedImage;
/*
 *  小敌机类
 */
public class AirPlane extends FlyingObject {
	
	public AirPlane() {
		image = Shoot.a0;
		y = -image.getHeight();
		x = (int)(Math.random()*Shoot.WIDTH - image.getWidth());
		images = new BufferedImage[] {
				Shoot.a0, Shoot.a1, Shoot.a2,
				Shoot.a3, Shoot.a4, Shoot.a5
		};
		width = image.getWidth();
		height = image.getHeight();
		speed = 2;
	}
	// 小敌机走步
	int index = 0;
	public void step() {
		image = images[index++/30%images.length];
		if(index >= Integer.MAX_VALUE) {
			index = 0;			
		}
		y += speed;
	}
	// 小敌机越界
	public boolean outOfBound() {
		return y > Shoot.HEIGHT;
	}
}
