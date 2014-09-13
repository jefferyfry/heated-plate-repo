package Gallhp.algo;

/**
 * Class for the Tpdohp algorithm.
 * @author jeffro
 *
 */
public class Tpdohp4ui implements HeatedPlate {
	
	private LatticePoint oldPlateRoot, newPlateRoot;
	
	private int dimension;
	private int leftTemp, rightTemp, topTemp, bottomTemp;
	private int iteration=0;

	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#initialize(int, double, double, double, double)
	 */
	@Override
	public void initialize(int dimension, int left, int right,
			int top, int bottom) {
		this.dimension = dimension;
		
		this.leftTemp = left;
		this.rightTemp = right;
		this.topTemp = top;
		this.bottomTemp = bottom;
		
		oldPlateRoot = initializePlate(oldPlateRoot);
		newPlateRoot = initializePlate(newPlateRoot);
	}
	
	public LatticePoint initializePlate(LatticePoint plateRoot)
	{
		plateRoot = new LatticePoint(-1);
		
		LatticePoint rowIndex = plateRoot;
		LatticePoint trav1 = rowIndex;
		LatticePoint trav2;
		
		for(int i=0; i < dimension+1; i++)
		{
			trav1.right = new LatticePoint(topTemp);
			trav1.right.left = trav1;
			trav1 = trav1.right;
		}
		
		for(int i=0; i < dimension+1; i++)
		{
			trav1 = rowIndex;
			trav2 = new LatticePoint(leftTemp);
			
			for(int j=0; j < dimension+1; j++)
			{
				trav2.up = trav1;
				trav1.down = trav2;
				
				int temp = 0;
				if(j == dimension) temp = rightTemp;
				if(i == dimension) temp = bottomTemp;
				
				trav2.right = new LatticePoint(temp);
				trav2.right.left = trav2;
				
				trav1 = trav1.right;
				trav2 = trav2.right;
			}
			
			rowIndex = rowIndex.down;
		}
		
		return plateRoot;
	}

	/* (non-Javadoc)
	 * @see Gallhp.algo.HeatedPlate#nextResults()
	 */
	@Override
	public double[][] nextResults() {
		// TODO Auto-generated method stub
		return null;
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
			LatticePoint newPlateRowIndex = newPlateRoot;
			LatticePoint oldPlateRowIndex = oldPlateRoot;
			
			for(int i=0; i < dimension; i++)
			{
				newPlateRowIndex = newPlateRowIndex.down;
				oldPlateRowIndex = oldPlateRowIndex.down;
				LatticePoint travNewPlate = newPlateRowIndex;
				LatticePoint travOldPlate = oldPlateRowIndex;
				
				for(int j=0; j < dimension; j++)
				{
					travNewPlate = travNewPlate.right;
					travOldPlate = travOldPlate.right;
					
					travNewPlate.temperature = (travOldPlate.left.temperature + travOldPlate.right.temperature +
							travOldPlate.up.temperature + travOldPlate.down.temperature) / 4.0;
				}
			}	
		     
			swap();
	      
		}while(! done() && iteration++ < 10000);
		
		return convert2double(newPlateRoot);
	}

	private double[][] convert2double(LatticePoint newPlateRoot) {
		double[][] newArray = new double[dimension+2][dimension+2];
		LatticePoint thisPoint = newPlateRoot;
		for(int i=0; i < newArray.length; i++)
		{
			for(int j=0; j < newArray.length; j++)
			{
				newArray[i][j] = thisPoint.temperature;
				thisPoint = thisPoint.right;
				System.out.format("(%d,%d)=%f ", i,j,thisPoint.temperature);
			}
			thisPoint = thisPoint.down;
			System.out.println();
		}
		return newArray;
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
		LatticePoint newPlateRowIndex = newPlateRoot;
		LatticePoint oldPlateRowIndex = oldPlateRoot;
		
		for(int i=0; i < dimension; i++)
		{
			newPlateRowIndex = newPlateRowIndex.down;
			oldPlateRowIndex = oldPlateRowIndex.down;
			LatticePoint travNewPlate = newPlateRowIndex;
			LatticePoint travOldPlate = oldPlateRowIndex;
			
			for(int j=0; j < dimension; j++)
			{
				travNewPlate = travNewPlate.right;
				travOldPlate = travOldPlate.right;
				
				if(travNewPlate.temperature - travOldPlate.temperature != 0)
					return false;
			}
		}	
					
		return true;
	}
	
	private void swap()
	{
		LatticePoint temp;
		
		temp = oldPlateRoot;
		oldPlateRoot = newPlateRoot;
		newPlateRoot = temp;
	}
	
	private class LatticePoint
	{
		private double temperature;
		private LatticePoint up, down, left, right;
			
		public LatticePoint(double temperature)
		{
			this.temperature = temperature;
		}	
	}
}
