package Tpfahp;

class Diffusion 
{
	private float oldPlate[][];
	private float newPlate[][];
	
	private int dim;
	private int left, right, top, bottom;
	
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
	    printTable();
	}
	
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
	
	public void diffuse()
	{
		int count = 0;
		
		do{
			for (int i = 1; i <= dim; i++) 
		        for (int j = 1; j <= dim; j++) 
		          newPlate[i][j] = (oldPlate[i + 1][j] + oldPlate[i - 1][j] +
		                            oldPlate[i][j + 1] + oldPlate[i][j - 1]) / 4.0f;
	
			swap();
	      
		}while(! done() && count++ < 10000);
		
		System.out.println(count);
	}
	
	private boolean done()
	{
		for(int i=1; i <= dim; i++)
			for(int j=1; j <= dim; j++)
				if(newPlate[i][j] - oldPlate[i][j] != 0)
					return false;
					
		return true;
	}
	
	private void swap()
	{
		float[][] temp;
		
		temp = oldPlate;
		oldPlate = newPlate;
		newPlate = temp;
	}
	
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
