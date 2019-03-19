package MyShoot;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {
	
	int doubleFire; // ˫������
	int life = 3; // ����ֵ	
	
	public Hero() {
		x = 230;
		y = 300;
		image = Shoot.hero0;
		width = image.getWidth();
		height = image.getHeight();
		images  = new BufferedImage[]{
				Shoot.hero0, Shoot.hero1, Shoot.hero2, Shoot.hero3, 
				Shoot.hero4, Shoot.hero5, Shoot.hero6, Shoot.hero7,
				Shoot.hero8, Shoot.hero9
		};
		doubleFire = 0;
	}
	
	// Ӣ�ۻ����߲�
	int index = 0;
	public void step(){
//		image = images[index++/10]; // 0-9 ������ 0-9
//		if(index >= 100){
//			index = 0;
//		}
		// 100������һ��    1000���� 1��
		image = images[index++/10%images.length];
		if(index >= Integer.MAX_VALUE) {
			index = 0;
		}
	}
	
	// Ӣ�ۻ��ƶ�
	public void moveTo(int x, int y) {
		this.x = x - width/2;
		this.y = y - height/2;
	}
	
	// �����ӵ�
	public Bullet[] shootBy() {
		if(doubleFire == 0) { // ��������
			Bullet[] bs = new Bullet[1]; // �ӵ�����
			bs[0] = new Bullet(this.x+width/2-Shoot.bullet.getWidth()/2, this.y+10);
			return bs;
		}else{ // ˫������
			Bullet[] bs = new Bullet[2];
			bs[0] = new Bullet(this.x+36, this.y+10);
			bs[1] = new Bullet(this.x+56, this.y+10);
			return bs;
		}
	}
	// Ӣ�ۻ���С�л�����ײ
	public boolean bang(int x, int y,int width, int height){
		/*
		  int x1 = this.x;
		 
		int y1 = this.y;
		if(x>x1) {
			int x2 = x;
			int y2 = y;
			int x3 = this.x + 165;
			int y3 = this.y + 161;
			if(x1 > x2 && x1 < x3 && y1 > y2 && y1 < y3){			
				return true;
			}
			return false;
		} else {
			x1 += this.width;
			int x2 = this.x - width;
			int y2 = this.y ;
			int x3 = this.x + this.width;
			int y3 = this.y + 161;
			if(x1 > x2 && x1 < x3 && y1 > y2 && y1 < y3){			
				return true;
			}
			return false;
		}*/
		int x1 = this.x - width;
		int x2 = x + this.width;
		int y1 = this.y - height;
		int y2 = y + this.height;
		if(x >= x1 && x<=x2 && y >= y1 && y <= y2) {
			return true;
		}
		return false;
	}

	@Override
	public boolean outOfBound() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
