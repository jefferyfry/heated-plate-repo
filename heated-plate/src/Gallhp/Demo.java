package Gallhp;

import javax.swing.JFrame;

public class Demo {

	public static void main(String[] args) {
		HeatedPlateFrame heatedPlateFrame = new HeatedPlateFrame();

		heatedPlateFrame.pack();   
		heatedPlateFrame.setVisible(true);        
		heatedPlateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
