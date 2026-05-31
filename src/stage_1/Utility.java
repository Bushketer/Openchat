import java.lang.String;
import java.lang.Exception;


public class Utility
{
	
	static Boolean verbose = false;

	public static void toTerminaln(String msg)
	{
		if(verbose)
			System.out.println(msg);
	}



	public static void toTerminal(String msg)
	{
		if(verbose)
			System.out.print(msg);
	}


	
	public static void setVerbose(Boolean value)
	{
		verbose = value;
	}

}
