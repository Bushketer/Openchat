


public class Main
{
	public static void main(String[] args)
	{
		if(args.length == 1)
		{
			if(args[0].equals("Server"))
			{
				System.out.println("running server");
				Network_Server server = new Network_Server();
				server.startServer(10800);
			}
		}
		else
		{
			System.out.println("running client");
			Tests test = new Tests();
			test.messageServer("127.0.0.1", 10800);
		}
	}
}
