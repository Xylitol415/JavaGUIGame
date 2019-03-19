package MyShoot;

import java.awt.image.BufferedImage;

/*
 *  爱心类
 */
public class Heart extends FlyingObject {
	
	int xspeed;
	//int state = 0;

	public Heart() {
		image = Shoot.q0;
		x = (int) (Math.random() * Shoot.WIDTH - image.getWidth());
		y = -image.getHeight();
		images = new BufferedImage[] { Shoot.q0, Shoot.q1, Shoot.q2, Shoot.q3, Shoot.q4, Shoot.q5, Shoot.q6, Shoot.q7,
				Shoot.q8, };
		width = image.getWidth();
		height = image.getHeight();
		speed = 2;
		xspeed = 1;
	}

	// 爱心走步
	int index = 0;

	public void step() {
		image = images[index++ / 20 % images.length];
		y += speed;
		x += xspeed;
		if(x>=Shoot.WIDTH-image.getWidth()){
			xspeed = -1;
		}else if(x<=0)
		{
			xspeed = 1;
		}

	}
	// 爱心越界
	public boolean outOfBound() {
		return y > Shoot.HEIGHT;
	}

}

