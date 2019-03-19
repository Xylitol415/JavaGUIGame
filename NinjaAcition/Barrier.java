package NinjaAcition;

public class Barrier {
	int x;
	int y;
	int width;
	int height;
	int speed;
	
	public Barrier() {
		width = 80 + (int)(Math.random()*100);
		height = 50 + (int)(Math.random()*200);
		x = -2;
		y = 0;
		speed = 2;
	}
	
	// 障碍物的移动
	public void step() {
		y += speed; 
	}
	
	// 障碍物越界
	public boolean outOfBound(){
		return y >= Running.HEIGHT;
	}
	
	public boolean continuous(int x, int y, int w, int h){
		if(this.y < y+h){
			return true;
		}
		return false;
	}
	
}
