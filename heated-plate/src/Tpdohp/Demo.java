package Tpdohp;

public class Demo 
{
	public static void main(String...args) 
	{
		int dim = Integer.parseInt(args[1]);
		int left = Integer.parseInt(args[3]), 
			right = Integer.parseInt(args[5]), 
			top = Integer.parseInt(args[7]), 
			bottom = Integer.parseInt(args[9]); 
		
		Diffusion diff = new Diffusion(dim, left, right, top, bottom);
	}
}
