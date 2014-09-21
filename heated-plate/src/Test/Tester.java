package Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JOptionPane;

public class Tester
{
	public static void main(String...args) throws IOException
	{
		int dim, left, right, top, bottom;
		
		Object[] options = {"Automated", "Manual", "Step-Range"};
		int choice = JOptionPane.showOptionDialog(null, 
				"Would you like to start an automated or manual run?", 
				"Test Type",
				 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				 null,
				 options,
				 options[0]);
		
		FileWriter write = new FileWriter("results.csv");
		PrintWriter pw = new PrintWriter(write);
		
		if(choice == 0)//Automated choice
		{
			int testCycles = Integer.parseInt(JOptionPane.showInputDialog("How many random cycles?"));
			
			for(int i=0; i < testCycles; i++)
			{
				System.out.println("\nCycle " + (i + 1));
				pw.println("\nCycle " + (i + 1));
				
				Random randomGen = new Random();
				
				dim = randomGen.nextInt(49) + 1;
				System.out.println("\nDimension: " + dim);
				pw.println("\nDimension: , " + dim);
				
				left = randomGen.nextInt(101);
				right = randomGen.nextInt(101);
				top = randomGen.nextInt(101);
				bottom = randomGen.nextInt(101);
				System.out.println("\nLeft Temp: " + left + "\nTop Temp: " + top + "\nRight Temp: " + right + "\nBottom Temp: " + bottom);
				pw.println("\nLeft Temp: , " + left + "\nTop Temp: , " + top + "\nRight Temp: , " + right + "\nBottom Temp: , " + bottom);
				
				Runtime runtime = Runtime.getRuntime();
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpdahp - double precision - 8 bytes - 64 bits");
				pw.println("\nPrecision: Tpdahp - double precision - 8 bytes - 64 bits");
				long startTime = System.currentTimeMillis();
				long startMemory = runtime.totalMemory() - runtime.freeMemory();
				Tpdahp.Diffusion tpdahp = new Tpdahp.Diffusion(dim, left, right, top, bottom);
				long endTime = System.currentTimeMillis();
				long endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpdahp.getIteration());
				pw.println("Iterations: , " + tpdahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + (endMemory-startMemory));
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + (endMemory-startMemory));
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + (endMemory-startMemory)/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpfahp - float precision - 4 bytes - 32 bits");
				pw.println("\nPrecision: Tpfahp - float precision - 4 bytes - 32 bits");
				startTime = System.currentTimeMillis();
				startMemory = runtime.totalMemory() - runtime.freeMemory();
				Tpfahp.Diffusion tpfahp = new Tpfahp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpfahp.getIteration());
				pw.println("Iterations: , " + tpfahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + (endMemory-startMemory));
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + (endMemory-startMemory));
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + (endMemory-startMemory)/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Twfahp - Float precision - 4 bytes - 32 bits");
				pw.println("\nPrecision: Twfahp - Float precision - 4 bytes - 32 bits");
				startTime = System.currentTimeMillis();
				startMemory = runtime.totalMemory() - runtime.freeMemory();
				Twfahp.Diffusion twfahp = new Twfahp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + twfahp.getIteration());
				pw.println("Iterations: , " + twfahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + (endMemory-startMemory));
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + (endMemory-startMemory));
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + (endMemory-startMemory)/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpdohp - double precision - 8 bytes - 64 bits");
				pw.println("\nPrecision: Tpdohp - double precision - 8 bytes - 64 bits");
				startTime = System.currentTimeMillis();
				startMemory = runtime.totalMemory() - runtime.freeMemory();
				Tpdohp.Diffusion tpdohp = new Tpdohp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpdohp.getIteration());
				pw.println("Iterations: , " + tpdohp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + (endMemory-startMemory));
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + (endMemory-startMemory));
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + (endMemory-startMemory)/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
			}
		}
		else if(choice == 1) //Manual choice
		{
			int runAgain, cycle = 0;
			
			do{
				System.out.println("\nCycle " + (++cycle));
				pw.println("\nCycle , " + cycle);
				
				//Dimension range not checked
				dim = Integer.parseInt(JOptionPane.showInputDialog("Enter dimension (integer value greater than 0):"));
				System.out.println("\nDimension: " + dim);
				pw.println("\nDimension: , " + dim);
				
				//Temperature range not checked
				left = Integer.parseInt(JOptionPane.showInputDialog("Enter left temperature (integer value between 0 - 100, inclusive):"));
				right = Integer.parseInt(JOptionPane.showInputDialog("Enter right temperature (integer value between 0 - 100, inclusive):"));
				top = Integer.parseInt(JOptionPane.showInputDialog("Enter top temperature (integer value between 0 - 100, inclusive):"));
				bottom = Integer.parseInt(JOptionPane.showInputDialog("Enter bottom temperature (integer value between 0 - 100, inclusive):"));
				System.out.println("\nLeft Temp: " + left + "\nTop Temp: " + top + "\nRight Temp: " + right + "\nBottom Temp: " + bottom);
				pw.println("\nLeft Temp: , " + left + "\nTop Temp: , " + top + "\nRight Temp: , " + right + "\nBottom Temp: , " + bottom);
				
				Runtime runtime = Runtime.getRuntime();
				runtime.gc();
				
				System.out.println("\nPrecision: Tpdahp - double precision - 8 bytes - 64 bits");
				pw.println("\nPrecision: Tpdahp - double precision - 8 bytes - 64 bits");
				long startTime = System.currentTimeMillis();
				Tpdahp.Diffusion tpdahp = new Tpdahp.Diffusion(dim, left, right, top, bottom);
				long endTime = System.currentTimeMillis();
				long endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpdahp.getIteration());
				pw.println("Iterations: , " + tpdahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpfahp - float precision - 4 bytes - 32 bits");
				pw.println("\nPrecision: Tpfahp - float precision - 4 bytes - 32 bits");
				startTime = System.currentTimeMillis();
				Tpfahp.Diffusion tpfahp = new Tpfahp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpfahp.getIteration());
				pw.println("Iterations: , " + tpfahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Twfahp - Float precision - 4 bytes - 32 bits");
				pw.println("\nPrecision: Twfahp - Float precision - 4 bytes - 32 bits");
				startTime = System.currentTimeMillis();
				Twfahp.Diffusion twfahp = new Twfahp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + twfahp.getIteration());
				pw.println("Iterations: , " + twfahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpdohp - double precision - 8 bytes - 64 bits");
				pw.println("\nPrecision: Tpdohp - double precision - 8 bytes - 64 bits");
				startTime = System.currentTimeMillis();
				Tpdohp.Diffusion tpdohp = new Tpdohp.Diffusion(dim, left, right, top, bottom);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpdohp.getIteration());
				pw.println("Iterations: , " + tpdohp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
				
				runAgain = JOptionPane.showConfirmDialog(null, "Run again?");
				
			}while(runAgain == 0);
		}
		else //Step-Range choice
		{
			int dimStart = Integer.parseInt(JOptionPane.showInputDialog("Starting dimension?"));
			int dimEnd = Integer.parseInt(JOptionPane.showInputDialog("Ending dimension?"));
			int dimStep = Integer.parseInt(JOptionPane.showInputDialog("Dimension step?"));
			System.out.println("Dimension Start: " + dimStart + " \nDimension End: " + dimEnd + " \nDimension Step: " + dimStep);
			pw.println("Dimension Start: , " + dimStart + " \nDimension End: , " + dimEnd + " \nDimension Step: , " + dimStep);
			
			int tempLeftStart = Integer.parseInt(JOptionPane.showInputDialog("Starting left temp?"));
			int tempLeftStep = Integer.parseInt(JOptionPane.showInputDialog("Left temp step?"));
			System.out.println("Starting left temp: " + tempLeftStart + " \nLeft temp step: " + tempLeftStep);
			pw.println("Starting left temp: , " + tempLeftStart + " \nLeft temp step: , " + tempLeftStep);
			
			int tempTopStart = Integer.parseInt(JOptionPane.showInputDialog("Starting top temp?"));
			int tempTopStep = Integer.parseInt(JOptionPane.showInputDialog("Top temp step?"));
			System.out.println("Starting top temp: " + tempTopStart + " \nTop temp step: " + tempTopStep);
			pw.println("Starting top temp: , " + tempTopStart + " \nTop temp step: , " + tempTopStep);
			
			int tempRightStart = Integer.parseInt(JOptionPane.showInputDialog("Starting right temp?"));
			int tempRightStep = Integer.parseInt(JOptionPane.showInputDialog("Right temp step?"));
			System.out.println("Starting right temp: " + tempRightStart + " \nRight temp step: " + tempRightStep);
			pw.println("Starting right temp: , " + tempRightStart + " \nRight temp step: , " + tempRightStep);
			
			int tempBotStart = Integer.parseInt(JOptionPane.showInputDialog("Starting bottom temp?"));
			int tempBotStep = Integer.parseInt(JOptionPane.showInputDialog("Bottom temp step?"));
			System.out.println("Starting bottom temp: " + tempBotStart + " \nBottom temp step: " + tempBotStep);
			pw.println("Starting bottom temp: , " + tempBotStart + " \nBottom temp step: , " + tempBotStep);
			
			int cycle = 0;
			for(int d=dimStart; d <= dimEnd; d+=dimStep)
			{
				System.out.println("\nCycle " + (++cycle));
				pw.println("\nCycle " + cycle);
				
				System.out.println("\nDimension: " + d);
				pw.println("\nDimension: , " + d);
				
				System.out.println("\nLeft Temp: " + tempLeftStart + "\nTop Temp: " + tempTopStart + "\nRight Temp: " + tempRightStart + "\nBottom Temp: " + tempBotStart);
				pw.println("\nLeft Temp: , " + tempLeftStart + "\nTop Temp: , " + tempTopStart + "\nRight Temp: , " + tempRightStart + "\nBottom Temp: , " + tempBotStart);
				
				Runtime runtime = Runtime.getRuntime();
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpdahp - double precision - 8 bytes - 64 bits");
				pw.println("\nPrecision: Tpdahp - double precision - 8 bytes - 64 bits");
				long startTime = System.currentTimeMillis();
				Tpdahp.Diffusion tpdahp = new Tpdahp.Diffusion(d, tempLeftStart, tempRightStart, tempTopStart, tempBotStart);
				long endTime = System.currentTimeMillis();
				long endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpdahp.getIteration());
				pw.println("Iterations: , " + tpdahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpfahp - float precision - 4 bytes - 32 bits");
				pw.println("\nPrecision: Tpfahp - float precision - 4 bytes - 32 bits");
				startTime = System.currentTimeMillis();
				Tpfahp.Diffusion tpfahp = new Tpfahp.Diffusion(d, tempLeftStart, tempRightStart, tempTopStart, tempBotStart);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpfahp.getIteration());
				pw.println("Iterations: , " + tpfahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Twfahp - Float precision - 4 bytes - 32 bits");
				pw.println("\nPrecision: Twfahp - Float precision - 4 bytes - 32 bits");
				startTime = System.currentTimeMillis();
				Twfahp.Diffusion twfahp = new Twfahp.Diffusion(d, tempLeftStart, tempRightStart, tempTopStart, tempBotStart);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + twfahp.getIteration());
				pw.println("Iterations: , " + twfahp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
				
				runtime.gc();
				
				System.out.println("\nPrecision: Tpdohp - double precision - 8 bytes - 64 bits");
				pw.println("\nPrecision: Tpdohp - double precision - 8 bytes - 64 bits");
				startTime = System.currentTimeMillis();
				Tpdohp.Diffusion tpdohp = new Tpdohp.Diffusion(d, tempLeftStart, tempRightStart, tempTopStart, tempBotStart);
				endTime = System.currentTimeMillis();
				endMemory = runtime.totalMemory() - runtime.freeMemory();
				System.out.println("Iterations: " + tpdohp.getIteration());
				pw.println("Iterations: , " + tpdohp.getIteration());
				System.out.println("Max Memory in JVM (bytes): " + runtime.maxMemory() + "\nMemory Usage (bytes): " + endMemory);
				pw.println("Max Memory in JVM (bytes): , " + runtime.maxMemory() + "\nMemory Usage (bytes): , " + endMemory);
				System.out.println("Max Memory in JVM (MB): " + runtime.maxMemory()/1000000.0 + "\nMemory Usage (MB): " + endMemory/1000000.0);
				System.out.println("Elapsed Time (ms): " + (endTime-startTime));
				pw.println("Elapsed Time (ms): , " + (endTime-startTime));
				
				tempLeftStart += tempLeftStep;
				tempRightStart += tempRightStep;
				tempTopStart += tempTopStep;
				tempBotStart += tempBotStep;
			}
		}
		
		pw.close();
		write.close();
	}
}
