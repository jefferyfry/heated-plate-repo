package Gallhp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
    private DecimalFormat df = new DecimalFormat("###.##");
    private DecimalFormat df1 = new DecimalFormat("###.#");
    private DecimalFormat df2 = new DecimalFormat("###");
    
    //heated plate results
    private double[][] results;
	
    /**
     * Default constrcutor.
     */
    public HeatedPlateGridPanel() {
        super();
        reset();
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
	 * @return the results
	 */
	public double[][] getResults() {
		return results;
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
     * Return the preferred size of the panel.
     */
    @Override
	public Dimension getPreferredSize() {
		return new Dimension(500,500);
	}

    /**
     * Paints a single cell in the heated plate grid.
     * @param graphics
     * @param row
     * @param col
     * @param value
     */
    private void paintSquare(Graphics graphics, int row, int col, double value,int length, int cellSize,int dim) {
    	int ulhcY = (getHeight()-length)/2;
    	int ulhcX = (getWidth()-length)/2;
    	
        int rowPos = ulhcY + row * cellSize;
        int colPos = ulhcX + col * cellSize;

        // Overwrite everything that was there previously
        graphics.setColor(Color.black);
        graphics.fillRect(colPos, rowPos, cellSize, cellSize);
        
        // Color in RGB format with green and blue values = 0.0
        graphics.setColor(new Color((float) value/100.0f, 0.f, 0.f));    
        graphics.fillRect(colPos, rowPos, cellSize, cellSize);
        
        //write the temperature
        if(value!=0){
	        graphics.setFont(new Font("Arial", Font.PLAIN,9)); 
	        graphics.setColor(Color.white);
	        FontMetrics fm = graphics.getFontMetrics();
	        String valueStr;
	        if(dim<=25)
	        	valueStr = df.format(value);
	        else if(dim<=30)
	        	valueStr =df1.format(value);
	        else
	        	valueStr = df2.format(value);
	        int x = (cellSize - fm.stringWidth(valueStr)) / 2;
	        int y = (fm.getAscent() + (cellSize - (fm.getAscent() + fm.getDescent())) / 2);
	        graphics.drawString(valueStr, colPos+x, rowPos+y);
        }
    }
    
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        //calculate length of the heated plate
        int length=getHeight()-MARGIN;
    	if(getWidth()<getHeight())
    		length = getWidth()-MARGIN;
    
    	//determine the size of a cell/grid square
    	int cellSize = length/results.length;
        
        if(results!=null){
	        BufferedImage bi = new BufferedImage(getWidth(), getHeight(),
	                BufferedImage.TYPE_INT_ARGB);
	        Graphics anotherGraphics = bi.createGraphics();
	
	        for (int i = 0; i < results.length; i++)
	            for (int j = 0; j < results.length; j++){
	                double value = results[i][j];
	                paintSquare(anotherGraphics, i, j, value,length,cellSize,results.length-2);
	            }
	        
	        //paint edge boundary
	        anotherGraphics.setColor(Color.yellow);
	        int boundaryLength = (results.length-2)*cellSize;
	        int ulhcY = (getHeight()-length)/2+cellSize;
	    	int ulhcX = (getWidth()-length)/2+cellSize;
	        anotherGraphics.drawRect(ulhcX, ulhcY, boundaryLength, boundaryLength);
	        
	        graphics.drawImage(bi, 0, 0, this);
        }
   }

}
