package Gallhp;

import javax.swing.JFrame;

/**
 * Demo is the simple launch class which just sets up the heated plate frame.
 * @author jeffro
 *
 */
public class Demo {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HeatedPlateFrame heatedPlateFrame = new HeatedPlateFrame();

		heatedPlateFrame.setExtendedState(heatedPlateFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH);   
		heatedPlateFrame.setVisible(true);        
		heatedPlateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
