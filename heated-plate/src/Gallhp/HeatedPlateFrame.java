package Gallhp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class HeatedPlateFrame extends JFrame implements HeatedPlateController.HeatedPlateControllerListener, ActionListener {
	
	//constant strings for input tips
	private final static String DIMENSION_RANGE_TIP = "  (0 < dimension <= 50)";
	private final static String TEMPERATURE_RANGE_TIP = "  (0 <= temperature <= 100)";
	
	//all the gui widget components
	private HeatedPlateController heatedPlateController = new HeatedPlateController();
	private HeatedPlateGridPanel heatedPlateGridPanel = new HeatedPlateGridPanel();
	private JComboBox heatedPlateAlgoComboBox = new JComboBox(new String[]{"Tpdahp","Tpfahp","Twfahp","Tpdohp"});
	private JSpinner plateDimensionSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
	private JSpinner leftEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50,0,100,1));
	private JSpinner rightEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50,0,100,1));
	private JSpinner topEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50,0,100,1));
	private JSpinner bottomEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50,0,100,1));
	private JCheckBox animateCheckBox = new JCheckBox("Animate");
	private JButton getResultsButton = new JButton("Get Results");
	private JButton cancelButton = new JButton("Cancel");

	/**
	 * Default constructor for the main window.
	 */
	public HeatedPlateFrame() {
		super("Gallhp");
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
		plateDimensionSpinnerPanel.add(new JLabel(DIMENSION_RANGE_TIP));
		controlPanel.add(plateDimensionSpinnerPanel);
		
		JPanel leftEdgeTemperatureSpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		leftEdgeTemperatureSpinnerPanel.add(new JLabel("Left Edge Temperature:"));
		leftEdgeTemperatureSpinnerPanel.add(leftEdgeTemperatureSpinner);
		leftEdgeTemperatureSpinnerPanel.add(new JLabel(TEMPERATURE_RANGE_TIP));
		controlPanel.add(leftEdgeTemperatureSpinnerPanel);
		
		JPanel rightEdgeTemperatureSpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rightEdgeTemperatureSpinnerPanel.add(new JLabel("Right Edge Temperature:"));
		rightEdgeTemperatureSpinnerPanel.add(rightEdgeTemperatureSpinner);
		rightEdgeTemperatureSpinnerPanel.add(new JLabel(TEMPERATURE_RANGE_TIP));
		controlPanel.add(rightEdgeTemperatureSpinnerPanel);
		
		JPanel topEdgeTemperatureSpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topEdgeTemperatureSpinnerPanel.add(new JLabel("Top Edge Temperature:"));
		topEdgeTemperatureSpinnerPanel.add(topEdgeTemperatureSpinner);
		topEdgeTemperatureSpinnerPanel.add(new JLabel(TEMPERATURE_RANGE_TIP));
		controlPanel.add(topEdgeTemperatureSpinnerPanel);
		
		JPanel bottomEdgeTemperatureSpinnerPanel = new JPanel();
		bottomEdgeTemperatureSpinnerPanel.add(new JLabel("Bottom Edge Temperature:"));
		bottomEdgeTemperatureSpinnerPanel.add(bottomEdgeTemperatureSpinner);
		bottomEdgeTemperatureSpinnerPanel.add(new JLabel(TEMPERATURE_RANGE_TIP));
		controlPanel.add(bottomEdgeTemperatureSpinnerPanel);
		
		JPanel animateCheckBoxPanel = new JPanel();
		animateCheckBoxPanel.add(animateCheckBox);
		controlPanel.add(animateCheckBoxPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(getResultsButton);
		buttonPanel.add(cancelButton);
		getResultsButton.addActionListener(this);
		cancelButton.setEnabled(false);
		cancelButton.addActionListener(this);
		controlPanel.add(buttonPanel);
		
		getContentPane().add(controlPanel,BorderLayout.WEST);
		
		heatedPlateController.addListener(this);
	}
	
	private void setControlPanel(boolean enabled){
		getResultsButton.setEnabled(enabled);
		cancelButton.setEnabled(!enabled);
		
		heatedPlateAlgoComboBox.setEnabled(enabled);
		plateDimensionSpinner.setEnabled(enabled);
		leftEdgeTemperatureSpinner.setEnabled(enabled);
		rightEdgeTemperatureSpinner.setEnabled(enabled);
		topEdgeTemperatureSpinner.setEnabled(enabled);
		bottomEdgeTemperatureSpinner.setEnabled(enabled);
		animateCheckBox.setEnabled(enabled);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==getResultsButton){
			int algorithm = heatedPlateAlgoComboBox.getSelectedIndex();
			boolean animation = animateCheckBox.isSelected();
			int dimension = (Integer) plateDimensionSpinner.getValue();
			int left = (Integer)leftEdgeTemperatureSpinner.getValue();
			int right = (Integer)rightEdgeTemperatureSpinner.getValue();
			int top = (Integer)topEdgeTemperatureSpinner.getValue();
			int bottom = (Integer)bottomEdgeTemperatureSpinner.getValue();
			heatedPlateController.start(dimension, left, right, top, bottom, algorithm, animation);
		}
		else if(e.getSource()==cancelButton){
			heatedPlateController.cancel();
			JOptionPane.showMessageDialog(this,"Cancelled!" , "WARNING", JOptionPane.WARNING_MESSAGE, null);
		}
	}

	/**
	 * Interface methods for HeatedPlateControllerListener allow callbacks to update the UI.
	 */
	@Override
	public void started() {
		//enable disable buttons, fields
		setControlPanel(false);
		
		//initialize heated plate
		heatedPlateGridPanel.reset();
	}

	@Override
	public void haveResults(double[][] results) {
		heatedPlateGridPanel.setResults(results);
	}


	@Override
	public void finished() {
		//enable disable buttons, fields
		setControlPanel(true);
		
	}
}
