package RocketsLogo;

import javax.swing.JFrame;

public class RocketsJFrame extends JFrame {
	RocketsJPanel rj;
	public RocketsJFrame() {
		rj = new RocketsJPanel();
		this.add(rj);
		this.setTitle("ÐÝË¹¶Ù»ð¼ý");
		this.setSize(1365, 727);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		new RocketsJFrame();
	}

}
