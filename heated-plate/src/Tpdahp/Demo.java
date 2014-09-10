package Tpdahp;

public class Demo 
{
	public static void main(String...args) 
	{
		int dimension = Integer.parseInt(args[0]);
		int left = Integer.parseInt(args[1]), 
			right = Integer.parseInt(args[2]), 
			top = Integer.parseInt(args[3]), 
			bottom = Integer.parseInt(args[4]); 
		
		Diffusion diff = new Diffusion(dimension, left, right, top, bottom);
	}
}
