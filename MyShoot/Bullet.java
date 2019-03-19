package MyShoot;

import java.awt.image.BufferedImage;

public class Bullet {
	int x;
	int y;
	int width;
	int height;
	int speed;
	BufferedImage image;
	// �вι�����
	public Bullet(int x, int y) {
		image = Shoot.bullet;
		//x = x;//����x���Ǿֲ�������JAVA�ľͽ�ԭ��
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
		speed = 2;
	}
	// �ӵ����ƶ�
	public void step() {
		y -= speed;
	}
	// ��С�л�����ײ
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
		
	// �ӵ�Խ��
	public boolean OutOfBound() {
		return y < -height;
	}
	
}
