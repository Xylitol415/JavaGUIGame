package MyShoot;

import java.awt.image.BufferedImage;

public class Boss extends FlyingObject{
	BufferedImage[] images1;
	int speed;
	int xspeed;
	int life;
	boolean UP = true;

	public Boss() {
		image = Shoot.b2;
		x = Shoot.WIDTH / 2 - image.getWidth() / 2;
		y = Shoot.HEIGHT;
		images = new BufferedImage[] { Shoot.b0, Shoot.b1 };
		images1 = new BufferedImage[] { Shoot.b2, Shoot.b3 };
		width = image.getWidth();
		height = image.getHeight();
		speed = 2;
		xspeed = 1;
		life = 3;
	}

	// boss机走步
	int index = 0;

	public void step() {
		if (UP) {
			image = images1[index++ / 20 % images.length];
			y -= speed;
			if (this.y == -this.image.getHeight()) {
				UP = false;
			}
		} else {
			image = images[index++ / 20 % images1.length];
			y += speed;
			if (this.y == 0) {
				speed = 0;
				x += xspeed;
				if (this.x >= Shoot.WIDTH - image.getWidth()) {
					xspeed = -1;
				}
				if (this.x == 0) {
					xspeed = 1;
				}
			}
		}
	}
	
	// 发射子弹
	public BossBullet[] shoot() {
		BossBullet[] bbt = new BossBullet[4];
		bbt[0] = new BossBullet(this.x+60, this.y+150);
		bbt[1] = new BossBullet(this.x+120, this.y+150);
		bbt[2] = new BossBullet(this.x+180, this.y+150);
		bbt[3] = new BossBullet(this.x+240, this.y+150);
		return bbt;
	}

	@Override
	public boolean outOfBound() {
		// TODO Auto-generated method stub
		return false;
	}

}
