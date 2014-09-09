package Gallhp;

import common.HeatedPlate;

/**
 * Dummy class for generating random results for testing while waiting for Tpdahp, Tpfahp, Twfahp, Tpdohp to be completed.
 * @author jeffro
 *
 */
public class DummyHeatedPlateResults implements HeatedPlate {
	
	private int dimension=10;
	private int iteration=0;

	@Override
	public void initialize(int dimension, double left, double right,
			double top, double bottom) {
		this.dimension=dimension;
		this.iteration=0;
	}

	@Override
	public double[][] nextResults() {
		double results[][] = new double[dimension][dimension];
		for(int i=0;i<results.length;i++){
    		for(int j=0;j<results.length;j++)
    			results[i][j]=Math.random()*100;
    	}
		return results;
	}

	@Override
	public boolean hasNext() {
		if(this.iteration==25)
			return false;
		else {
			this.iteration++;
			return true;
		}
	}

	@Override
	public double[][] getFinalResults() {
		double results[][] = new double[dimension][dimension];
		for(int i=0;i<results.length;i++){
    		for(int j=0;j<results.length;j++)
    			results[i][j]=Math.random()*100;
    	}
		return results;
	}
}
