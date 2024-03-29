package Tpdohp;

/**
 * Class for the Tpdohp algorithm.
 * @author aabreu
 *
 */
public class Diffusion 
{
	private class LatticePoint
	{
		private double temperature;
		private LatticePoint up, down, left, right;
			
		public LatticePoint(double temperature)
		{
			this.temperature = temperature;
		}	
	}
	
	private LatticePoint oldPlateRoot, newPlateRoot;
	
	private int dim;
	private int leftTemp, rightTemp, topTemp, bottomTemp;
	int iteration;
	
	public Diffusion(int dim, int left, int right, int top, int bottom)
	{
		this.dim = dim;
		
		this.leftTemp = left;
		this.rightTemp = right;
		this.topTemp = top;
		this.bottomTemp = bottom;
		
		oldPlateRoot = initialize(oldPlateRoot);
		newPlateRoot = initialize(newPlateRoot);
	    diffuse();
	}
	
	public int getIteration() { return iteration; }
	
	//initialize plate with temperatures on appropriate edges
	//inner plate is implicitly set to zero for all cells not on an edge
	public LatticePoint initialize(LatticePoint plateRoot)
	{
		//root node to keep a pointer to the plate grid
		plateRoot = new LatticePoint(-1);
		
		LatticePoint rowIndex = plateRoot; //keep track of current row
		LatticePoint trav1 = rowIndex; //node traveler for previous row
		LatticePoint trav2; //node traveler for current row
		
		//create top row
		for(int i=0; i < dim+1; i++)
		{
			trav1.right = new LatticePoint(topTemp);
			trav1.right.left = trav1;
			trav1 = trav1.right;
		}
		
		for(int i=0; i < dim+1; i++)
		{
			trav1 = rowIndex;
			trav2 = new LatticePoint(leftTemp);
			
			for(int j=0; j < dim+1; j++)
			{
				trav2.up = trav1;
				trav1.down = trav2;
				
				int temp = 0;
				if(j == dim) temp = rightTemp; //if last column assign right temp
				if(i == dim) temp = bottomTemp; //if last row assign bottom temp
				
				trav2.right = new LatticePoint(temp);
				trav2.right.left = trav2;
				
				trav1 = trav1.right;
				trav2 = trav2.right;
			}
			
			rowIndex = rowIndex.down;
		}
		
		return plateRoot;
	}
	
	//simulation of heat diffusion on plate
	public void diffuse()
	{
		iteration = 0;
		
		do{
			LatticePoint newPlateRowIndex = newPlateRoot;
			LatticePoint oldPlateRowIndex = oldPlateRoot;
			
			for(int i=0; i < dim; i++)
			{
				newPlateRowIndex = newPlateRowIndex.down;
				oldPlateRowIndex = oldPlateRowIndex.down;
				LatticePoint travNewPlate = newPlateRowIndex;
				LatticePoint travOldPlate = oldPlateRowIndex;
				
				for(int j=0; j < dim; j++)
				{
					travNewPlate = travNewPlate.right;
					travOldPlate = travOldPlate.right;
					
					travNewPlate.temperature = (travOldPlate.left.temperature + travOldPlate.right.temperature +
							travOldPlate.up.temperature + travOldPlate.down.temperature) / 4.0;
				}
			}	
		     
			swap();
	      
		}while(! done() && ++iteration < Math.max(10000, dim * 100));
	}
	
	//return true if all the cells have a change less than or equal to 0.0001
	//return false otherwise
	private boolean done()
	{
		LatticePoint newPlateRowIndex = newPlateRoot;
		LatticePoint oldPlateRowIndex = oldPlateRoot;
		
		for(int i=0; i < dim; i++)
		{
			newPlateRowIndex = newPlateRowIndex.down;
			oldPlateRowIndex = oldPlateRowIndex.down;
			LatticePoint travNewPlate = newPlateRowIndex;
			LatticePoint travOldPlate = oldPlateRowIndex;
			
			for(int j=0; j < dim; j++)
			{
				travNewPlate = travNewPlate.right;
				travOldPlate = travOldPlate.right;
				
				if(Math.abs(travNewPlate.temperature - travOldPlate.temperature) > 0.0001)
					return false;
			}
		}	
					
		return true;
	}
	
	//swap old and new plate 
	private void swap()
	{
		LatticePoint temp;
		
		temp = oldPlateRoot;
		oldPlateRoot = newPlateRoot;
		newPlateRoot = temp;
	}
	
	//print table with 2 decimal precision
	public void printTable()
	{
		LatticePoint printRow = newPlateRoot;
		
		for(int i=0; i < dim; i++)
		{	
			printRow = printRow.down;
			LatticePoint printTrav = printRow;
			
			for(int j=0; j < dim; j++)
			{
				printTrav = printTrav.right;
				System.out.format("%6.2f ", printTrav.temperature);
			}
			System.out.println();
		}	
	}
	 
}