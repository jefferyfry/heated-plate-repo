package Gallhp.tests;

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

	@Before
	public void setUp() throws Exception {
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
	public void testLeftEdgeTemperature() {
		gui.spinner("leftEdgeTemperatureSpinner").enterText("45");
		gui.button("getResultsButton").click();
		HeatedPlateGridPanel gridPanel = (HeatedPlateGridPanel) gui.panel("heatedPlateGridPanel").target;
		assertEquals(45.0,gridPanel.getResults()[1][0]);
	}
	
	@Test
	public void testRightEdgeTemperature() {
		gui.spinner("plateDimensionSpinner").enterText("25");
		gui.spinner("rightEdgeTemperatureSpinner").enterText("46");
		gui.button("getResultsButton").click();
		HeatedPlateGridPanel gridPanel = (HeatedPlateGridPanel) gui.panel("heatedPlateGridPanel").target;
		assertEquals(46.0,gridPanel.getResults()[1][26]);
	}
	
	@Test
	public void testTopEdgeTemperature() {
		gui.spinner("topEdgeTemperatureSpinner").enterText("47");
		gui.button("getResultsButton").click();
		HeatedPlateGridPanel gridPanel = (HeatedPlateGridPanel) gui.panel("heatedPlateGridPanel").target;
		assertEquals(47.0,gridPanel.getResults()[0][1]);
	}
	
	@Test
	public void testBottomEdgeTemperature() {
		gui.spinner("plateDimensionSpinner").enterText("25");
		gui.spinner("bottomEdgeTemperatureSpinner").enterText("48");
		gui.button("getResultsButton").click();
		HeatedPlateGridPanel gridPanel = (HeatedPlateGridPanel) gui.panel("heatedPlateGridPanel").target;
		assertEquals(48.0,gridPanel.getResults()[26][1]);
	}
	

	
	@Test
	public void testGridSize() {
		gui.spinner("plateDimensionSpinner").enterText("25");
		gui.button("getResultsButton").click();
		HeatedPlateGridPanel gridPanel = (HeatedPlateGridPanel) gui.panel("heatedPlateGridPanel").target;
		assertEquals(27,gridPanel.getResults().length);
	}

}
