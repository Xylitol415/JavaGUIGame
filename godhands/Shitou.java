package godhands;

import java.awt.image.BufferedImage;

public class Shitou {
	int x;
	int y;
	BufferedImage[] images = new BufferedImage[2];
	int width;
	int height;
	int score;
	
	public Shitou(){
		images = GodHands.shitou;
		x = 185;
		y = 650;
		width = images[0].getWidth();
		height = images[0].getHeight();
		score = 0;
	}
	
//	public void moveTo(int speed){
//		this.x+=speed;
//	}
	
	// ×ó²àÊ¯Í·Åö×²
	public boolean bang1(int x, int y, int w, int h){		
		int x1 = this.x;
		int y1 = this.y;
		int x2 = x+w;
		int y2 = y+h;
		if(x1>=x && x1<=x2 && y1>=y && y1<=y2) {
			return true;
		}
		return false;
	}
	// ÓÒ²àÊ¯Í·Åö×²
	public boolean bang2(int x, int y, int w, int h){		
		int x1 = this.x;
		int y1 = this.y;
		int x2 = x+w;
		int y2 = y+h;
		int x3 = x1 + w;
		if(x3>=x && x3<=x2 && y1>=y && y1<=y2) {
			return true;
		}
		return false;
	}
	
	
}
