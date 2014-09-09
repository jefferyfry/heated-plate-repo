package Gallhp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HeatedPlateGridPanel extends JPanel {
    
	//margin around the heated plate
    private final static int MARGIN = 50;
    
    //formatting of the text
    private DecimalFormat df = new DecimalFormat("###.00");

    //heated plate dimension
    private int dimension=10;
    
    //heated plate results
    private double[][] results;
	
    /**
     * Default constrcutor.
     */
    public HeatedPlateGridPanel() {
        super();
    }
    
    
    /**
     * Set new results and update the heated plate panel.
     * @param results
     */
    public void setResults(double[][] results) {
		this.results = results;
		repaint();
	}
    
    /**
     * Resets the heated plate grid to nothing.
     */
    public void reset(){
    	this.results = new double[2][2];
    	for(int i=0;i<results.length;i++){
    		for(int j=0;j<results.length;j++)
    			this.results[i][j]=0;
    	}
    	repaint();
    }

	/**
     * Sets the heated plate dimension to draw the grid.
     * @param dimension
     */
    public void setHeatedPlateDimension(int dimension){
    	this.dimension=dimension;
    }
    
    /**
     * Return the preferred size of the panel.
     */
    @Override
	public Dimension getPreferredSize() {
		return new Dimension(500,500);
	}

    /**
     * Paints a single cell in the heated plate grid.
     * @param aGraphics
     * @param row
     * @param col
     * @param value
     */
    private void paintSquare(Graphics aGraphics, int row, int col, double value) {
    	int length=getHeight()-MARGIN;
    	if(getWidth()<getHeight())
    		length = getWidth()-MARGIN;
    
    	int cellSize = length/dimension;
    	
    	int ulhcY = (getHeight()-length)/2;
    	int ulhcX = (getWidth()-length)/2;
    	
        int rowPos = ulhcY + row * cellSize;
        int colPos = ulhcX + col * cellSize;

        // Overwrite everything that was there previously
        aGraphics.setColor(Color.black);
        aGraphics.fillRect(colPos, rowPos, cellSize, cellSize);
        
        // Color in RGB format with green and blue values = 0.0
        aGraphics.setColor(new Color((float) value/100.0f, 0.f, 0.f));    
        aGraphics.fillRect(colPos, rowPos, cellSize, cellSize);
        
        //write the temperature
        aGraphics.setColor(Color.white);
        FontMetrics fm = aGraphics.getFontMetrics();
        String valueStr = df.format(value);
        int x = (cellSize - fm.stringWidth(valueStr)) / 2;
        int y = (fm.getAscent() + (cellSize - (fm.getAscent() + fm.getDescent())) / 2);
        aGraphics.drawString(valueStr, colPos+x, rowPos+y);
    }
    
    protected void paintComponent(Graphics aGraphics) {
        super.paintComponent(aGraphics);
        
        if(results!=null){
	        BufferedImage bi = new BufferedImage(getWidth(), getHeight(),
	                BufferedImage.TYPE_INT_ARGB);
	        Graphics anotherGraphics = bi.createGraphics();
	
	        for (int i = 0; i < dimension; i++)
	            for (int j = 0; j < dimension; j++){
	                double value = results[i][j];
	                paintSquare(anotherGraphics, i, j, value);
	            }
	        
	        aGraphics.drawImage(bi, 0, 0, this);
        }
   }

}
