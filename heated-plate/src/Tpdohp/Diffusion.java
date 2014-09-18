package Tpdohp;

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
	    //printTable();
	}
	
	public int getIteration() { return iteration; }
	
	public LatticePoint initialize(LatticePoint plateRoot)
	{
		plateRoot = new LatticePoint(-1);
		
		LatticePoint rowIndex = plateRoot;
		LatticePoint trav1 = rowIndex;
		LatticePoint trav2;
		
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
				if(j == dim) temp = rightTemp;
				if(i == dim) temp = bottomTemp;
				
				trav2.right = new LatticePoint(temp);
				trav2.right.left = trav2;
				
				trav1 = trav1.right;
				trav2 = trav2.right;
			}
			
			rowIndex = rowIndex.down;
		}
		
		return plateRoot;
	}
	
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
	      
		}while(! done() && iteration++ < 10000);
	}
	
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
	
	public void printTable()
	{
		LatticePoint printRow = newPlateRoot;
		
		for(int i=0; i < dim+2; i++)
		{
			LatticePoint printTrav = printRow;
			
			for(int j=0; j < dim+2; j++)
			{
				System.out.format("%6.2f ", printTrav.temperature);
				printTrav = printTrav.right;
				
			}
			printRow = printRow.down;
			
			System.out.println();
		}	
	}
	 
}