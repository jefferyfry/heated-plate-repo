package Gallhp;

import java.util.ArrayList;
import java.util.List;

public class HeatedPlateController {
	
	//dummy class for generating random results for now
	//private DummyHeatedPlateResults dummyHeatedPlate = new DummyHeatedPlateResults();
	
	//the heated plate in use
	private HeatedPlate thisHeatedPlate;
	
	//Tpdahp4ui algoritm
	private HeatedPlate tpdahp4ui = new Tpdahp4ui();
	
	//Tpfahp4ui algoritm
	private HeatedPlate tpfahp4ui = new Tpfahp4ui();
	
	//Twfahp4ui algoritm
	private HeatedPlate twfahp4ui = new Twfahp4ui();
	
	//Tpdohp4ui algoritm
	private HeatedPlate tpdohp4ui = new Tpdohp4ui();
	
	//determines whether the heated plate will be animated
	private boolean animation = false;
	
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
	public void start(int dimension,int left,int right,int top,int bottom,int algorithm,boolean animation){
		switch(algorithm){
			case 0:
				thisHeatedPlate = this.tpdahp4ui;
				break;
			case 1:
				thisHeatedPlate = this.tpfahp4ui;
				break;
			case 2:
				thisHeatedPlate = this.twfahp4ui;
				break;
			case 3:
				thisHeatedPlate = this.tpdohp4ui;
				break;
			default:
		}
		
		thisHeatedPlate.initialize(dimension, left, right, top, bottom);
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
				double[][]results = thisHeatedPlate.getFinalResults();
				fireHaveResults(results);
			}
			else { //animated
				do{
					double[][] results = thisHeatedPlate.nextResults();
					fireHaveResults(results);
					try {
						Thread.sleep(500);
					}
					catch(Exception e){}
				}while(thisHeatedPlate.hasNext()&&go);
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
