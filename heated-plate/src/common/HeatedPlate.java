package common;

/**
 * Interface to be implemented by the heated plate classes.  Methods provide support for "animation" in the gui and getting the final results.
 * @author jeffro
 *
 */
public interface HeatedPlate {
	
	/**
	 * Initialize the heated plate class.
	 * @param dimension dimension of the square lattice (number of rows and columns, not counting the edge values)
	 * @param left edge temperature for left
	 * @param right edge temperature for right
	 * @param top edge temperature for top
	 * @param bottom edge temperature for bottom
	 */
	public void initialize(int dimension,double left, double right,double top,double bottom);
	
	/**
	 * Returns the results for the next iteration of the algorithm.  Intended to be used for animation.
	 * @return 
	 */
	public double[][] nextResults();
	
	/**
	 * Check to see if the algorithm has finished.  Intended to be used for the animation.
	 * @return True if the algorithm is finished.
	 */
	public boolean hasNext();
	
	/**
	 * Gets the final results of the algorithm.
	 * @return
	 */
	public double[][] getFinalResults();

}
