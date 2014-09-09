package Gallhp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class HeatedPlateFrame extends JFrame implements HeatedPlateController.HeatedPlateControllerListener {
	
	private HeatedPlateController demoController = new HeatedPlateController();
	private HeatedPlateGridPanel heatedPlateGridPanel = new HeatedPlateGridPanel();
	private JComboBox<String> heatedPlateAlgoComboBox = new JComboBox<String>(new String[]{"Tpdahp","Tpfahp","Twfahp","Tpdohp"});
	private JSpinner plateDimensionSpinner = new JSpinner(new SpinnerNumberModel(10,0,100,1));
	private JSpinner leftEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50.0,0.0,100.0,0.1));
	private JSpinner rightEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50.0,0.0,100.0,0.1));
	private JSpinner topEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50.0,0.0,100.0,0.1));
	private JSpinner bottomEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50.0,0.0,100.0,0.1));
	private JCheckBox animateCheckBox = new JCheckBox("Animate");
	private JButton getResultsButton = new JButton("Get Results");
	private JButton cancelButton = new JButton("Cancel");

	public HeatedPlateFrame() {
		super("Gallhp Demo");
		getContentPane().setLayout(new BorderLayout());
		
		//the heated plate is in the "center"
		getContentPane().add(heatedPlateGridPanel,BorderLayout.CENTER);
		heatedPlateGridPanel.setBorder(new TitledBorder("Heated Plate Results"));
		
		//create the control panel on the left with all the parameters
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel,BoxLayout.Y_AXIS));
		controlPanel.setBorder(new TitledBorder("Heated Plate Control"));
		
		JPanel heatedPlateAlgoComboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		heatedPlateAlgoComboBoxPanel.add(new JLabel("Heated Plate Type:"));
		heatedPlateAlgoComboBoxPanel.add(heatedPlateAlgoComboBox);
		controlPanel.add(heatedPlateAlgoComboBoxPanel);
		
		JPanel plateDimensionSpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		plateDimensionSpinnerPanel.add(new JLabel("Plate Dimension:"));
		plateDimensionSpinnerPanel.add(plateDimensionSpinner);
		controlPanel.add(plateDimensionSpinnerPanel);
		
		JPanel leftEdgeTemperatureSpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		leftEdgeTemperatureSpinnerPanel.add(new JLabel("Left Edge Temperature:"));
		leftEdgeTemperatureSpinnerPanel.add(leftEdgeTemperatureSpinner);
		controlPanel.add(leftEdgeTemperatureSpinnerPanel);
		
		JPanel rightEdgeTemperatureSpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rightEdgeTemperatureSpinnerPanel.add(new JLabel("Right Edge Temperature:"));
		rightEdgeTemperatureSpinnerPanel.add(rightEdgeTemperatureSpinner);
		controlPanel.add(rightEdgeTemperatureSpinnerPanel);
		
		JPanel topEdgeTemperatureSpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topEdgeTemperatureSpinnerPanel.add(new JLabel("Top Edge Temperature:"));
		topEdgeTemperatureSpinnerPanel.add(topEdgeTemperatureSpinner);
		controlPanel.add(topEdgeTemperatureSpinnerPanel);
		
		JPanel bottomEdgeTemperatureSpinnerPanel = new JPanel();
		bottomEdgeTemperatureSpinnerPanel.add(new JLabel("Left Edge Temperature:"));
		bottomEdgeTemperatureSpinnerPanel.add(bottomEdgeTemperatureSpinner);
		controlPanel.add(bottomEdgeTemperatureSpinnerPanel);
		
		JPanel animateCheckBoxPanel = new JPanel();
		animateCheckBoxPanel.add(animateCheckBox);
		controlPanel.add(animateCheckBoxPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(getResultsButton);
		buttonPanel.add(cancelButton);
		controlPanel.add(buttonPanel);
		
		getContentPane().add(controlPanel,BorderLayout.WEST);
		
		demoController.addListener(this);
	}

	@Override
	public void started() {
		//enable disable buttons, fields
		//initialize heated plate
		
	}

	@Override
	public void haveResults(double[][] results) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finished() {
		//enable disable buttons, fields
		
	}
}
