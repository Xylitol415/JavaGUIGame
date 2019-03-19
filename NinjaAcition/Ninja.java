package NinjaAcition;

import java.awt.image.BufferedImage;

public class Ninja {
	int x;
	int y;
	int width;
	int height;
	BufferedImage image;
	BufferedImage[] images;
	BufferedImage[] images1;
	int speed;
	int xspeed;
	int direction; // ����0������ 1������
	boolean edge;
	
	public Ninja() {
		image = Running.nl1;
		width = image.getWidth();
		height = image.getHeight();
		x = 200;
		y = 500;
		images = new BufferedImage[]{
				Running.nl1, Running.nl2
		};
		images1 = new BufferedImage[]{
				Running.nr1, Running.nr2
		};
		speed = 1;	
		xspeed = 3;
		direction = 0;
		edge = false;
	}
	
	// ���ߵ��߲�
	int index = 0;
	public void step() {
		y -= speed;
		if(direction == 0){		
			image = images[index++/20%images.length];
			// mouseClicked�¼��е����ı䷽��������ƶ�
			if(Running.changeDir){
				x -= xspeed;
				speed = 0;
			}
		}
		if(direction == 1) {
			image = images1[index++/20%images.length];
			// mouseClicked�¼��е����ı䷽��������ƶ�
			if(Running.changeDir){
				x += xspeed;
				speed = 0;
			}
		}
	}

	// �������ϰ������ײ
	public boolean collision(int x, int y, int w, int h) {
		/*//ȫ����ײ
		if(direction == 0) {
			int x1 = this.x;
			int y1 = this.y;
			int x2 = x + w+2;
			int y2 = y + h;
			if(x1 >= x && x1<=x2 && y1 >= y && y1 <= y2) {
				System.out.println("���ײ�ˣ�");
				return true;
			}
		} else {
			int x1 = this.x + width;
			int y1 = this.y;
			int x2 = Running.WIDTH;
			int y2 = y + h;
			if(x1 >= x && x1<=x2 && y1 >= y && y1 <= y2) {
				System.out.println("�ұ�ײ�ˣ�");
				return true;
			}
		}*/
		// ������ײ
		if(direction == 0) {
			int x1 = this.x;
			int y1 = this.y;
			int x2 = x + w+2;
			int y2 = y + h;
			//if(((x1-x2)==-1 || (x1-x2)==0||(x1-x2)==-2) && y1 >= y && y1 <= y2) {
			if(((x1-x2)==-1 || (x1-x2)==0 || (x1-x2)==-2)){
				//System.out.println("���ײ�ˣ�");
				if(y1 >= y && y1 <= y2) {					
					return true;
				} else {
					return false;
				}
			}
		} else {
			int x1 = this.x + width;
			int y1 = this.y;
			int x2 = Running.WIDTH;
			int y2 = y + h;
			//if(((x1 - x)==-2||(x1 - x)==-1||(x1 - x)==0) && y1 >= y && y1 <= y2) {
			if(((x1 - x)==-2||(x1 - x)==-1||(x1 - x)==0)){
				//System.out.println("�ұ�ײ�ˣ�");
				if(y1 >= y && y1 <= y2) {					
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
}
