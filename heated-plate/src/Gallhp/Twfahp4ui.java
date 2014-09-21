package Gallhp;

/**
 * Class for the Twfahp algorithm.
 * @author jeffro
 *
 */
public class Twfahp4ui implements HeatedPlate {

	private int dimension;
	private Float left;
	private Float right;
	private Float top;
	private Float bottom;
	private int iteration=0;
	
	private Float oldPlate[][];
	private Float newPlate[][];


	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#initialize(int, double, double, double, double)
	 */
	@Override
	public void initialize(int dimension, int left, int right,
			int top, int bottom) {
		this.dimension = dimension;
		this.left=new Float(left);
		this.right=new Float(right);
		this.top=new Float(top);
		this.bottom=new Float(bottom);
		this.iteration=0;
		
		oldPlate = new Float[dimension + 2][dimension + 2];
		newPlate = new Float[dimension + 2][dimension + 2];
		
		initializePlate(oldPlate);
	    initializePlate(newPlate);

	}

	private void initializePlate(Float[][] plate)
	{
		for(int i=1; i <= this.dimension; i++)
		{
			plate[i][0] = left;
			plate[i][this.dimension+1] = right;
			
			plate[0][i] = top;
			plate[this.dimension+1][i] = bottom;
		}
		
		//initialize the corners to 0
		plate[0][0] = new Float(0);
		plate[this.dimension+1][0] = new Float(0);
		plate[0][this.dimension+1] = new Float(0);
		plate[this.dimension+1][this.dimension+1] = new Float(0);
		
		for(int i=1; i <= this.dimension; i++)
			for(int j=1; j <= this.dimension; j++)
				plate[i][j] = new Float(0);
	}

	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#nextResults()
	 */
	@Override
	public double[][] nextResults() {
		for (int i = 1; i <= this.dimension; i++) 
	        for (int j = 1; j <= this.dimension; j++) 
	          newPlate[i][j] = (oldPlate[i + 1][j] + oldPlate[i - 1][j] +
	                            oldPlate[i][j + 1] + oldPlate[i][j - 1]) / 4.0F;
	      
		swap();
		iteration++;
		return convert2double(newPlate);
	}

	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return (!done() && iteration < Math.max(10000, dimension * 100));
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
		                            oldPlate[i][j + 1] + oldPlate[i][j - 1]) / 4.0F;
		      
			swap();
		      
		}while(! done() && ++iteration < Math.max(10000, dimension * 100));
		
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
				if(Math.abs(newPlate[i][j] - oldPlate[i][j]) >= 0.0001)
					return false;
		
		return true;
	}
	
	private void swap()
	{
		Float[][] temp;
		
		temp = oldPlate;
		oldPlate = newPlate;
		newPlate = temp;
	}
	
	private double[][] convert2double(Float[][] array){
		double[][] newArray = new double[array.length][array.length];
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array.length;j++){
				newArray[i][j] = (double)array[i][j];
			}
		}
		return newArray;
	}
}
