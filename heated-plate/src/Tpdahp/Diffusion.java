package Tpdahp;

/**
 * Class for the Tpdahp algorithm.
 * @author aabreu
 *
 */
public class Diffusion 
{
	private double oldPlate[][];
	private double newPlate[][];
	
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
		
		oldPlate = new double[dim + 2][dim + 2];
		newPlate = new double[dim + 2][dim + 2];
		
		initialize(oldPlate);
	    initialize(newPlate);
	    diffuse();
	}
	
	public int getIteration() { return iteration; }
	
	//initialize plate with temperatures on appropriate edges
	//inner plate is implicitly set to zero for all cells not on an edge
	public void initialize(double[][] plate)
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
		                            oldPlate[i][j + 1] + oldPlate[i][j - 1]) / 4.0;
		      
			swap();
		      
		}while(! done() && ++iteration < Math.max(10000, dim * 100));
	}
	
	//return true if all the cells have a change less than or equal to 0.0001
	//return false otherwise
	private boolean done()
	{
		for(int i=1; i <= dim; i++)
			for(int j=1; j <= dim; j++)
				if(Math.abs(newPlate[i][j] - oldPlate[i][j]) > 0.0001)
					return false;
					
		return true;
	}
	
	//swap old and new plate 
	private void swap()
	{
		double[][] temp;
		
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
