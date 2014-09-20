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
		this.iteration=0;
		
		oldPlateRoot = initializePlate(oldPlateRoot);
		newPlateRoot = initializePlate(newPlateRoot);
	}
	
	public LatticePoint initializePlate(LatticePoint plateRoot)
	{
		plateRoot = new LatticePoint(0);
		
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
		iteration++;
		return convert2double(newPlateRoot);
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
	      
		}while(! done() && ++iteration < Math.max(10000, dimension * 100));
		
		return convert2double(newPlateRoot);
	}

	private double[][] convert2double(LatticePoint newPlateRoot) {
		double[][] newArray = new double[dimension+2][dimension+2];
		LatticePoint latticeRow = newPlateRoot;
		
		for(int i=0; i < dimension+2; i++)
		{
			LatticePoint latticeTrav = latticeRow;
			
			for(int j=0; j < dimension+2; j++)
			{
				if((i==0&&j==0)
						||(i==newArray.length-1&&j==0)
						||(i==0&&j==newArray.length-1)
						||(i==newArray.length-1&&j==newArray.length-1))
					newArray[i][j] = 0; //the basic algorithm initialized the corners to non-zero values
										//this doesn't impact the results, but does impact the gui since I show the edges
										//to work around this, i just set the corners to zero
				else 
					newArray[i][j] = latticeTrav.temperature;
					
				latticeTrav = latticeTrav.right;
			}
			latticeRow = latticeRow.down;
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
				
				if(Math.abs(travNewPlate.temperature - travOldPlate.temperature) > 0.0001)
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
