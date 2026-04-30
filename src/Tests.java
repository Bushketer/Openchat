



public class Tests
{

	public void messageServer(String ip, int port)
	{
		Network_Client client = new Network_Client();
		client.startConnection(ip, port);
		
		String response;
		response = client.sendMessage("Hello");
		System.out.println("RECEIVED: " + response);
		response = client.sendMessage("Hey how are you, server?");
		System.out.println("RECEIVED: " + response);
		response = client.sendMessage("Everything works!");
		System.out.println("RECEIVED: " + response);
		response = client.sendMessage("exit");
		System.out.println("RECEIVED: " + response);

		response = client.sendMessage("test");
		System.out.println("RECEIVED: " + response);
	}



}
