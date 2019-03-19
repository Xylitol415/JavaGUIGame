package godhands;

import java.awt.image.BufferedImage;
/*
 * Ê¯Í·µÐÈË
 */

public class St {
	int x;
	int y;
	BufferedImage[] images = new BufferedImage[2];
	int width;
	int height;
	
	public St(){
		images = GodHands.st;
		x=0;
		y =0;
		width = images[0].getWidth();
		height = images[0].getHeight();
	}
	
	public void step(){
		this.y+=3;
	}
	
	public boolean outOfBound(){
		return this.y >= GodHands.HEIGHT;
	}

}
