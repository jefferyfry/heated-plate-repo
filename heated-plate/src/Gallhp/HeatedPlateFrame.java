package Gallhp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
public class HeatedPlateFrame extends JFrame implements HeatedPlateController.HeatedPlateControllerListener,ActionListener {
	
	//all the gui widget components
	private HeatedPlateController heatedPlateController = new HeatedPlateController();
	private HeatedPlateGridPanel heatedPlateGridPanel = new HeatedPlateGridPanel();
	private JComboBox heatedPlateAlgoComboBox = new JComboBox(new String[]{"Tpdahp","Tpfahp","Twfahp","Tpdohp"});
	private JSpinner plateDimensionSpinner = new JSpinner(new SpinnerNumberModel(10,0,100,1));
	private JSpinner leftEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50,0,100,1));
	private JSpinner rightEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50,0,100,1));
	private JSpinner topEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50,0,100,1));
	private JSpinner bottomEdgeTemperatureSpinner = new JSpinner(new SpinnerNumberModel(50,0,100,1));
	private JCheckBox animateCheckBox = new JCheckBox("Animate");
	private JButton getResultsButton = new JButton("Get Results");
	private JButton cancelButton = new JButton("Cancel");
	
	private JLabel elapsedTimeLabel = new JLabel("0");
	private JLabel memoryUsageLabel = new JLabel("0");
	private JLabel iterationLabel = new JLabel("0");

	/**
	 * Default constructor for the main window.
	 */
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
		getResultsButton.addActionListener(this);
		cancelButton.setEnabled(false);
		cancelButton.addActionListener(this);
		controlPanel.add(buttonPanel);
		
		getContentPane().add(controlPanel,BorderLayout.WEST);
		
		//create the analysis panel on the right
		JPanel analysisPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		analysisPanel.setBorder(new TitledBorder("Analysis"));
		
		JPanel elapsedTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		elapsedTimePanel.add(new JLabel("Elasped Time (ms):"));
		elapsedTimePanel.add(elapsedTimeLabel);
		gbc.gridy = 0;
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		analysisPanel.add(elapsedTimePanel,gbc);
		
		JPanel memoryUsagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		memoryUsagePanel.add(new JLabel("Memory Usage (bytes):"));
		memoryUsagePanel.add(memoryUsageLabel);
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		analysisPanel.add(memoryUsagePanel,gbc);
		
		JPanel iterationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		iterationPanel.add(new JLabel("Iterations:"));
		iterationPanel.add(iterationLabel);
		gbc.gridy = 2;
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		analysisPanel.add(iterationPanel,gbc);
		
		gbc.gridy = 4;
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		analysisPanel.add(new JPanel(),gbc);
		analysisPanel.setPreferredSize(new Dimension(250,analysisPanel.getHeight())); 
		getContentPane().add(analysisPanel,BorderLayout.EAST);
		
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
		
		//reset analysis
		elapsedTimeLabel.setText("0");
		memoryUsageLabel.setText("0");
		iterationLabel.setText("0");
		
		//initialize heated plate
		heatedPlateGridPanel.reset();
	}

	@Override
	public void haveResults(double[][] results) {
		heatedPlateGridPanel.setResults(results);
	}

	/* (non-Javadoc)
	 * @see Gallhp.HeatedPlateController.HeatedPlateControllerListener#haveElapsedTime(long)
	 */
	@Override
	public void haveElapsedTime(long elapsedTime) {
		elapsedTimeLabel.setText(Long.toString(elapsedTime));
	}

	/* (non-Javadoc)
	 * @see Gallhp.HeatedPlateController.HeatedPlateControllerListener#haveMemoryUsage(long)
	 */
	@Override
	public void haveMemoryUsage(long memoryUsed) {
		memoryUsageLabel.setText(Long.toString(memoryUsed));
	}

	/* (non-Javadoc)
	 * @see Gallhp.HeatedPlateController.HeatedPlateControllerListener#iterationCompleted(int)
	 */
	@Override
	public void iterationCompleted(int iteration) {
		iterationLabel.setText(Integer.toString(iteration));
	}

	@Override
	public void finished() {
		//enable disable buttons, fields
		setControlPanel(true);
		
	}
}
