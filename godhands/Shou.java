package godhands;

import java.awt.image.BufferedImage;

/*
 *  ÷¿‡
 */

public class Shou {
	int x;
	int y;
	BufferedImage[] images = new BufferedImage[4];
	int width;
	int height;
	
	public Shou(){
		images = GodHands.shou;
		x = 0;
		y = 758;
		width = images[0].getWidth();
		height = images[0].getHeight();
	}

}
