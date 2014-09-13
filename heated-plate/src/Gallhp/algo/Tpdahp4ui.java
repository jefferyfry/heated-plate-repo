package Gallhp.algo;

/**
 * Class for the Tpdahp algorithm.
 * @author jeffro
 *
 */
public class Tpdahp4ui implements HeatedPlate {
	
	private int dimension;
	private double left;
	private double right;
	private double top;
	private double bottom;
	private int iteration=0;
	
	private double oldPlate[][];
	private double newPlate[][];

	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#initialize(int, double, double, double, double)
	 */
	@Override
	public void initialize(int dimension, int left, int right,
			int top, int bottom) {
		this.dimension = dimension;
		this.left=left;
		this.right=right;
		this.top=top;
		this.bottom=bottom;
		this.iteration=0;
		
		oldPlate = new double[dimension + 2][dimension + 2];
		newPlate = new double[dimension + 2][dimension + 2];
		
		initializePlate(oldPlate);
	    initializePlate(newPlate);

	}
	
	private void initializePlate(double[][] plate)
	{
		for(int i=1; i <= this.dimension; i++)
		{
			plate[i][0] = left;
			plate[i][this.dimension+1] = right;
			
			plate[0][i] = top;
			plate[this.dimension+1][i] = bottom;
		}
	}

	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#nextResults()
	 */
	@Override
	public double[][] nextResults() {
		for (int i = 1; i <= this.dimension; i++) 
	        for (int j = 1; j <= this.dimension; j++) 
	          newPlate[i][j] = (oldPlate[i + 1][j] + oldPlate[i - 1][j] +
	                            oldPlate[i][j + 1] + oldPlate[i][j - 1]) / 4.0;
	      
		swap();
		iteration++;
		return newPlate;
	}

	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return (!done() && iteration < 10000);
	}

	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#getFinalResults()
	 */
	@Override
	public double[][] getFinalResults() {
		do{
			for (int i = 1; i <= this.dimension; i++) 
		        for (int j = 1; j <= this.dimension; j++) 
		          newPlate[i][j] = (oldPlate[i + 1][j] + oldPlate[i - 1][j] +
		                            oldPlate[i][j + 1] + oldPlate[i][j - 1]) / 4.0;
		      
			swap();
		      
		}while(!done() && iteration++ < 10000);
		
		return newPlate;
	}
	
	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#getIterations()
	 */
	@Override
	public int getIterations() {
		return iteration;
	}
	
	private boolean done()
	{
		for(int i=1; i <= this.dimension; i++)
			for(int j=1; j <= this.dimension; j++)
				if(newPlate[i][j] - oldPlate[i][j] != 0)
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
}
