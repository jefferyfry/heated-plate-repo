package Gallhp.algo;

/**
 * Class for the Tpfahp algorithm.
 * @author jeffro
 *
 */
public class Tpfahp4ui implements HeatedPlate {
	
	private int dimension;
	private float left;
	private float right;
	private float top;
	private float bottom;
	private int iteration=0;
	
	private float oldPlate[][];
	private float newPlate[][];


	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#initialize(int, double, double, double, double)
	 */
	@Override
	public void initialize(int dimension, int left, int right,
			int top, int bottom) {
		this.dimension = dimension;
		this.left=(float)left;
		this.right=(float)right;
		this.top=(float)top;
		this.bottom=(float)bottom;
		this.iteration=0;
		
		oldPlate = new float[dimension + 2][dimension + 2];
		newPlate = new float[dimension + 2][dimension + 2];
		
		initializePlate(oldPlate);
	    initializePlate(newPlate);

	}

	private void initializePlate(float[][] plate)
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
	                            oldPlate[i][j + 1] + oldPlate[i][j - 1]) / 4.0f;
	      
		swap();
		iteration++;
		return convert2double(newPlate);
	}

	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return (!done() && iteration < 1000000);
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
		                            oldPlate[i][j + 1] + oldPlate[i][j - 1]) / 4.0f;
		      
			swap();
		      
		}while(! done() && ++iteration < 1000000);
		
		return convert2double(newPlate);
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
		float[][] temp;
		
		temp = oldPlate;
		oldPlate = newPlate;
		newPlate = temp;
	}
	
	private double[][] convert2double(float[][] array){
		double[][] newArray = new double[array.length][array.length];
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array.length;j++){
				newArray[i][j] = (double)array[i][j];
			}
		}
		return newArray;
	}

}
