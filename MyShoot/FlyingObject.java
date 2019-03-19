package MyShoot;
import java.awt.image.BufferedImage;
/*
 * �����ࣺ
 * ���󷽷�ֻ��д�ڳ�������
 * �������ﲻ��ֻ�г��󷽷�
 */
public abstract class FlyingObject {
	int x;
	int y;
	int width;
	int height;
	BufferedImage image;
	BufferedImage[] images;
	int speed;
	
	public abstract void step();
	public abstract boolean outOfBound();
	
}
