package flappy_bird;

import java.awt.image.BufferedImage;

/*
 * �ܵ���
 */
public class Pipe {
	int x1;
	int y1;
	int width;
	int height;
	BufferedImage image1;
	BufferedImage image2;
	
	public Pipe(){
		image1 = FlappyBird.pipe1;
		image2 = FlappyBird.pipe2;
		x1 =800;
		y1 =0;
		width = image1.getWidth();
		height = image1.getHeight();	
	}
	//Խ��
	public boolean outOfBound(){
		return this.x1<=0-width;
	}
	
}
