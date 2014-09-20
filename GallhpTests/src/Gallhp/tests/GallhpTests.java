package Gallhp.tests;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import junit.framework.TestCase;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Gallhp.HeatedPlateFrame;
import Gallhp.HeatedPlateGridPanel;

public class GallhpTests extends TestCase {
	
	//GUI testing using FEST
	private FrameFixture gui=null;
	
	private String dim;
	private String left, right, top, bottom;
	private int animate;

	@Before
	public void setUp() throws Exception {
		dim = JOptionPane.showInputDialog("Enter dimension (integer value greater than 0):");
		
		left = JOptionPane.showInputDialog("Enter left temperature (integer value between 0 - 100, inclusive):");
		right = JOptionPane.showInputDialog("Enter right temperature (integer value between 0 - 100, inclusive):");
		top = JOptionPane.showInputDialog("Enter top temperature (integer value between 0 - 100, inclusive):");
		bottom = JOptionPane.showInputDialog("Enter bottom temperature (integer value between 0 - 100, inclusive):");
		
		animate = JOptionPane.showConfirmDialog(null, "Do you want to animate?", "Animate", JOptionPane.YES_NO_OPTION);
	
		gui = new FrameFixture(new HeatedPlateFrame());
		gui.show();
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		gui.cleanUp();
		
		super.tearDown();
	}

	@Test
	public void testDiffusion() throws IOException 
	{
		FileWriter write = new FileWriter("resultsGUI.csv");
		PrintWriter pw = new PrintWriter(write);
		
		for(int i=0; i < 4; i++)
		{
			gui.comboBox("heatedPlateAlgoComboBox").selectItem(i);
			pw.println("\nAlgorithm: " + gui.comboBox("heatedPlateAlgoComboBox").contents()[i]);
			
			gui.spinner("plateDimensionSpinner").enterText(dim);
			pw.println("\nDimension: , " + dim);
			
			gui.spinner("leftEdgeTemperatureSpinner").enterText(left);
			gui.spinner("rightEdgeTemperatureSpinner").enterText(right);
			gui.spinner("topEdgeTemperatureSpinner").enterText(top);
			gui.spinner("bottomEdgeTemperatureSpinner").enterText(bottom);
			pw.println("\nLeft Temp: , " + left + "\nTop Temp: , " + top + "\nRight Temp: , " + right + "\nBottom Temp: , " + bottom);
			
			if(animate == 0)
				gui.checkBox("animateCheckBox").check();
			
			gui.button("getResultsButton").click();
			
			HeatedPlateGridPanel gridPanel = (HeatedPlateGridPanel) gui.panel("heatedPlateGridPanel").target;
			
			Runtime runtime = Runtime.getRuntime();
			runtime.gc();
			
			long startTime = System.currentTimeMillis();
			while(!gui.button("getResultsButton").target.isEnabled());
			long endTime = System.currentTimeMillis();
			long endMemory = runtime.totalMemory() - runtime.freeMemory();
			
			pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + endMemory);
			pw.println("Elapsed Time (ms): , " + (endTime-startTime));
		}
		
		pw.close();
		write.close();
	}
}
