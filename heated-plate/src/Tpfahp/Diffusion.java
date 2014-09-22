package Tpfahp;

public class Diffusion 
{
	private float oldPlate[][];
	private float newPlate[][];
	
	private int dim;
	private int left, right, top, bottom;
	int iteration;
	
	public Diffusion(int dim, int left, int right, int top, int bottom)
	{
		this.dim = dim;
		
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		
		oldPlate = new float[dim + 2][dim + 2];
		newPlate = new float[dim + 2][dim + 2];
		
		initialize(oldPlate);
	    initialize(newPlate);
	    diffuse();
	}
	
	public int getIteration() { return iteration; }
	
	//initialize plate with temperatures on appropriate edges
	//inner plate is implicitly set to zero for all cells not on an edge
	public void initialize(float[][] plate)
	{
		for(int i=1; i <= dim; i++)
		{
			plate[i][0] = left;
			plate[i][dim+1] = right;
			
			plate[0][i] = top;
			plate[dim+1][i] = bottom;
		}
	}
	
	//simulation of heat diffusion on plate
	public void diffuse()
	{
		iteration = 0;
		
		do{
			for (int i = 1; i <= dim; i++) 
		        for (int j = 1; j <= dim; j++) 
		          newPlate[i][j] = (oldPlate[i + 1][j] + oldPlate[i - 1][j] +
		                            oldPlate[i][j + 1] + oldPlate[i][j - 1]) / 4.0f;
	
			swap();
	      
		}while(! done() && ++iteration < Math.max(10000, dim * 100));
	}
	
	//return true if all the cells have a change less than 0.0001
	//return false otherwise
	private boolean done()
	{
		for(int i=1; i <= dim; i++)
			for(int j=1; j <= dim; j++)
				if(Math.abs(newPlate[i][j] - oldPlate[i][j]) >= 0.0001)
					return false;
					
		return true;
	}
	
	//swap old and new plate 
	private void swap()
	{
		float[][] temp;
		
		temp = oldPlate;
		oldPlate = newPlate;
		newPlate = temp;
	}
	
	//print table with 2 decimal precision
	public void printTable()
	{
		for(int i=1; i <= dim; i++)
		{
			for(int j=1; j <= dim; j++)
				System.out.format("%6.2f ", newPlate[i][j]);
			
			System.out.println();
		}
	}
	 
}
