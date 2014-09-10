package Tpdahp;

public class Diffusion 
{
	private double oldPlate[][];
	private double newPlate[][];
	
	private int dimension;
	private int left, right, top, bottom;
	
	public Diffusion(int dimension, int left, int right, int top, int bottom)
	{
		this.dimension = dimension;
		
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		
		oldPlate = new double[dimension + 2][dimension + 2];
		newPlate = new double[dimension + 2][dimension + 2];
		
		initialize(oldPlate);
	    initialize(newPlate);
	    diffuse();
	    printTable();
	}
	
	public void initialize(double[][] plate)
	{
		for(int i=1; i <= dimension; i++)
		{
			plate[i][0] = left;
			plate[i][dimension] = right;
			
			plate[0][i] = top;
			plate[dimension][i] = bottom;
		}
	}
	
	public void diffuse()
	{
		int count = 0;
		
		while (! done() || count++ < 10000) 
	    {
	      for (int i = 1; i <= dimension; i++) 
	        for (int j = 1; j <= dimension; j++) 
	          newPlate[i][j] = (oldPlate[i + 1][j] + oldPlate[i - 1][j] +
	                            oldPlate[i][j + 1] + oldPlate[i][j - 1]) / 4.0;
	      
	      swap();
	    }
	}
	
	private boolean done()
	{
		for(int i=1; i <= dimension; i++)
			for(int j=1; j <= dimension; j++)
				if(Math.abs(newPlate[i][j] - oldPlate[i][j]) > 0.25)
					return false;
					
		return true;
	}
	
	private void swap()
	{
		double[][] temp;
		
		temp = oldPlate;
		oldPlate = newPlate;
		newPlate = temp;
	}
	
	public void printTable()
	{
		for(int i=1; i <= dimension; i++)
			for(int j=1; j <= dimension; j++)
				System.out.format(".2%d", newPlate[i][j]);
	}
	 
}
