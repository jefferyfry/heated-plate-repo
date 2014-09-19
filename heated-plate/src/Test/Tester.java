package Test;

import java.util.Random;

import javax.swing.JOptionPane;

public class Tester
{
	public static void main(String...args)
	{
		int dim, left, right, top, bottom;
		
		Object[] options = {"Automated", "Manual"};
		int choice = JOptionPane.showOptionDialog(null, 
				"Would you like to start an automated or manual run?", 
				"Test Type",
				 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				 null,
				 options,
				 options[0]);
		
		if(choice == 0)//Automated choice
		{
			int testCycles = Integer.parseInt(JOptionPane.showInputDialog("How many random cycles?"));
			
			for(int i=0; i < testCycles; i++)
			{
				System.out.println("\nCycle " + (i + 1));
				
				Random randomGen = new Random();
				
				dim = randomGen.nextInt(999) + 1;
				System.out.println("\nDimension: " + dim);
				
				left = randomGen.nextInt(101);
				right = randomGen.nextInt(101);
				top = randomGen.nextInt(101);
				bottom = randomGen.nextInt(101);
				System.out.println("\nLeft Temp: " + left + "\nTop Temp: " + top + "\nRight Temp: " + right + "\nLeft Temp: " + left);
				
				System.out.println("\nRelative change stopping criterion: Any change greater than 0 or 10,000 iterations and the computation will continue. "
						+ "(Maybe we should change this to be a factor of dimension)");
				
				
				Runtime runtime = Runtime.getRuntime();
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpdahp - double precision - 8 bytes - 64 bits");
				long startMemory = runtime.totalMemory() - runtime.freeMemory();
				long startTime = System.currentTimeMillis();
				Tpdahp.Diffusion tpdahp = new Tpdahp.Diffusion(dim, left, right, top, bottom);
				long endTime = System.currentTimeMillis();
				long endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpdahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + (endMemory - startMemory));
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + (endMemory - startMemory)/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpfahp - float precision - 4 bytes - 32 bits");
				startMemory = runtime.totalMemory() - runtime.freeMemory();
				startTime = System.currentTimeMillis();
				Tpfahp.Diffusion tpfahp = new Tpfahp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpfahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + (endMemory));
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + (endMemory - startMemory)/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Twfahp - Float precision - 4 bytes - 32 bits");
				startMemory = runtime.totalMemory() - runtime.freeMemory();
				startTime = System.currentTimeMillis();
				Twfahp.Diffusion twfahp = new Twfahp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + twfahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + (endMemory - startMemory));
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + (endMemory - startMemory)/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpdohp - double precision - 8 bytes - 64 bits");
				startMemory = runtime.totalMemory() - runtime.freeMemory();
				startTime = System.currentTimeMillis();
				Tpdohp.Diffusion tpdohp = new Tpdohp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpdohp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + (endMemory - startMemory));
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + (endMemory - startMemory)/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
			}
		}
		else //Manual choice
		{
			int runAgain, cycle = 0;
			
			do{
				System.out.println("\nCycle " + ++cycle);
				
				//Dimension range not checked
				dim = Integer.parseInt(JOptionPane.showInputDialog("Enter dimension (integer value greater than 0):"));
				System.out.println("\nDimension: " + dim);
				
				//Temperature range not checked
				left = Integer.parseInt(JOptionPane.showInputDialog("Enter left temperature (integer value between 0 - 100, inclusive):"));
				right = Integer.parseInt(JOptionPane.showInputDialog("Enter right temperature (integer value between 0 - 100, inclusive):"));
				top = Integer.parseInt(JOptionPane.showInputDialog("Enter top temperature (integer value between 0 - 100, inclusive):"));
				bottom = Integer.parseInt(JOptionPane.showInputDialog("Enter bottom temperature (integer value between 0 - 100, inclusive):"));
				System.out.println("\nLeft Temp: " + left + "\nTop Temp: " + top + "\nRight Temp: " + right + "\nLeft Temp: " + left);
				
				System.out.println("\nRelative change stopping criterion: Any change greater than 0 and the computation will continue. "
						+ "(Maybe we should change this to be a factor of dimension, or a user input)");
				
				Runtime runtime = Runtime.getRuntime();
				runtime.gc();
				
				System.out.println("\nPrecision: Tpdahp - double precision - 8 bytes - 64 bits");
				//long startMemory = runtime.totalMemory() - runtime.freeMemory();
				long startTime = System.currentTimeMillis();
				Tpdahp.Diffusion tpdahp = new Tpdahp.Diffusion(dim, left, right, top, bottom);
				long endTime = System.currentTimeMillis();
				long endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpdahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpfahp - float precision - 4 bytes - 32 bits");
				//startMemory = runtime.totalMemory() - runtime.freeMemory();
				startTime = System.currentTimeMillis();
				Tpfahp.Diffusion tpfahp = new Tpfahp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpfahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Twfahp - Float precision - 4 bytes - 32 bits");
				//startMemory = runtime.totalMemory() - runtime.freeMemory();
				startTime = System.currentTimeMillis();
				Twfahp.Diffusion twfahp = new Twfahp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + twfahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpdohp - double precision - 8 bytes - 64 bits");
				//startMemory = runtime.totalMemory() - runtime.freeMemory();
				startTime = System.currentTimeMillis();
				Tpdohp.Diffusion tpdohp = new Tpdohp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpdohp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				
				runAgain = JOptionPane.showConfirmDialog(null, "Run again?");
				
			}while(runAgain == 0);
		}
	}
}
