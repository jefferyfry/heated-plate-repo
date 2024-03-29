package Twfahp;

/**
 * Demo is the simple launch class which just sets up the heated plate frame.
 * @author aabreu
 *
 */
public class Demo 
{
	public static void main(String...args) throws Exception 
	{
		int dim = 0;
		int left = 0, right = 0, top = 0, bottom = 0;
		
		//assign arguments to variable according to flag
		for(int i=0; i < args.length; i+=2)
		{
			if(args[i].compareToIgnoreCase("-d") == 0)
				dim = Integer.parseInt(args[i+1]);
			else if(args[i].compareToIgnoreCase("-l") == 0)
				left = Integer.parseInt(args[i+1]);
			else if(args[i].compareToIgnoreCase("-r") == 0)
				right = Integer.parseInt(args[i+1]);
			else if(args[i].compareToIgnoreCase("-t") == 0)
				top = Integer.parseInt(args[i+1]);
			else if(args[i].compareToIgnoreCase("-b") == 0)
				bottom = Integer.parseInt(args[i+1]);
			else
				throw new Exception("Argument flag is not recognized");
		}
	
		//instantiate a Diffusion object with passed dimensions and temperatures
		Diffusion diff = new Diffusion(dim, left, right, top, bottom);
		diff.printTable();
	}
}

