


public class Main
{
	public static void main(String[] args)
	{
		Utility.setVerbose(true);

		if(args.length == 1)
		{
			if(args[0].equals("Server"))
			{
				Utility.toTerminaln("Running server");
				NetworkServer server = new NetworkServer();
				server.startServer(10800);
			}
		}
		else
		{
			Utility.toTerminaln("Running client");
			Tests test = new Tests();
			test.messageServer("127.0.0.1", 10800);
		}
	}
}
