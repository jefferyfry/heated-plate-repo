package Gallhp;

import java.util.ArrayList;
import java.util.List;

public class HeatedPlateController {
	
	//dummy class for generating random results for now
	private DummyHeatedPlateResults dummyHeatedPlate = new DummyHeatedPlateResults();
	
	//determines whether the heated plate will be animated
	private boolean animation=false;
	
	//status flag for the executor thread
	private boolean go=true;
	
	//listeners
	private List<HeatedPlateControllerListener> listeners = new ArrayList<HeatedPlateControllerListener>();

	/**
	 * Default constructor.
	 */
	public HeatedPlateController() {
		//constructor will create each of the algorithm APIs
	}
	
	/**
	 * Starts the algorithm via the Executor thread.
	 * @param dimension
	 * @param left
	 * @param right
	 * @param top
	 * @param bottom
	 * @param algorithm
	 */
	public void start(int dimension,double left,double right,double top,double bottom,int algorithm,boolean animation){
		System.out.println("Started heat plate with dimension="+dimension+", left="+left+", right="+right+", top="+top+", bottom="+bottom+", algorithm="+algorithm+", animation="+animation);
		dummyHeatedPlate.initialize(dimension, left, right, top, bottom);
		this.animation=animation;
		this.go=true;
		(new Executor()).start();
	}
	
	/**
	 * Cancel a running executor.
	 */
	public void cancel(){
		this.go=false;
	}
	
	private void fireStarted(){
		for(HeatedPlateControllerListener listener:listeners)
			listener.started();
	}
	
	private void fireHaveResults(double[][] results){
		for(HeatedPlateControllerListener listener:listeners)
			listener.haveResults(results);
	}
	
	private void fireFinished(){
		for(HeatedPlateControllerListener listener:listeners)
			listener.finished();
	}
	
	/**
	 * Adds a HeatedPlateControllerListener.
	 * @param listener
	 */
	public void addListener(HeatedPlateControllerListener listener){
		listeners.add(listener);
	}
	
	/**
	 * Removes a HeatedPlateControllerListener.
	 * @param listener
	 */
	public void removeListener(HeatedPlateControllerListener listener){
		listeners.remove(listener);
	}

	/**
	 * Execution thread needed so as to not block IO.
	 * @author jeffro
	 *
	 */
	private class Executor extends Thread {
		
		public void run(){
			fireStarted();
			
			//if not animated
				//double[][] results = alg.getFinalResults()
				//fireHaveResults(results)
			//else animated
				//run a while(alg.hasNext())
					//double[][] results = alg.next()
					//fireHaveResults(results)
					//thread.sleep(500)
			
			if(!animation){
				double[][]results = dummyHeatedPlate.getFinalResults();
				fireHaveResults(results);
			}
			else { //animated
				while(dummyHeatedPlate.hasNext()&&go){
					double[][] results = dummyHeatedPlate.nextResults();
					fireHaveResults(results);
					try {
						Thread.sleep(500);
					}
					catch(Exception e){}
				}
			}
			
			fireFinished();
		}
	}
	
	/**
	 * Listens for updates from the Executor thread running the algorithm.
	 * @author jeffro
	 *
	 */
	interface HeatedPlateControllerListener {
		
		/**
		 * Tells the listener that the algorithm started.
		 */
		public void started();
		
		/**
		 * Provides results to the listener.
		 * @param results
		 */
		public void haveResults(double[][] results);
		
		/**
		 * Tells the listener that the algorithm finished.
		 */
		public void finished();
	}

}
