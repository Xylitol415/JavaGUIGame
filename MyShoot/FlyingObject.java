package MyShoot;
import java.awt.image.BufferedImage;
/*
 * 抽象类：
 * 抽象方法只能写在抽象类里
 * 抽象类里不是只有抽象方法
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
